package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeManager extends ShiftManager {

	private VectorOfUsers vecUser;
	public AccountControl accControl;

	public void editAccess(int staffID, int accessLevel) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		for (int i = 0; i < vecUser.getVector().size(); i++ ){
			switch (accessLevel){
				case 1:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevel + 1;
					}
				case 2:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevel + 1;
					}
				case 3:
					if (vecUser.getVector().get(i).getStaffID() == staffID){
						accessLevel = accessLevel + 1;
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
				editAccess(staffID, accessLevel + 1);
				//accControl.write("UPDATE Staff_Member SET access = " + accessLevel + "WHERE StaffID = " + staffID);
			case 2:
				editAccess(staffID, accessLevel + 1);
				prepStat.setInt(1, accessLevel);
				prepStat.setInt(2, staffID);
			case 3:
				editAccess(staffID, accessLevel + 1);
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
		vecUser.addUser(user);
	}

	public void removeUser(UserAccount user) {
		throw new UnsupportedOperationException();
	}

	public OfficeManager(UserAccount user) {
		super(user);
		throw new UnsupportedOperationException();
	}
}