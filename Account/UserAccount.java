package Account;

public class UserAccount {
	private int staffID;
	private String password;
	private int access;
	private String name;

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

	public String getName(){return this.name;}

	public void setName(String name){this.name = name;}

	public UserAccount(int id, String password, String name, int access) {
		this.password = password;
		this.access = access;
		this.staffID = id;
		this.name = name;
	}
}