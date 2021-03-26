package Payment;

import Account.*;
import Job.Job;
import Job.VectorOfJobs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Payment {
	private int iD;
	private float amount;
	private Date date;
	private Customer customer;
	private Job[] jobs;
	private int dueDate;
	private VectorOfPayments vecPaym;
	private Card card;
	private Job job;
	private VectorOfJobs vecJob;


	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(int date) throws ParseException {
		this.date = convertDate(date);
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isOnTime() {
		throw new UnsupportedOperationException();
	}

	public Job[] getJobs() {
		return this.jobs;
	}

	public Card getCard() {
		return card;
	}

	private float calculateCost(float amount, Customer customer) {
		//check whether customer is valued and has a discount
		if (customer.getClass() == ValuedCustomer.class && !((ValuedCustomer) customer).getDiscSet().getDiscounts().isEmpty()) {
			//process flat discount
			if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getClass() == Discount.class) {
				return amount * (1 - ((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getDiscountRate());
			}
			//process banded discount
				if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0) instanceof DiscountBand) {
				//find correct band
				for (int bandItr = 0; bandItr < ((ValuedCustomer) customer).getDiscSet().getDiscounts().size(); bandItr++) {
					//get correct band (this needs to be changed to previous month's expenses
					if (((DiscountBand)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr)).getRange_min() <= amount
							&& amount < ((DiscountBand)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr)).getRange_max()) {
						//apply discount for band
						return amount * (1 - ((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr).getDiscountRate());
					}
				}
			}
			//process task discount
			if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0) instanceof TaskDiscount) {
				float totalDisc = 0;
				//loop through jobs
				for (int jobVecItr = 0; jobVecItr < jobs.length; jobVecItr++) {
					//cycle through each job's tasks
					for (int taskVecitr = 0; jobVecItr < jobs[jobVecItr].vecTaskJ.getVector().size(); taskVecitr++) {
						//check whether tasks of the jobs are contained in that of the discounts
						for (int discountTasksItr = 0; discountTasksItr < ((ValuedCustomer) customer).getDiscSet().getDiscounts().size(); discountTasksItr++) {
							//compare ID of task part of the job to those in the discount set
							if (jobs[jobVecItr].getVecTaskJ().getVector().get(taskVecitr).getTaskID() ==
									((TaskDiscount)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(discountTasksItr)).getTaskID()) {
								totalDisc += jobs[jobVecItr].getVecTaskJ().getVector().get(taskVecitr).getPrice() * (1 - ((TaskDiscount)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(discountTasksItr)).getRate());
							}
						}
					}
				}
				amount -= totalDisc;
			}
		}
		return amount;
	}

	private void upload() throws SQLException {
		//upload to database (also generates ID)
		String sql = "INSERT INTO payment (`date`, `paymentType`, `amount`) VALUES (?, ?, ?);";
		PreparedStatement prepStat = vecPaym.getPaymCtrl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		prepStat.setDate(1, date);
		if (card != null) {
			prepStat.setString(2, "card");
		} else {
			prepStat.setString(2, "cash");
		}
		prepStat.setFloat(3, amount);
		//write to DB
		vecPaym.getPaymCtrl().getControl().getDBC().getDBGateway().write(prepStat);
	}

	private void uploadCardDetails() throws SQLException, ParseException {
		//upload card details to database
		String sql = "INSERT INTO card_details (`PaymentpaymentID`, `type`, `expiryDate`, `last4Dig`) VALUES (?, ?, ?, ?)";
		PreparedStatement prepStat = vecPaym.getPaymCtrl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		prepStat.setInt(1, iD);
		prepStat.setString(2, card.getType());
		prepStat.setDate(3, convertDate(card.getExpiryDate()));
		prepStat.setInt(4, card.getLast4Digits());
		vecPaym.getPaymCtrl().getControl().getDBC().getDBGateway().write(prepStat);
	}

	private int generatedID() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		//retrieves payment with highest ID with matching values
		String sql = "SELECT `paymentID` FROM `payment` WHERE payment.date = ? AND payment.paymentType = ? AND amount = ?";
		PreparedStatement preparedStatement = vecPaym.getPaymCtrl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setDate(1, date);
		if (card != null) {
			preparedStatement.setString(2, "card");
		} else {
			preparedStatement.setString(2, "cash");
		}
		preparedStatement.setFloat(3, amount);
		ResultSet rs = vecPaym.getPaymCtrl().getControl().getDBC().read(preparedStatement);
		//Find the largest ID, this indicates this is the newest object and hence belongs to this one
		// as this function only gets called in the constructor
		int finalValue = -1, checkValue = -1;
		//get values from result set
		while (rs.next()) {
			//apply value to correct variable
			if (finalValue == -1) {
				finalValue = rs.getInt(1);
			} else {
				checkValue = rs.getInt(1);
			}
			//see if the check value is larger than the final value (which will be returned)
			if (checkValue > finalValue) {
				finalValue = checkValue;
			}
		}
		return finalValue;
	}

	private Date convertDate(Integer oldFormat) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		return (Date) originalFormat.parse(oldFormat.toString());
	}

	public void delayedPayment(int paymentID, int jobID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		// Check if the payment ID exists and if there is an associated JOB ID.

		//Database Version//
	}


	public Payment(float amount, int date, Customer customer, Job[] jobs, int dueDate) throws ParseException, SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		this.date = convertDate(date);
		this.customer = customer;
		this.jobs = jobs;
		this.dueDate = dueDate;
		this.amount = calculateCost(amount, customer);
		upload();
		this.iD = generatedID();
	}

	public Payment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) throws ParseException, SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		this.date = convertDate(date);
		this.customer = customer;
		this.jobs = jobs;
		this.dueDate = dueDate;
		this.card = card;
		this.amount = calculateCost(amount, customer);
		upload();
		this.iD = generatedID();
		uploadCardDetails();
	}
}