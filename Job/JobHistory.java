package Job;

import Account.Customer;

public class JobHistory {
	private Customer customer;
	public JobFacadeControl jfc;
	public VectorOfJobs vecJob;

	public boolean isActive(int iD) {
		throw new UnsupportedOperationException();
	}

	public void addJob(Job job) {
		vecJob.addJob(job);
	}

	public void updateStatus(int iD) {
		throw new UnsupportedOperationException();
	}

	public void updateTaskStatus(int jobID, int taskID) {
		throw new UnsupportedOperationException();
	}

	public JobHistory(Customer customer) {
		this.customer = customer;
	}
}