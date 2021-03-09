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

	public Payment(float amount, int date, Customer customer, Job[] jobs, int dueDate) {
		throw new UnsupportedOperationException();
	}

	public static void Payment2(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) {
		throw new UnsupportedOperationException();
	}
}