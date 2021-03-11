package Account;

import java.util.Vector;
import Account.Customer;

public class VectorOfAccounts {
	private int noOfCustAccounts = 0;
	public Receptionist staff;
	public AccountControl accControl;
	public Vector<Customer> customer = new Vector<Customer>();

	public void addCustAccount(Customer customer) {
		incrementNoOfCustAccounts();
	}

	public void removeCustAccount(int accountNo) {
		decrementNoOfCustAccounts();
	}

	public Customer retrieveCustAccount(int accountNo) {
		for(int i=0; i<customer.size(); ++i) {
			if(customer.get(i).getAccountNo()==accountNo){
				return customer.get(i);
			}

		}
		return null;
	}

	public int traverseVector(int accountNo) {
		for(int i=0; i<customer.size(); ++i){
			if(customer.get(i).getAccountNo()==accountNo){
				return i;
			}
		}
		return -1;
	}

	public void incrementNoOfCustAccounts() {
		noOfCustAccounts++;
	}

	public void decrementNoOfCustAccounts() {
		noOfCustAccounts--;
	}

	public Vector<Customer> getCustomerVector(){
		return customer;
	}

	public int getCustomerID(){
		int j=0, largest=0;
		while(j<customer.size()){
			if(customer.get(j).getAccountNo()>largest){
				largest=customer.get(j).getAccountNo();
			}
			++j;
		}
		return largest;
	}

	public AccountControl getAccControl() {
		return accControl;
	}

	public VectorOfAccounts(Vector<Customer> customer, AccountControl accountControl) {
		this.customer = customer;
		this.accControl = accountControl;
	}
}