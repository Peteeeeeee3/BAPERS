package Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount {
	private int staffID;
	private String password;
	private int access;

	public int getStaffID() {
		return this.staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccess() {
		return this.access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public UserAccount(int id, String password, int access) {
		this.password = password;
		this.access = access;
		this.staffID = id;
	}
}