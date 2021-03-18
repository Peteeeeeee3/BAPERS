package Report;

public class CustomerJobReport extends Report {
	private String customerName;

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public CustomerJobReport(String customerName, Report report) {
		super();
	}
}