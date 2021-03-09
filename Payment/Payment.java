package Payment;

import Account.Customer;
import Job.Job;

public class Payment {
	private int iD;
	private float amount;
	private int date;
	private Customer customer;
	private Job[] jobs;
	private int dueDate;
	public VectorOfPayments vecPaym;
	public Card card;

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

	public int getiD() {
		return iD;
	}

	public Card getCard() {
		return card;
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