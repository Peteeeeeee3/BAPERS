package Account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OfficeManager extends ShiftManager {

	private VectorOfUsers vecUser;
	public AccountControl accControl;
	private VectorOfAccounts vecAcc;
	public DiscountBand discB;
	public Customer cust;
	public ValuedCustomer valCust;
	public UserAccount user;

	public void editAccess(int staffID, int newAccessLevel) {
		for (int i = 0; i < vecUser.getVector().size(); i++ ){
			if (user.getStaffID() == staffID){
				user.setAccess(newAccessLevel);
			}
		}
		//Database//
		try(PreparedStatement prepStat = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement("UPDATE Staff_Member SET access = ? WHERE Staff_Member.staffID = ?")) {
			prepStat.setInt(1, staffID);
			prepStat.setInt(2, newAccessLevel);
			accControl.getControl().getDBC().write(prepStat);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void backupSystem() {
		throw new UnsupportedOperationException();
	}

	public void restoreSystem() {
		throw new UnsupportedOperationException();
	}

	public void defineBands(int accountNo, float maxrange, float minrange) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		float difference = 0;
		for (int i = 0; i < vecAcc.getCustomerVector().size(); ++i) {
			if (vecAcc.getCustomerVector().get(i).getAccountNo() == accountNo) {
				discB.setRange_max(maxrange);
				discB.setRange_min(minrange);
			}
			difference = discB.getRange_max() - discB.getRange_min();
		}
		String sql = "INSERT INTO Discount_Band (`volume`) VALUES (?)";
		PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, (int) difference);
		accControl.getControl().getDBC().write(preparedStatement);
	}

	public void createUser(UserAccount user) {
		vecUser.addUser(user);
		//Database Version//
		String sql = "INSERT INTO Staff_Member (`staffid`, `password`, `name`, `access`) VALUES (?, ?, ?, ?)";
		try(PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)){
			preparedStatement.setInt(1, user.getStaffID());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setInt(4, user.getAccess());
			accControl.getControl().getDBC().write(preparedStatement);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public OfficeManager(UserAccount user) {
		super(user);
		createUser(user);
		editAccess(user.getStaffID(), user.getAccess());
	}
}