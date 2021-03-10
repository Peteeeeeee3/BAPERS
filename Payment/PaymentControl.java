package Payment;

import Account.Customer;
import Account.ValuedCustomer;
import Job.Job;
import Control.Control;

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

	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) {
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
		vVecPaym.add(new VectorOfPayments(customer, vecCard));
	}

	public PaymentControl() {
		vecCard = new VectorOfCards();
	}


	public int PaymentToCustomer(int ID, int accountNo){
		return customer.listOfPayment();

	}
}