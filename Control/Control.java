package Control;

import Account.AccountControl;
import Database.DBControl;

import java.awt.image.PackedColorModel;
import java.sql.SQLException;

public class Control implements I_Control {

	private String state = "";
	private DBControl DBC;
	private AccountControl accountControl;

	public Control() throws ClassNotFoundException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		////Test Login Start (do not remove)////
//		Control controller = new Control();
//		controller.accountControl = new AccountControl();
//		controller.accountControl.addControl(controller);
//		controller.accountControl.login(1, "password");
		////Test Login End////
	}

	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}
}