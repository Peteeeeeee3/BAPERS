package Account;

import Job.Job;
import Job.VectorOfJobs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValuedCustomer extends Customer {
	public DiscountSet discSet;
	public VectorOfJobs vecJob;
	public Job job;

	public void addDiscountPlan(String type, float[] rates, int[] tasks, float[] ranges) {
		//check type
		if (type.equals("flat")) {
			//check if rates contains values
			if (rates.length == 0) return;
			//add a singular flat discount to the set
			//use only the first value of rates as only one value will be required and this will be contained in the first position
			discSet.addDiscount(new Discount(rates[0]));
		}
		if (type.equals("task")) {
			//check if rates and tasks is the same length and whether they contain values (checking one is enough)
			if (rates.length != tasks.length || rates.length == 0) return;
			//for every task add a task discount
			for (int i = 0; i < tasks.length; i++) {
				//task IDs and rates will be stores in the same locations
				discSet.addDiscount(new TaskDiscount(tasks[i], rates[i]));
			}
		}
		if (type.equals("band")) {
			//check whether the are an even number of values contained in ranges,
			//whether rates is half the length of ranges
			//and if they both are not of length 0
			if (ranges.length % 2 != 0 || rates.length != ranges.length / 2 || ranges.length == 0 || rates.length == 0) return;
			//keep track of ranges
			int rangeItr = 0;
			//for every rate add one band
			for (int rateItr = 0; rateItr < rates.length; rateItr++) {
				//ranges are stored: min, max, min, max, min, max etc.
				discSet.addDiscount(new DiscountBand(ranges[rangeItr], ranges[rangeItr + 1], rates[rateItr]));
				rangeItr += 2;
			}
		}
	}

	public void alertManager() throws SQLException {

	}

	public DiscountSet getDiscSet() {
		return discSet;
	}

	public ValuedCustomer(Customer customer, VectorOfAccounts vectorOfAccounts) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		super(customer.getCompany(), customer.getName(), customer.getAddress(), customer.getPhone(), vectorOfAccounts);
		discSet = new DiscountSet();
	}
}