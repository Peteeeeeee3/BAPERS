package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class VectorOfUsers {
	private UserAccount user;
	public VectorOfUsers vecUser;
	private int noOfUsers = 0;
	public AccountControl accControl;
	public Vector<UserAccount> vector = new Vector<UserAccount>();


	public void incrementNoOfUsers() {
		noOfUsers++;
	}

	public void decrementNoOfUsers() {
		noOfUsers--;
	}

	public void addUser(UserAccount user) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		vector.add(new UserAccount(getLargestID() + 1, user.getPassword(), user.getName(), user.getAccess()));
		//vector.add(user);
		incrementNoOfUsers();

		//accControl.write("INSERT INTO Staff_Member " + "(staffID, password, name, access) " + " VALUES (" + user.getStaffID() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getAccess() + ")");
		System.out.println("Data: " + user.getStaffID() + user.getPassword() + user.getName() + user.getAccess() + "has been inserted");
		}



	public void removeUser(int staffID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		vector.remove(staffID);
		decrementNoOfUsers();

		//accControl.write("DELETE FROM Staff_Member WHERE staffID =" + staffID + ";");
	}

	public UserAccount retrieveUser(int staffID) {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).getStaffID() == staffID) {
				return vector.get(i);
			}
		}
		return null;
	}

	public AccountControl getAccControl() {
		return accControl;
	}

	public int traverse(int staffID) {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).getStaffID() == staffID) {
				return i;
			}
		}
		return -1;
	}

	public int getLargestID() {
		//retrieves the largest ID in the vector
		//used to generate staffID
		int i = 0, largest = 0;
		while (i < vector.size()) {
			if (vector.get(i).getStaffID() > largest) {
				largest = vector.get(i).getStaffID();
			}
			++i;
		}
		return largest;
	}

	public Vector<UserAccount> getVector() {
		return vector;
	}

	//returns true if matching data is found and false if none is found
	public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		////local data version////
//		for (int i = 0; i < vector.size(); i++) {
//			if (vector.get(i).getStaffID() == ID && vector.get(i).getPassword() == password) {
//				return true;
//			}
//		}
//		return false;

		////database version////
		String sql = "SELECT access FROM staff_member WHERE staff_member.staffID = ? AND staff_member.password = ?";
		PreparedStatement prepStat = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		//ResultSet rs = accControl.read("SELECT access FROM staff_member WHERE staff_member.staffID = " + ID + " AND staff_member.password = '" + password + "';");
		prepStat.setInt(1, ID);
		prepStat.setString(2, password);
		//System.out.println(prepStat.toString());
		ResultSet rs = accControl.read(prepStat);
		int access = -1;
		//handle result set
		while (rs.next()) {
			access = rs.getInt(1);
		}
		System.out.println(access + " " + ID + " " + password);
		switch (access) {
			case 1:
				return true;
			case 2:
				return true;
			case 3:
				return true;
			case 4:
				return true;
		}
		return false;
	}

	public void logout() {
		throw new UnsupportedOperationException();
	}

	public VectorOfUsers(VectorOfUsers vecUser, AccountControl accountControl) {
		this.vecUser = vecUser;
		this.accControl = accountControl;
	}

}