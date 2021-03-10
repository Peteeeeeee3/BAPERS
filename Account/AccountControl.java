package Account;

import Control.Control;
import Control.I_Control;
import Database.I_Database;
import Payment.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AccountControl implements Account.I_Account, I_Database {
	public VectorOfAccounts vecAcc;
	private VectorOfUsers vecUser;
	private Control control;
	public Vector<VectorOfUsers> vecVecUser;

	public UserAccount retrieveUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public Customer retrieveCustomer(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void createCustomer(String company, String name, String address, int phone) {
	}

	public void createUser(int ID, String password, int access) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		//VectorOfUsers vecU = null;
		//for (int i = 0; i < vecVecUser.size(); i++) {
		//	if (vecVecUser.get(i).getLargestID() == ID) {
		//		vecU = vecVecUser.get(i);
	//			break;
	//		}
	//	}
	//	if (vecU == null){
	//		return;
	//	}
		vecUser.addUser(new UserAccount(ID, password, access));
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
	public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return vecUser.login(ID, password);
	}

	@Override
	public void logout() {

	}

	public void updateAccess(int staffID, Object newAccess) {
		throw new UnsupportedOperationException();
	}

	public AccountControl() {
		vecUser = new VectorOfUsers(this);
	}

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
	public ResultSet read(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return control.getDBC().read(sql);
	}

	@Override
	public void write(String sql) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		control.getDBC().write(sql);
	}

}
