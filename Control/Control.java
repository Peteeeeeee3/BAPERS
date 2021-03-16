package Control;

import Account.AccountControl;
import Account.Customer;
import Database.DBControl;
import Job.Job;
import Job.JobFacadeControl;
import Payment.I_Payment;
import Payment.Payment;
import Payment.PaymentControl;
import Payment.Card;

import java.sql.SQLException;
import java.text.ParseException;

public class Control implements I_Control, I_Payment {

	private String state = "";
	private DBControl DBC;
	private AccountControl accountControl;
	private PaymentControl paymentControl;
	private JobFacadeControl jobControl;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl();
		controller.accountControl.addControl(controller);
		controller.paymentControl = new PaymentControl(controller);

		////Test Login Start (do not remove)////
		//controller.accountControl.login(6, "password6");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("Oxford","Hanan","32 oxford street",755551315);

		//Test for Create User//
		controller.accountControl.createUser(8, "password1", "farhan", 1);
	}

	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}

	public AccountControl getAccountControl() {
		return accountControl;
	}

	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}

	public PaymentControl getPaymentControl() {
		return paymentControl;
	}

	@Override
	public Payment retrievePayment(int iD) {
		return null;
	}

	@Override
	public Payment[] retrieveListOfPayments(Customer customer) {
		return new Payment[0];
	}

	@Override
	public Card[] retrieveCards(Customer customer) {
		return new Card[0];
	}

	@Override
	public void generateInvoice() {

	}

	@Override
	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) throws ClassNotFoundException, SQLException, InstantiationException, ParseException, IllegalAccessException {
		paymentControl.addPayment(amount, date, customer, jobs, dueDate, card);
	}
}