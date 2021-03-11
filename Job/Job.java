package Job;

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

	public void assignID() {
		throw new UnsupportedOperationException();
	}

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

	private void upload() throws SQLException{

	}

	public Job(String summary, int startTime, int urgency) {
		this.summary = summary;
		this.startTime = startTime;
		this.urgency = urgency;
	}
}