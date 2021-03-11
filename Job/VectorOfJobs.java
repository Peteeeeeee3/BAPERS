package Job;

import java.util.Vector;
import Job.Job;

public class VectorOfJobs {
	private int noOfJobs = 0;
	public JobHistory jHist;
	public Vector<Job> jobs = new Vector<Job>();

	public void addJob(Job job) {
	}

	public Job retrieveJob(int iD) {
		throw new UnsupportedOperationException();
	}

	public int traverse(int iD) {
		throw new UnsupportedOperationException();
	}

	public void incrementNoOfJobs() {
		noOfJobs++;
	}

	public void decrementNoOfJobs() {
		noOfJobs--;
	}

	public int getNoOfJobs() {
		return this.noOfJobs;
	}

	public Vector<Job> getJobVector(){
		return jobs;
	}

	public VectorOfJobs() {
		throw new UnsupportedOperationException();
	}
}