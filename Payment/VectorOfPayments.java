package Payment;

import Account.Customer;
import java.util.Vector;

public class VectorOfPayments {
	private Customer customer;
	private Vector<Payment> vecPaym = new Vector<Payment>();
	private VectorOfCards vecCards;
	private PaymentControl paymCtrl;

	public void addPayment(Payment payment) {

		vecPaym.add(payment);
		if (payment.getCard() != null) {
			vecCards.addCard(payment.getCard());
		}
	}

	public Payment retrievePayment(int iD) {
		for (int i = 0; i < vecPaym.size(); i++) {
			if (vecPaym.get(i).getiD() == iD) {
				return vecPaym.get(i);
			}
		}
		return null;
	}

	public int traverse(int iD) {
		for (int i = 0; i < vecPaym.size(); i++) {
			if (vecPaym.get(i).getiD() == iD) {
				return i;
			}
		}
		return -1;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void vectorOfPayments(Customer customer) {
		this.customer = customer;
	}

	public PaymentControl getPaymCtrl() {
		return paymCtrl;
	}

	public VectorOfPayments(Customer customer, VectorOfCards vecCards, PaymentControl paymCtrl) {
		this.customer = customer;
		this.vecCards = vecCards;
		this.paymCtrl = paymCtrl;
	}
}