package Account;

public class Receptionist extends UserAccount {
	public VectorOfAccounts vecAcc;

	public void acceptJob() {
		throw new UnsupportedOperationException();
	}

	public void createCustomer() {
		throw new UnsupportedOperationException();
	}

	public void assignTask(int jogID, int taskID) {
		throw new UnsupportedOperationException();
	}

	public Customer searchCustomer(int accountNo) {
		throw new UnsupportedOperationException();
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
		super(user.getPassword(), user.getAccess(), user.getVecUser());
		throw new UnsupportedOperationException();
	}
}