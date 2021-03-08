package Account;

import java.sql.SQLException;

public interface I_Account {

	public UserAccount retrieveUser(int staffID);

	public Customer retrieveCustomer(int accountNo);

	public void createCustomer(String company, String name, String address, int phone);

	public void updateAccess(int staffID, Object newAccess);

	public void upgradeCust(int accountNo);

	public void downgradeCust(int accountNo);

	public void editDiscount(int accountNo);

	public void makePayment(Object[] jobs);

	public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

	public void logout();
}