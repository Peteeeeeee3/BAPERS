package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeManager extends ShiftManager {

	private VectorOfUsers vecUser;
	public AccountControl accControl;

	public void editAccess(int staffID, UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
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

	public void upgradeCustomer(int customerID) {
		accControl.upgradeCust(customerID);
	}

	public void defineBands(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void downgradeCust(int custID) {
		accControl.downgradeCust(custID);
	}

	public void defineFlatDiscount(int custID, float rate) {
		throw new UnsupportedOperationException();
	}

	public void createUser(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		accControl.createUser(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess());

		//Database Version//
		String sql = "INSERT INTO Staff_Member (`staffid`, `password`, `name`, `access`) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, user.getStaffID());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setInt(4, user.getAccess());
	}

	public void removeUser(UserAccount user) {
		;
	}

	public OfficeManager(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		super(user);
		createUser(user);
		editAccess(user.getStaffID(), user);
	}
}