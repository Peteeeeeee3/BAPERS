package Job;

import Account.Customer;
import Database.*;
import Payment.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Job {
	private int iD;
	private float price;
	private String summary;
	private String status;
	private int startTime;
	private int endTime;
	private int urgency;
	private int customerid;
	private int paymentid;
	public int startDate;
	private boolean isPaid = false;
	public VectorOfTasksForJob vecTaskJ;
	public VectorOfJobs vecJob;
	public Customer customer;
	public Payment payment;

	public int calculatePrice() {
		throw new UnsupportedOperationException();
	}

	public int calcEstTime() {
		throw new UnsupportedOperationException();
	}

	public void updateStatus(int jobID) throws SQLException {

		ArrayList<String> statusArray = new ArrayList<>();
		ArrayList<String> statusCompleteArray = new ArrayList<>();


		try {

			String find = "SELECT status FROM task_of_job WHERE" + jobID;
			PreparedStatement stmt = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(find);
			ResultSet result = stmt.executeQuery(find);

			while (result.next()) {
				statusArray.add(result.getString(1));
			}


			for(int i = 0; i<statusArray.size(); i++){
				statusCompleteArray.add("complete");
			}

			int count = 0;

			if(statusArray.contains("in progress") | statusArray.contains("complete")){

				for(int i = 0; i<statusArray.size(); i++){

					if(statusArray.get(i).equals(statusCompleteArray.get(i))){
						count++;
					}
				}

				if(count == statusArray.size()){
					System.out.println("complete");
					setStatus("complete", jobID);
				}

				else{
					System.out.println("in progress");
					setStatus("in progress", jobID);
				}
			}

			else{
				System.out.println("pending");
				setStatus("pending", jobID);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return this.iD;
	}

	public double getPrice() {
		return this.price;
	}

	public String getSummary() {
		return this.summary;
	}

	public int getCustomerid(){return customerid;}

	public int getPaymentid(){return paymentid;}

	public int getStartDate(){return startDate;}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status, int jobID) throws SQLException {  //this method is called by updateTask but can also be called manually. It updates the job status in the database.

		try {

			PreparedStatement stmt = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement
					("UPDATE Job SET status=" + status + " WHERE jobNumber=" + jobID);

			stmt.executeUpdate();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getStartTime() {
		return this.startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return this.endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getUrgency() {
		return this.urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public boolean getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public VectorOfTasksForJob getVecTaskJ() {
		return vecTaskJ;
	}

	public int generateJobNo() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT `jobNumber` FROM `job` WHERE `startTime` = ? AND `startDate` = ? AND `priority` = ? AND `specialInstructions` = ? AND `price` = ? AND `status` = ? AND `CustomeraccountNo` = ? AND `PaymentpaymentID` = ?";
		PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

		preparedStatement.setInt(1, startTime);
		preparedStatement.setInt(2, startDate);
		preparedStatement.setInt(3, urgency);
		preparedStatement.setString(4, summary);
		preparedStatement.setDouble(5, price);
		preparedStatement.setString(6, status);
		preparedStatement.setInt(7, customerid);
		preparedStatement.setInt(8, paymentid);

		ResultSet rs = vecJob.getControl().getControl().getDBC().read(preparedStatement);
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

	public void upload(){
		String sql = "INSERT INTO `job`(`CustomeraccountNo`,`PaymentpaymentID`,`startTime`, `startDate`, `priority`, `specialInstructions`, `price`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)) {
			this.iD = generateJobNo();
			preparedStatement.setInt(1, customerid);
			preparedStatement.setInt(2, paymentid);
			preparedStatement.setInt(3, startTime);
			preparedStatement.setInt(4, startDate);
			preparedStatement.setInt(5, urgency);
			preparedStatement.setString(6, summary);
			preparedStatement.setDouble(7, price);
			preparedStatement.setString(8, status);
			vecJob.getControl().getControl().getDBC().write(preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Job(int customerno, int paymentid, int startTime, int startDate, int urgency, String summary, double price, String status, VectorOfJobs vecJob) {
		this.summary = summary;
		this.startTime = startTime;
		this.urgency = urgency;
		this.vecJob = vecJob;
		this.startDate = startDate;
		this.price = (float) price;
		this.status = status;
		this.customerid = customerno;
		this.paymentid = paymentid;
		upload();
	}

	public Job(int ID, int customerno, int paymentid, int startTime, int startDate, int urgency, String summary, float price, String status){
		this.iD = ID;
		this.customerid = customerno;
		this.paymentid = paymentid;
		this.startTime = startTime;
		this.urgency = urgency;
		this.summary = summary;
		this.price = price;
		this.status = status;
		this.startDate = startDate;
	}


	public void viewJob (int jobID) throws SQLException {

		updateStatus(jobID);

		try {

			String find = "SELECT * FROM Job WHERE jobNumber=" + jobID;
			PreparedStatement stmt = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(find);
			ResultSet result = stmt.executeQuery(find);

			while (result.next()) {

				int CustomerAccountNum = result.getInt(2);
				System.out.println(CustomerAccountNum);

				int PaymentpaymentID = result.getInt(3);
				System.out.println(PaymentpaymentID);

				int startTime= result.getInt(4);
				System.out.println(startTime);

				int startDate= result.getInt(5);
				System.out.println(startDate);

				int priority = result.getInt(6);
				System.out.println(priority);

				String specialInstructions = result.getString(7);
				System.out.println(specialInstructions);

				double price = result.getDouble(8);
				System.out.println(price);

				String status= result.getString(9);
				System.out.println(status);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void updateAllJobs() throws SQLException {
		try {
			String find = "SELECT * FROM job";
			PreparedStatement stmt = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(find);
			ResultSet result = stmt.executeQuery(find);

			while (result.next()) {

				int jobIDTemp = result.getInt(1);
				updateStatus(jobIDTemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void viewActiveJobs() throws SQLException {

		updateAllJobs();

		try {

			PreparedStatement stmt = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement
					("SELECT FROM job WHERE status = 'in progress'");

			ResultSet result = stmt.executeQuery();

			while (result.next()) {

				int JobNumber = result.getInt(1);
				System.out.println(JobNumber);

				int CustomerAccountNum = result.getInt(2);
				System.out.println(CustomerAccountNum);

				int PaymentpaymentID = result.getInt(3);
				System.out.println(PaymentpaymentID);

				int startTime = result.getInt(4);
				System.out.println(startTime);

				int startDate = result.getInt(5);
				System.out.println(startDate);

				int priority = result.getInt(6);
				System.out.println(priority);

				String specialInstructions = result.getString(7);
				System.out.println(specialInstructions);

				double price = result.getDouble(8);
				System.out.println(price);

				String status = result.getString(9);
				System.out.println(status);

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setZero(int statusID){
		statusID = 0;
	}
    
}

/*
 updateStatus checks through the job's tasks and determines whether the job is in progress, pending or complete.
 In update job, setStatus is called to set whatever status thats determined for the job.
 ViewJob is to view a specific job, updateStatus is called to make sure the status is the latest one.
 ViewActiveJobs views all jobs that are active, updateAllJobs is called which updates(checks status etc) all the jobs beforehand
 */