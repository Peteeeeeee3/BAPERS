package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeManager extends ShiftManager {

	private VectorOfUsers vecUser;
	public AccountControl accControl;
	private VectorOfAccounts vecAcc;
	public Discount disc;
	public DiscountBand discB;
	public Customer cust;
	public ValuedCustomer valCust;
	public UserAccount user;

	public void editAccess(int staffID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		int accessLevelRec = 1;
		int accessLevelTech = 2;
		int accessLevelSM = 3;
		int accessLevelOM = 4;
		for (int i = 0; i < vecUser.getVector().size(); i++ ){
			if (user.getStaffID() == staffID && user.getAccess() == accessLevelRec){
				user.setAccess(accessLevelTech);
			}
			if (user.getStaffID() == staffID && user.getAccess() == accessLevelTech){
				user.setAccess(accessLevelSM);
			}
			if (user.getStaffID() == staffID && user.getAccess() == accessLevelSM){
				user.setAccess(accessLevelOM);
			}
		}

		//Database//
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement("SELECT access FROM staff_member WHERE staff_member.staffID = ?");
		preparedStatement.setInt(1, staffID);
		ResultSet rs = accControl.read(preparedStatement);

		PreparedStatement prepStat = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement("UPDATE staff_member SET access = ? WHERE staff_member.staffID = ?");
		if (user.getStaffID() == staffID && user.getAccess() == accessLevelRec){
			prepStat.setInt(1, staffID);
			prepStat.setInt(2, accessLevelTech);
		}
		if (user.getStaffID() == staffID && user.getAccess() == accessLevelTech){
			prepStat.setInt(1, staffID);
			prepStat.setInt(2, accessLevelSM);
		}
		if (user.getStaffID() == staffID && user.getAccess() == accessLevelSM){
			prepStat.setInt(1, staffID);
			prepStat.setInt(2, accessLevelOM);
		}
		vecUser.getAccControl().getControl().getDBC().write(preparedStatement);
	}


	public void backupSystem() {
		throw new UnsupportedOperationException();
	}

	public void restoreSystem() {
		throw new UnsupportedOperationException();
	}

	public void upgradeCustomer(int customerID) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		accControl.upgradeCust(customerID);

		//Database Update//
		String sql = "UPDATE Customer SET `valued` = ? WHERE `accountNo` = ?";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, 1);
		preparedStatement.setInt(2, cust.getAccountNo());
		accControl.getControl().getDBC().write(preparedStatement);

	}

	public void defineBands(int accountNo, float maxrange, float minrange) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		float difference = 0;
		for(int i = 0; i < vecAcc.getCustomerVector().size(); ++i){
			if (vecAcc.getCustomerVector().get(i).getAccountNo() == accountNo){
				discB.setRange_max(maxrange);
				discB.setRange_min(minrange);
			}
			difference = discB.getRange_max() - discB.getRange_min();
		}
		String sql = "INSERT INTO Discount_Band (`volume`) VALUES (?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, (int) difference);
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public void downgradeCust(int custID) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		accControl.downgradeCust(custID);

		//DatabaseUpdate//
		String sql = "UPDATE Customer SET `valued` = ? WHERE `accountNo` = ?";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, 0);
		preparedStatement.setInt(2, cust.getAccountNo());
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public void defineFlatDiscount(int custID, float rate) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		for(int i = 0; i < vecAcc.getCustomerVector().size(); ++ i){
			if (vecAcc.getCustomerVector().get(i).getAccountNo() == custID){
				disc.setDiscountRate(rate);
			}
		}
		String sql = "INSERT INTO Flat_Discount (`rate`) VALUES (?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setFloat(1, disc.getDiscountRate());
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public void createUser(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		vecUser.addUser(user);

		//Database Version//
		String sql = "INSERT INTO Staff_Member (`staffid`, `password`, `name`, `access`) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, user.getStaffID());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setInt(4, user.getAccess());
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public void removeUser(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		vecUser.removeUser(user.getStaffID());

		String sql = "DELETE FROM Staff_Member (`staffid`, `password`, `name`, `access`) WHERE (?, ?, ?, ?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, user.getStaffID());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setInt(4, user.getAccess());
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public OfficeManager(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		super(user);
		createUser(user);
		editAccess(user.getStaffID());
	}
}