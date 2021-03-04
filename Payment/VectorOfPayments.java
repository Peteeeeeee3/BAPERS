package Payment;

import Account.Customer;
import java.util.Vector;
import Payment.Payment;

public class VectorOfPayments {
	private Customer customer;
	public PaymentControl unnamed_PaymentControl_;
	public Vector<Payment> payment = new Vector<Payment>();
	public VectorOfCards vecCards;

	public void addPayment(Payment payment) {
		throw new UnsupportedOperationException();
	}

	public Payment retrievePayment(int iD) {
		throw new UnsupportedOperationException();
	}

	public int traverse(int iD) {
		throw new UnsupportedOperationException();
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public VectorOfPayments(Customer customer) {
		throw new UnsupportedOperationException();
	}
}