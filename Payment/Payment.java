package Payment;

import Account.*;
import Job.Job;

public class Payment {
	private int iD;
	private float amount;
	private int date;
	private Customer customer;
	private Job[] jobs;
	private int dueDate;
	public VectorOfPayments vecPaym;
	private Card card;

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isOnTime() {
		throw new UnsupportedOperationException();
	}

	public Job[] getJobs() {
		return this.jobs;
	}


	public Card getCard() {
		return card;
	}

	private float calculateCost(float amount, Customer customer) {
		//check whether customer is valued and has a discount
		if (customer.getClass() == ValuedCustomer.class && !((ValuedCustomer) customer).getDiscSet().getDiscounts().isEmpty()) {
			//process flat discount
			if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getClass() == Discount.class) {
				return amount * (1 - ((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getDiscountRate());
			}
			//process banded discount
			if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getClass() == DiscountBand.class) {
				//find correct band
				for (int bandItr = 0; bandItr < ((ValuedCustomer) customer).getDiscSet().getDiscounts().size(); bandItr++) {
					//get correct band (this needs to be changed to previous month's expenses
					if (((DiscountBand)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr)).getRange_min() <= amount
							&& amount < ((DiscountBand)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr)).getRange_max()) {
						//apply discount for band
						return amount * (1 - ((DiscountBand)((ValuedCustomer) customer).getDiscSet().getDiscounts().get(bandItr)).getDiscountRate());
					}
				}
			}
			//process task discount
			if (((ValuedCustomer) customer).getDiscSet().getDiscounts().get(0).getClass() == TaskDiscount.class) {
				//enter code here
			}
		}
		return amount;
	}

	public Payment(float amount, int date, Customer customer, Job[] jobs, int dueDate) {
		this.amount = amount;
		this.date = date;
		this.customer = customer;
		this.jobs = jobs;
		this.dueDate = dueDate;
	}

	public Payment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) {
		this.amount = amount;
		this.date = date;
		this.customer = customer;
		this.jobs = jobs;
		this.dueDate = dueDate;
		this.card = card;
	}
}