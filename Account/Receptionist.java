package Account;

import java.sql.SQLException;

public class Receptionist extends UserAccount {
	public VectorOfAccounts vecAcc;
	public AccountControl accControl;


	public void acceptJob() {
		throw new UnsupportedOperationException();
	}

	public void createCustomer(String company, String name, String address, int phone) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		accControl.createCustomer(company,name,address,phone);
	}

	public void assignTask(int jobID, int taskID) {
		throw new UnsupportedOperationException();
	}

	public Customer searchCustomer(int accountNo) {
		for(int i=0; i<vecAcc.getCustomerVector().size(); ++i ){
			if(vecAcc.getCustomerID()==accountNo){
				accountNo=vecAcc.getCustomerID();
				break;
			}
		}
		return searchCustomer(accountNo);
	}

	public void assignUrgency(int jobID, int urgency) {
		throw new UnsupportedOperationException();
	}

	public void recordDeadline(int deadline) {
		throw new UnsupportedOperationException();
	}

	public void recordSpecialInstruction(String instructions) {
		throw new UnsupportedOperationException();
	}

	public Receptionist(UserAccount user) {
		super(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess());
		throw new UnsupportedOperationException();
	}
}