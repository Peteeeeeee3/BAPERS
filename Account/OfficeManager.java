package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeManager extends ShiftManager {

	private VectorOfUsers vecUser;
	public AccountControl accControl;

	public void editAccess(int staffID, int accessLevel, UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		int accessLevelRec = 1;
		int accessLevelTech = 2;
		int accessLevelSM = 3;
		int accessLevelOM = 4;
		for (int i = 0; i < vecUser.getVector().size(); i++ ){
			switch (accessLevel){
				case 1:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevelTech;
						user.setAccess(accessLevel);
					}
				case 2:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevelSM;
						user.setAccess(accessLevel);
					}
				case 3:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevelOM;
						user.setAccess(accessLevel);
					}
			}
		}

		//Database//
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement("SELECT access FROM staff_member WHERE staff_member.staffID = ?");
		preparedStatement.setInt(1, staffID);
		ResultSet rs = accControl.read(preparedStatement);
		int access = -1;
		while (rs.next()){
			access = rs.getInt(1);
		}
		PreparedStatement prepStat = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement("UPDATE staff_member SET access = ? WHERE staff_member.staffID = ?");
		switch(access){
			case 1:
				editAccess(staffID, accessLevel, user);
				//accControl.write("UPDATE Staff_Member SET access = " + accessLevel + "WHERE StaffID = " + staffID);
			case 2:
				editAccess(staffID, accessLevel, user);
				prepStat.setInt(1, accessLevel);
				prepStat.setInt(2, staffID);
			case 3:
				editAccess(staffID, accessLevel, user);
				prepStat.setInt(1, accessLevel);
				prepStat.setInt(2, staffID);
		}
	}


	public void backupSystem() {
		throw new UnsupportedOperationException();
	}

	public void restoreSystem() {
		throw new UnsupportedOperationException();
	}

	public void upgradeCustomer(int customerID, String discountType) {
		throw new UnsupportedOperationException();
	}

	public void defineBands(int accountNo) {
		throw new UnsupportedOperationException();
	}

	public void downgradeCust(int custID) {
		throw new UnsupportedOperationException();
	}

	public void defineFlatDiscount(int custID, float rate) {
		throw new UnsupportedOperationException();
	}

	public void createUser(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		accControl.createUser(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess());

		//Database Version//
		String sql = "INSERT INTO staff_member (`staffid`, `password`, `name`, `access`) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, user.getStaffID());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setInt(4, user.getAccess());
	}

	public void removeUser(UserAccount user) {
		throw new UnsupportedOperationException();
	}

	public OfficeManager(UserAccount user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		super(user);
		createUser(user);
		editAccess(user.getStaffID(), user.getAccess(), user);
	}
}