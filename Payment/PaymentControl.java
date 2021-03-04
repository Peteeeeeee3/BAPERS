package Payment;

import Account.Customer;
import Job.Job;

public class PaymentControl implements I_Payment {
	public VectorOfPayments unnamed_VectorOfPayments_;
	public VectorOfCards unnamed_VectorOfCards_;

	public Payment retrievePayment(int iD) {
		throw new UnsupportedOperationException();
	}

	public Payment[] retrieveListOfPayments(Customer customer) {
		throw new UnsupportedOperationException();
	}

	public Card[] retrieveCards(Customer customer) {
		throw new UnsupportedOperationException();
	}

	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) {
		throw new UnsupportedOperationException();
	}

	public void generateInvoice() {
		throw new UnsupportedOperationException();
	}

	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate) {
		throw new UnsupportedOperationException();
	}
}