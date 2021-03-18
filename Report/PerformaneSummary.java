package Report;

public class PerformaneSummary extends Report {
	private int date;
	private int startDate;
	private int endDate;

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getStartDate() {
		return this.startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return this.endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public PerformaneSummary(int date, int startDate, int endDate, Report report) {
		super();
		throw new UnsupportedOperationException();
	}
}