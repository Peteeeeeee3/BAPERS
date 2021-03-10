package Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Account.UserAccount;

public class VectorOfUsers {
	private int noOfUsers = 0;
	public AccountControl accControl;
	public Vector<UserAccount> vector = new Vector<UserAccount>();

	public void incrementNoOfUsers() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfUsers() {
		throw new UnsupportedOperationException();
	}

	public void addUser(UserAccount user) {
		vector.add(new UserAccount(getLargestID() + 1, user.getPassword(), user.getAccess()));
	}

	public void removeUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public UserAccount retrieveUser(int staffID) {
		throw new UnsupportedOperationException();
	}

	public AccountControl getAccControl() {
		return accControl;
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
		ResultSet rs = accControl.read("SELECT access FROM staff_member WHERE staff_member.staffID = " + ID + " AND staff_member.password = '" + password + "';");
		int access = -1;
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

	public VectorOfUsers(AccountControl accountControl) {
		this.accControl = accountControl;
	}
}