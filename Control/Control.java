package Control;

import Account.AccountControl;
import Database.DBControl;

import java.sql.SQLException;

public class Control implements I_Control {

	private String state = "";
	private DBControl DBC;
	private AccountControl accountControl;

	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}


	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl();
		controller.accountControl.addControl(controller);

		////Test Login Start (do not remove)////
		controller.accountControl.login(6, "password6");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("Oxford","Hanan","32 oxford street",07555513157);
	}
}