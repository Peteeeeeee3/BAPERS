package Payment;

import Account.Customer;
import Job.Job;

public interface I_Payment {

	public Payment retrievePayment(int iD);

	public Payment[] retrieveListOfPayments(Customer customer);

	public Card[] retrieveCards(Customer customer);

	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate);

	public void generateInvoice();
}