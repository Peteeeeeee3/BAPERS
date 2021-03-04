package Report;

public class IndividualPerformanceReport extends Report {
	private String name;
	private int date;
	private int startTime;
	private int endTime;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
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

	public IndividualPerformanceReport(String name, int date, int startTime, int endTime) {
		throw new UnsupportedOperationException();
	}
}