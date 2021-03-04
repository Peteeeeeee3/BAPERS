package Job;

import java.util.Vector;
import Job.Job;

public class VectorOfJobs {
	private int noOfJobs = 0;
	public JobHistory jHist;
	public Vector<Job> job = new Vector<Job>();

	public void addJob(Job job) {
		throw new UnsupportedOperationException();
	}

	public Job retrieveJob(int iD) {
		throw new UnsupportedOperationException();
	}

	public int traverse(int iD) {
		throw new UnsupportedOperationException();
	}

	public void incrementNoOfJobs() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfJobs() {
		throw new UnsupportedOperationException();
	}

	public int getNoOfJobs() {
		return this.noOfJobs;
	}

	public VectorOfJobs() {
		throw new UnsupportedOperationException();
	}
}