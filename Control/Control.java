package Control;

import Account.AccountControl;
import Database.DBControl;
import Payment.PaymentControl;

import java.sql.SQLException;

public class Control implements I_Control {

	private String state = "";
	private DBControl DBC;
	private AccountControl accountControl;
	private PaymentControl paymentControl;

	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl();
		controller.accountControl.addControl(controller);
		controller.paymentControl = new PaymentControl();


		////Test Login Start (do not remove)////
		//controller.accountControl.login(1, "asd");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("Oxford","Hanan","32 oxford street",07555513157);

		//Test for Create User//
		controller.accountControl.createUser(1, "asd", 1);


	}
}