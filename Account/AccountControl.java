package Account;

import Control.Control;
import Database.I_Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AccountControl implements Account.I_Account, I_Database {
	public VectorOfAccounts vecAcc;
	private VectorOfUsers vecUser;
	private Control control;
	public Vector<VectorOfUsers> vecVecUser;
	public OfficeManager officeManager;
	public Customer customer;

	public UserAccount retrieveUser(int staffID) {
		return vecUser.retrieveUser(staffID);
	}

	public Customer retrieveCustomer(int accountNo) {
		return vecAcc.retrieveCustAccount(accountNo);
	}

	public void createCustomer(String company, String name, String address, int phone)  {
		Customer vecCustomer =null;
		for( int i=0; i<vecAcc.getCustomerVector().size(); i++){
			if(vecAcc.getCustomerVector().get(i).getName()== name){
				vecCustomer=vecAcc.getCustomerVector().get(i);
				break;
			}

		}
		vecAcc.addCustAccount(new Customer(company,name,address,phone));
	}

	public void createUser(int ID, String password, String name, int access) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		UserAccount vecU = null;
		for (int i = 0; i < vecUser.getVector().size(); i++) {
			if (vecUser.getVector().get(i).getStaffID() == ID) {
				vecU = vecUser.getVector().get(i);
				break;
			}
		}
		if (vecU == null){
			return;
		}
		vecUser.addUser(new UserAccount(ID, password, name, access));
	}

	public void updateAccess(int staffID, int newAccess) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		officeManager.editAccess(staffID,newAccess, retrieveUser(staffID));
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
		vecUser = new VectorOfUsers(vecUser, this);
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

	public void addUserVector(UserAccount user){
		vecVecUser.add(new VectorOfUsers(vecUser, this));
	}

	@Override
	public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return control.getDBC().read(sql);
	}

	@Override
	public void write(PreparedStatement sql) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		control.getDBC().write(sql);
	}

	public Control getControl() {
		return control;
	}
}
