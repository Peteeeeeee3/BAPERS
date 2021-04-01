package Payment;

import Account.Customer;
import Account.DiscountBand;
import Account.ValuedCustomer;
import Job.Job;
import Control.Control;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

public class PaymentControl implements I_Payment {
	//public VectorOfPayments unnamed_VectorOfPayments_;
	//public VectorOfCards unnamed_VectorOfCards_;
	public Customer customer;
	public Vector<VectorOfPayments> vVecPaym;
	public VectorOfCards vecCard;
	private Control control;

	public Payment retrievePayment(int iD) {
		throw new UnsupportedOperationException();
	}

	public Payment[] retrieveListOfPayments(Customer customer) {
		 throw new UnsupportedOperationException();
	}

	public Card[] retrieveCards(Customer customer) {
		throw new UnsupportedOperationException();
	}

	public void generateInvoice() {
		throw new UnsupportedOperationException();
	}

	public float applyDiscount (float price, Customer customer) {
		if (customer.getValued() == 1) {
			if (customer.getDiscountSet().getDiscounts().get(0) instanceof DiscountBand) {
				return 0f;
			}
		} else {
			return price;
		}
		return price;
	}

	public void addPayment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card)
			throws ClassNotFoundException, SQLException, InstantiationException, ParseException, IllegalAccessException {
		VectorOfPayments vecp = null;
		float price;
		//find correct payment vector
		for (int i = 0; i < vVecPaym.size(); i++) {
			if (vVecPaym.get(i).getCustomer() == customer) {
				vecp = vVecPaym.get(i);
				break;
			}
		}
		//check if a vector of payments has been found and applied
		if (vecp == null) {
			//prompt error if not and end function
			//add code for an error prompt
			return;
		}
		//check for valued customer
		if (customer.getClass() == ValuedCustomer.class) {
			//check for discount type
		}

		//check whether the payment contains a card
		if (card != null) {
			//add new payment with card to vector
			vecp.addPayment(new Payment(amount, date, customer, jobs, dueDate, card));
		} else {
			//add new payment without card to vector
			vecp.addPayment(new Payment(amount, date, customer, jobs, dueDate));
		}
	}

	public void addPaymentVectorToCustomer(Customer customer) {
		vVecPaym.add(new VectorOfPayments(customer, vecCard, this));
	}

	public VectorOfCards getVecCard() {
		return vecCard;
	}

	public Control getControl() {
		return control;
	}

	public PaymentControl(Control ctrl) {
		vecCard = new VectorOfCards();
		this.control = ctrl;
	}
}