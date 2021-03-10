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
		noOfUsers++;
	}

	public void decrementNoOfUsers() {
		noOfUsers--;
	}

	public void addUser(UserAccount user) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		vector.add(new UserAccount(getLargestID() + 1, user.getPassword(), user.getAccess()));
		//Not sure if we want to use the one above or the one below. Thinking of having a different addUser for createUser
		//vector.add(user);
		incrementNoOfUsers();

		accControl.write("INSERT INTO Staff_Member" + "VALUES (" + user.getStaffID() + user.getPassword() + user.getAccess() + ")");
		System.out.println(user.getStaffID() + user.getPassword() + user.getAccess());
		}



	public void removeUser(int staffID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		vector.remove(staffID);
		decrementNoOfUsers();

		accControl.write("DELETE FROM Staff_Member WHERE staffID =" + staffID);
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