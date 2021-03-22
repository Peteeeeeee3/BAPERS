package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Job {
	private int iD;
	private int price;
	private String summary;
	private String status;
	private int startTime;
	private int endTime;
	private int urgency = 24;
	private boolean isPaid = false;
	public VectorOfTasksForJob vecTaskJ;
	public VectorOfJobs vecJob;



	public int calculatePrice() {
		throw new UnsupportedOperationException();
	}

	public int calcEstTime() {
		throw new UnsupportedOperationException();
	}

	public void updateStatus() {
		throw new UnsupportedOperationException();
	}

	public int getID() {
		return this.iD;
	}

	public int getPrice() {
		return this.price;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		String sql = "SELECT `jobNumber` FROM `Job` WHERE Job.deadline = ? AND Job.priority = ? AND Job.price = ? AND Job.specialInstruction = ?";
		PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

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

	private void upload() throws SQLException{

	}

	public Job(int ID, String summary, int startTime, int urgency) {
		this.iD = ID;
		this.summary = summary;
		this.startTime = startTime;
		this.urgency = urgency;
	}
}