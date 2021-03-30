package Account;
import java.awt.*;
import java.sql.ResultSet;
import java.util.*;
import Job.Job;
import Job.VectorOfJobs;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ValuedCustomer extends Customer {
	public DiscountSet discSet;
	public VectorOfJobs vecJob;
	public VectorOfAccounts vecAcc;
	public Job job;
	public Customer customer;
	JFrame frame =new JFrame();

	public void addDiscountPlan(String type, float[] rates, int[] tasks, float[] ranges) {
		//check type
		if (type.equals("flat")) {
			//check if rates contains values
			if (rates.length == 0) return;
			//add a singular flat discount to the set
			//use only the first value of rates as only one value will be required and this will be contained in the first position
			discSet.addDiscount(new Discount(rates[0]));
		}
		if (type.equals("task")) {
			//check if rates and tasks is the same length and whether they contain values (checking one is enough)
			if (rates.length != tasks.length || rates.length == 0) return;
			//for every task add a task discount
			for (int i = 0; i < tasks.length; i++) {
				//task IDs and rates will be stores in the same locations
				discSet.addDiscount(new TaskDiscount(tasks[i], rates[i]));
			}
		}
		if (type.equals("band")) {
			//check whether the are an even number of values contained in ranges,
			//whether rates is half the length of ranges
			//and if they both are not of length 0
			if (ranges.length % 2 != 0 || rates.length != ranges.length / 2 || ranges.length == 0 || rates.length == 0) return;
			//keep track of ranges
			int rangeItr = 0;
			//for every rate add one band
			for (int rateItr = 0; rateItr < rates.length; rateItr++) {
				//ranges are stored: min, max, min, max, min, max etc.
				discSet.addDiscount(new DiscountBand(ranges[rangeItr], ranges[rangeItr + 1], rates[rateItr]));
				rangeItr += 2;
			}
		}
	}

	//this maybe need to be changed.
	public void alertManager() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//		String status = null;
//		int customerAccount=0;
//		Integer paymentID=0;
//		int month=0;
//		int monthCustomerAccount=0;
//		// code to read if job status, customer accountNO and paymentid
//		try {
//			PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement("SELECT `status`, `CustomeraccountNo`, `PaymentpaymentID` FROM job WHERE VALUES status=? AND CustomeraccountNo=?AND Paymentpayment=?");
//			preparedStatement.setString(1, status);
//			preparedStatement.setInt(2, customerAccount);
//			preparedStatement.setInt(3, paymentID);
//			ResultSet rj = vecJob.getControl().getControl().getDBC().read(preparedStatement);
//
//			//reads month and customer
//			PreparedStatement preStm = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement("SELECT `month`, `CustomeraccountNo` FROM job WHERE VALUES `month = ?` AND `CustomeraccountNo`=?  ");
//			preStm.setInt(1, month);
//			preStm.setInt(2, monthCustomerAccount);
//			ResultSet rm = vecJob.getControl().getControl().getDBC().read(preparedStatement);
//
//			//goes through all the month within month_spend data and checkes if customer in job and month_spend is the same if so it then checks if it has a paymentid, if not then it should alert manager.
//			for (int i = 0; i < rm.getFetchSize(); ++i) {
//				month = month + 1;
//				if (customerAccount == monthCustomerAccount) {
//					if (paymentID == null) {
//						// return a pop up alert on the office managers page. Not sure if i'm missing something.
//						// message should be displayed with the account number of the late payment
//						rm.getCharacterStream(monthCustomerAccount);
//						frame.setBackground(Color.white);
//						JOptionPane.showMessageDialog(frame, (monthCustomerAccount)+" Has not yet payed");
//
//					}
//				}
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}

	}

	public DiscountSet getDiscSet() {
		return discSet;
	}

	public ValuedCustomer(Customer customer, VectorOfAccounts vectorOfAccounts) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		super(customer.getCompany(), customer.getName(), customer.getAddress(), customer.getPhone(), vectorOfAccounts);
		discSet = new DiscountSet();
	}
}