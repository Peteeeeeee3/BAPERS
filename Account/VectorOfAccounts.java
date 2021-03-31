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

	public void addCustAccount(Customer customer) {
		customers.add(customer);
		incrementNoOfCustAccounts();

//		accControl.write("INSERT INTO Customer_Table" + "(company, name, address, phone)" + customer.getAccountNo() +customer.getCompany()+ "has been inserted");
//		System.out.println("Data: " + customer.getCompany() + customer.getName() + customer.getAddress() + customer.getPhone() + "have been inserted");

	}

	public void removeCustAccount(int accountNo) {
		customers.remove(accountNo);
		decrementNoOfCustAccounts();
	}

	public Customer retrieveCustAccount(int accountNo) {
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
		noOfCustAccounts++;
	}

	public void decrementNoOfCustAccounts() {
		noOfCustAccounts--;
	}

	public Vector<Customer> getCustomerVector(){
		return customers;
	}

	public AccountControl getAccControl() {
		return accControl;
	}

	public VectorOfAccounts(AccountControl accountControl) {
		this.accControl = accountControl;
	}

	public Customer searchCustomer(int id) {
		//Database version might need fixing//
		int accountno = 0, phone = 0, valued = 0;
		String name = "", company = "", address = "";

		String sql = "SELECT *" + " FROM customer" + " WHERE accountNo = ?";
		try {
			PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			ResultSet rs;
			preparedStatement.setInt(1, id);
			rs = accControl.getControl().getDBC().getDBGateway().read(preparedStatement);
			while (rs.next()) {
				accountno = rs.getInt("accountNo");
				name = rs.getString("name");
				company = rs.getString("company");
				phone = rs.getInt("phone");
				address = rs.getString("address");
				valued = rs.getInt("valued");
			}

			if (company.equals("") && name.equals("") && address.equals("") && phone == 0 && accountno == 0 && valued == 0) {
				//return null;
			} else {
				return new Customer(company, name, address, phone, this, accountno, valued);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return new Customer(company, name, address, phone, this, accountno, valued);
	}

	public void upgradeCustomer(int customerID) {
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
		String sql = "SELECT accountNo FROM customer WHERE accountNo = ?";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rs = accControl.read(preparedStatement);
		return rs.next();
	}
}