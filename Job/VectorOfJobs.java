package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Account.AccountControl;
import Job.Job;
import java.util.Vector;

public class VectorOfJobs {
	private int noOfJobs = 0;
	public JobHistory jHist;
	public Vector<Job> vector = new Vector<Job>();
	public JobFacadeControl jobControl;
	public VectorOfJobs vecJob;

	public void addJob(Job job) {
		vector.add(job);
		incrementNoOfJobs();

	}

	public Job retrieveJob(int iD) {
		for(int i = 0; i < vector.size(); i++){
			if(vector.get(i).getID() == iD){
				return vector.get(i);
			}
		}
		return null;
	}

	public int traverse(int iD) {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).getID() == iD) {
				return i;
			}
		}
		return -1;
	}

	public Job viewJob (int jobID){
		int id = 0, CustomerAccountNum = 0, PaymentpaymentID = 0, startTime = 0, startDate = 0, priority = 0;
		String specialInstructions = "", status = "";
		float price = 0;

		//updateStatus(jobID);

		try {

			String find = "SELECT * FROM Job WHERE jobNumber= ?";
			PreparedStatement stmt = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(find);
			ResultSet result;
			stmt.setInt(1, jobID);
			result = jobControl.getControl().getDBC().read(stmt);

			while (result.next()) {

				id = result.getInt("jobNumber");

				CustomerAccountNum = result.getInt("CustomeraccountNo");
				System.out.println(CustomerAccountNum);

				PaymentpaymentID = result.getInt("PaymentpaymentID");
				System.out.println(PaymentpaymentID);

				startTime= result.getInt("startTime");
				System.out.println(startTime);

				startDate= result.getInt("startDate");
				System.out.println(startDate);

				priority = result.getInt("priority");
				System.out.println(priority);

				specialInstructions = result.getString("specialInstructions");
				System.out.println(specialInstructions);

				price = result.getFloat("price");
				System.out.println(price);

				status= result.getString("status");
				System.out.println(status);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Job(id, CustomerAccountNum, PaymentpaymentID, startTime, startDate, priority, specialInstructions, price, status);
	}

	public void setStatus(String status, int jobID) {  //this method is called by updateTask but can also be called manually. It updates the job status in the database.

		try {
			String sql = "UPDATE Job SET status = ? WHERE jobNumber = ?";
			PreparedStatement stmt = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setInt(2, jobID);
			jobControl.getControl().getDBC().write(stmt);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkStatus(String status) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT status FROM job WHERE status = ?";
		PreparedStatement preparedStatement = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setString(1, status);
		ResultSet rs = jobControl.getControl().getDBC().read(preparedStatement);
		return rs.next();
	}

	public boolean checkJobID(int id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT status FROM task_of_job WHERE status = ?";
		PreparedStatement preparedStatement = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rs = jobControl.getControl().getDBC().read(preparedStatement);
		return rs.next();
	}

	public void incrementNoOfJobs() {
		noOfJobs ++;
	}

	public void decrementNoOfJobs() {
		noOfJobs --;
	}

	public int getNoOfJobs() {
		return this.noOfJobs;
	}

	public JobFacadeControl getControl(){
		return jobControl;
	}

	public Vector<Job> getVector(){
		return vector;
	}

	public VectorOfJobs(JobFacadeControl jobControl) {
		this.jobControl = jobControl;
	}
}