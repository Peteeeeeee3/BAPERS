package Account;

import java.sql.SQLException;
import java.util.Vector;
import Account.Customer;

public class VectorOfAccounts {
	private int noOfCustAccounts = 0;
	public Receptionist staff;
	public AccountControl accControl;
	public Vector<Customer> customers = new Vector<Customer>();

	public void addCustAccount(Customer customer) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		customers.add(new Customer(customer.getCompany(),customer.getName(),customer.getAddress(),customer.getPhone()));
		incrementNoOfCustAccounts();

		//accControl.write("INSERT INTO Customer_Table" + "(company, name, address, phone)" + customer.getAccountNo() +customer.getCompany()+ "has been inserted");
		System.out.println("Data: " + customer.getCompany() + customer.getName() + customer.getAddress() + customer.getPhone() + "have been inserted");
	}


	public void removeCustAccount(int accountNo) {
		customers.remove(accountNo);
		decrementNoOfCustAccounts();
	}

	public Customer retrieveCustAccount(int accountNo) {
		for(int i=0; i<customers.size(); ++i) {
			if(customers.get(i).getAccountNo()==accountNo){
				return customers.get(i);
			}

		}
		return null;
	}

	public int traverseVector(int accountNo) {
		for(int i=0; i<customers.size(); ++i){
			if(customers.get(i).getAccountNo()==accountNo){
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
		return customers;
	}

	public int getCustomerID(){
		int j=0, largest=0;
		while(j<customers.size()){
			if(customers.get(j).getAccountNo()>largest){
				largest=customers.get(j).getAccountNo();
			}
			++j;
		}
		return largest;
	}

	public VectorOfAccounts(Vector<Customer> customer) {
		this.customers=customer;
	}
}