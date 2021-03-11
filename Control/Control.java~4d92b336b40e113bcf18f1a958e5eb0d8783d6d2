package Control;

import Account.AccountControl;
import Database.DBControl;
import Job.JobFacadeControl;
import Payment.PaymentControl;

import java.sql.SQLException;

public class Control implements I_Control {

	private String state = "";
	private DBControl DBC;
	private AccountControl accountControl;
	private JobFacadeControl jobControl;
	private PaymentControl paymentControl;

	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}

	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}

	public void setJobContol (JobFacadeControl jobContol){
		this.jobControl=jobContol;

	}


	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl();
		controller.accountControl.addControl(controller);
		controller.jobControl.addControl(controller);
		controller.paymentControl = new PaymentControl();



		////Test Login Start (do not remove)////
		//controller.accountControl.login(6, "password6");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("Oxford","Hanan","32 oxford street",755551315);

		//Test for Create User//

		//controller.accountControl.createUser(1, "password1", "farhan", 1);

		//Test for Create Task//
		//controller.jobControl.addTask(003,"Printer Room", "print in colour", 12, 2);



		//controller.accountControl.createUser(8, "password1", "farhan", 1);
	}
}