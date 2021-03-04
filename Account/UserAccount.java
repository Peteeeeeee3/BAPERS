package Account;

public class UserAccount {
	private int staffID;
	private String password;
	private int access;
	public VectorOfUsers vecUser;

	public void login(String username, String password) {
		throw new UnsupportedOperationException();
	}

	public void logout() {
		throw new UnsupportedOperationException();
	}

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

	public UserAccount(String password, int access) {
		throw new UnsupportedOperationException();
	}
}