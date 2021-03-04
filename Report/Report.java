package Report;

public class Report {
	private int time;
	private int reportID;
	private String userName;
	private int timeTaken;
	public ReportFacadeControl RFC;

	public boolean isOnDemand(int reportID) {
		throw new UnsupportedOperationException();
	}

	public boolean isAutomated(int reportID) {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getReportID() {
		return this.reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Report(int time, int reportID, String userName) {
		throw new UnsupportedOperationException();
	}
}