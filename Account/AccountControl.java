package Account;

public class AccountControl implements Account.I_Account {
	public Account.VectorOfAccounts vecAcc;
	public Account.VectorOfUsers vecUser;

	public Account.UserAccount retrieveUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public Account.Customer retrieveCustomer(int accountNo) {
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
	//test
}