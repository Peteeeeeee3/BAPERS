package Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount {
	private int staffID;
	private String password;
	private int access;
	public VectorOfUsers vecUser;

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

	public VectorOfUsers getVecUser() {
		return vecUser;
	}

	private void generateID() {
		staffID = vecUser.getLargestID() + 1;
	}

	public UserAccount(String password, int access, VectorOfUsers vecUser) {
		this.password = password;
		this.access = access;
		this.vecUser = vecUser;
		generateID();
	}
}