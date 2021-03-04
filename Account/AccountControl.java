package Account;

public class AccountControl implements I_Account {
	public VectorOfAccounts vecAcc;
	public VectorOfUsers vecUser;

	public UserAccount retrieveUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public Customer retrieveCustomer(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void createCustomer(String company, String name, String address, int phone) {
		throw new UnsupportedOperationException();
	}

	public void createUser(String password, int access) {
		throw new UnsupportedOperationException();
	}

	public void updateAccess(int staffID, int newAccess) {
		throw new UnsupportedOperationException();
	}

	public void upgradeCust(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void downgradeCust(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void editDiscount(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void makePayment(Object[] jobs) {
		throw new UnsupportedOperationException();
	}

	public AccountControl() {
		throw new UnsupportedOperationException();
	}

	public void updateAccess(int staffID, Object newAccess) {
		throw new UnsupportedOperationException();
	}
}