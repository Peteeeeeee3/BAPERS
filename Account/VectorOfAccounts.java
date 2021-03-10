package Account;

import java.util.Vector;
import Account.Customer;

public class VectorOfAccounts {
	private int noOfCustAccounts = 0;
	public Receptionist staff;
	public AccountControl accControl;
	public Vector<Customer> customer = new Vector<Customer>();

	public void addCustAccount(Object customer) {
		throw new UnsupportedOperationException();
	}

	public void removeCustAccount(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public Customer retrieveCustAccount(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public Customer traverseVector(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void incrementNoOfCustAccounts() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfCustAccounts() {
		throw new UnsupportedOperationException();
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

	public VectorOfAccounts(Vector<Customer> customer) {
		this.customer=customer;
		throw new UnsupportedOperationException();
	}
}