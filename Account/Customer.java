package Account;
import Payment.*;

public class Customer {
	private String company;
	private int accountNo;
	private String name;
	private String address;
	private int phone;
	private VectorOfAccounts vecAcc;
	public VectorOfPayments vecPayment;
	public Payment payment;


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

	public int generateAccountNo(){
		return accountNo=vecAcc.getCustomerID()+1;
	}
    //this is incomplete as we need to link this to a new job being added this will increase the no of payment with a customer.
	public Payment[] listOfPayment(){
		int paymentID= 0;
		int i=0;
		while(i==generateAccountNo()){
			vecPayment.retrievePayment(i);
		}
		return new Payment[accountNo];
	}


	public Customer(String company, String name, String address, int phone) {
		this.company=company;
		this.name=name;
		this.address=address;
		this.phone=phone;
		generateAccountNo();
		listOfPayment();
		this.vecPayment = new VectorOfPayments(this, vecAcc.getAccControl().getControl().getPaymentControl().getVecCard(), vecPayment.getPaymCtrl());
	}
}