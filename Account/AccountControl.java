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
	public ValuedCustomer valCustomer;
	public DiscountSet discountSet;
	public Discount discount;

	public UserAccount retrieveUser(int staffID) {
		return vecUser.retrieveUser(staffID);
	}

	public Customer retrieveCustomer(int accountNo) {
		return vecAcc.retrieveCustAccount(accountNo);
	}

	public void createCustomer(String company, String name, String address, int phone) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Customer vecCustomer = null;
		for (int i = 0; i < vecAcc.getCustomerVector().size(); i++) {
			if (vecAcc.getCustomerVector().get(i).getName() == name) {
				vecCustomer = vecAcc.getCustomerVector().get(i);
				break;
			}

		}
		vecAcc.addCustAccount(new Customer(company, name, address, phone));
	}

	public void createUser(int ID, String password, String name, int access) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		UserAccount vecU = null;
		for (int i = 0; i < vecUser.getVector().size(); i++) {
			if (vecUser.getVector().get(i).getStaffID() == ID) {
				vecU = vecUser.getVector().get(i);
				break;
			}
		}
		if (vecU == null) {
			return;
		}
		vecUser.addUser(new UserAccount(ID, password, name, access));
	}

	public void updateAccess(int staffID, int newAccess) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		officeManager.editAccess(staffID, newAccess, retrieveUser(staffID));
	}

	public void upgradeCust(int accountNo) {
		if(customer.getAccountNo()==accountNo){
			customer=valCustomer;
		}
	}

	public void downgradeCust(int accountNo) {
		if(customer.getAccountNo()==accountNo)
			discountSet.removeDiscount(discount);
	}

	public void editDiscount(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void makePayment(Object[] jobs) {
		throw new UnsupportedOperationException();
	}

	public AccountControl(Control ctrl) {
		vecUser = new VectorOfUsers(vecUser, this);
		vecAcc=new VectorOfAccounts(vecAcc,this );
		this.control=ctrl;
	}

	//////// Override Methods ///////

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

	public Control getControl() {
		return control;
	}

	@Override
	public void connectDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

	}

	@Override
	public void disconnectDB() throws SQLException {

	}

	@Override
	public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		return null;
	}

	@Override
	public void write(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

	}
}

