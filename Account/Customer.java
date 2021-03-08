package Account;

public class Customer {
	private String company;
	private int accountNo;
	private String name;
	private String address;
	private int phone;
	public VectorOfAccounts vecAcc;

	public void addJob() {
		throw new UnsupportedOperationException();
	}

	public void makePayment(Object[] jobs) {
		throw new UnsupportedOperationException();
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Customer(String company, String name, String address, int phone) {
		throw new UnsupportedOperationException();
	}
	//create
}