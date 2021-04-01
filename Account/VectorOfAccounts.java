package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Account.Customer;

public class VectorOfAccounts {
	private int noOfCustAccounts = 0;
	public Receptionist staff;
	public AccountControl accControl;
	public Vector<Customer> customers= new Vector<Customer>();

	// allows the user to add a customer and stores it into the vector 'customer'. All the vector values will be added to the local host
	public void addCustAccount(Customer customer) {
		customers.add(customer);
		incrementNoOfCustAccounts(); // once add it will increment the vector size by one

	}
// this allows the user to remove customer from and remove it from the vector
	public void removeCustAccount(int accountNo) {
		customers.remove(accountNo);
		decrementNoOfCustAccounts(); //ensure that it decrements the vector bu 1.
	}


	public Customer retrieveCustAccount(int accountNo) {
		//loops through the vector finds the accountNo value and the one set in customer, once found it retrieves the data.
		for(int i=0; i<customers.size(); ++i) {
			if(customers.get(i).getAccountNo()==accountNo){
				return customers.get(i);
			}
		}
		return null;
	}

	public int traverseVector(int accountNo) {
		for(int i=0; i<customers.size(); ++i){
			if(customers.get(i).getAccountNo()==accountNo){
				return i;
			}
		}
		return -1;
	}

	public void incrementNoOfCustAccounts() {
		noOfCustAccounts++;  // increments the vector
	}

	public void decrementNoOfCustAccounts() {
		noOfCustAccounts--;  //decrement the vector
	}

	public Vector<Customer> getCustomerVector(){
		return customers; //vector that stores all the vector
	}

	public AccountControl getAccControl() {
		return accControl;
	}

	public VectorOfAccounts(AccountControl accountControl) {
		this.accControl = accountControl;  //call function to connect to the account control.
	}

	public Customer searchCustomer(int id) {
		//allows to retrieve all the information regarding the customer and displays it on the screen
		int accountno = 0, valued = 0;
		long phone = 0;
		String name = "", company = "", address = "";

		String sql = "SELECT *" + " FROM customer" + " WHERE accountNo = ?";
		try {
			PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			ResultSet rs;
			preparedStatement.setInt(1, id);
			rs = accControl.getControl().getDBC().getDBGateway().read(preparedStatement); //handle result
			// displays the results.
			while (rs.next()) {
				accountno = rs.getInt("accountNo");
				name = rs.getString("name");
				company = rs.getString("company");
				phone = rs.getLong("phone");
				address = rs.getString("address");
				valued = rs.getInt("valued");
			}

			if (company.equals("") && name.equals("") && address.equals("") && phone == 0 && accountno == 0 && valued == 0) {
				//return null;
			} else {
				return new Customer(company, name, address, phone, this, accountno, valued);
			}
		} catch (Exception e){ // check error.
			e.printStackTrace();
		}
		return new Customer(company, name, address, phone, this, accountno, valued);
	}

	public void upgradeCustomer(int customerID) {
		//retrieves the customer and upgrades the value from 0 to 1, stores in sql host.
		try {
			//Database Update//
			String sql = "UPDATE Customer SET `valued` = ? WHERE `accountNo` = ?";
			PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, customerID);
			accControl.getControl().getDBC().write(preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downgradeCust(int custID) {
		//retrieves customer and downgrades the value from 0 to 1, stores in sql host.
		try {
			//Database Update//
			String sql = "UPDATE Customer SET `valued` = ? WHERE `accountNo` = ?";
			PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, custID);
			accControl.getControl().getDBC().write(preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void defineFlatDiscount(int custID, float rate) {
		// provides a discount value and stores in sql host.
		try {
			String sql = "INSERT INTO Flat_Discount (`rate`) VALUES (?)";
			PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			preparedStatement.setFloat(1, rate);
			accControl.getControl().getDBC().write(preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean checkId(int id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		// retrieves the account number from sql and reads it
		String sql = "SELECT accountNo FROM customer WHERE accountNo = ?";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rs = accControl.read(preparedStatement);
		return rs.next();
	}
}