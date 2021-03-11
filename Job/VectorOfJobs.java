package Job;

import java.util.Vector;

import Account.AccountControl;
import Job.Job;
import java.util.Vector;

public class VectorOfJobs {
	private int noOfJobs = 0;
	public JobHistory jHist;
	public Vector<Job> vector = new Vector<Job>();
	public JobFacadeControl jobControl;
	public VectorOfJobs vecJob;

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
	}

	public void decrementNoOfJobs() {
	}



	public int getNoOfJobs() {
		return this.noOfJobs;
	}

	public JobFacadeControl getJobControl(){
		return jobControl;
	}

	public Vector<Job> getVector(){
		return vector;
	}

	public VectorOfJobs(VectorOfJobs vecJob, JobFacadeControl jobControl) {
		this.vecJob = vecJob;
		this.jobControl = jobControl;
	}
}