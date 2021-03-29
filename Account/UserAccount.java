package Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount {
	private int staffID;
	private String password;
	private int access;
	private String name;
	private VectorOfUsers vecUser;


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

	public VectorOfUsers getVecUser(){return vecUser;}

	public int generateAccountNo() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT `staffid` FROM `Staff_Member` WHERE Staff_Member.staffid = ? AND Staff_Member.password = ? AND Staff_Member.name = ? AND Staff_Member.access=?";
		PreparedStatement preparedStatement = vecUser.getAccControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

		preparedStatement.setInt(1, staffID);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, password);
		preparedStatement.setInt(4, access);

		ResultSet rs = vecUser.getAccControl().getControl().getDBC().read(preparedStatement);
		//Find the largest ID, this indicates this is the newest object and hence belongs to this one
		// as this function only gets called in the constructor
		int finalValue = -1, checkValue = -1;
		//get values from result set
		while (rs.next()) {
			//apply value to correct variable
			if (finalValue == -1) {
				finalValue = rs.getInt(1);
			} else {
				checkValue = rs.getInt(1);
			}
			//see if the check value is larger than the final value (which will be returned)
			if (checkValue > finalValue) {
				finalValue = checkValue;
			}
		}
		return finalValue;
	}

	public void upload(){
		String sql = "INSERT INTO `Staff_Member`(`password`, `name`, `access`) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = vecUser.getAccControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)) {
			this.staffID = generateAccountNo();
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, access);
			vecUser.getAccControl().getControl().getDBC().write(preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserAccount(String password, String name, int access, VectorOfUsers vecUser) {
		this.password = password;
		this.access = access;
		this.name = name;
		this.vecUser = vecUser;
		upload();
	}
}
