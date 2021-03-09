package Account;

import Control.Control;
import Control.I_Control;
import Database.I_Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountControl implements Account.I_Account, I_Database {
	public VectorOfAccounts vecAcc;
	private VectorOfUsers vecUser;
	private Control control;


	public UserAccount retrieveUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public Customer retrieveCustomer(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void createCustomer(String company, String name, String address, int phone) {
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

	@Override
	public boolean login(int ID, String password) throws SQLException {
		return vecUser.login(ID, password);
	}

	@Override
	public void logout() {

	}

	public void updateAccess(int staffID, Object newAccess) {
		throw new UnsupportedOperationException();
	}

	public AccountControl() {}

	public void addControl(Control control) {
		this.control = control;
	}

	@Override
	public void connectDB() throws ClassNotFoundException, SQLException {

	}

	@Override
	public void disconnectDB() throws SQLException {

	}

	@Override
	public ResultSet read(String sql) throws SQLException {
		return control.getDBC().read(sql);
	}

	@Override
	public void write(String sql) throws SQLException {

	}

}
