package Payment;

import Account.Customer;
import Job.Job;

import java.sql.SQLException;
import java.text.ParseException;

public interface I_Payment {

	public Payment retrievePayment(int iD);

	public Payment[] retrieveListOfPayments(Customer customer);

	public Card[] retrieveCards(Customer customer);

	public void addPayment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card)
			throws ClassNotFoundException, SQLException, InstantiationException, ParseException, IllegalAccessException;

	public void generateInvoice();
}