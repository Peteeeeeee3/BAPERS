package Account;

import java.sql.SQLException;

public interface I_Account {

	public UserAccount retrieveUser(int staffID);

	public Customer retrieveCustomer(int accountNo);

	public void createCustomer(String company, String name, String address, int phone) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void updateAccess(int staffID, int newAccess);

	public void upgradeCust(int accountNo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void downgradeCust(int accountNo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void editDiscount(int accountNo);

	public void makePayment(Object[] jobs);

	public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

	public void logout();
}