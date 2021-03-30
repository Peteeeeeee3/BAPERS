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
	//public VectorOfJobs vecJob;

	public void addJob(Job job) {
		vector.add(new Job(job.getID() ,job.getSummary(), job.getStartTime(), job.getUrgency(), this));
		incrementNoOfJobs();

	}

	public Job retrieveJob(int iD) {
		for(int i = 0; i < vector.size(); i++){
			if(vector.get(i).getID() == iD){
				return vector.get(i);
			}
		}
		return null;
	}

	public int traverse(int iD) {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).getID() == iD) {
				return i;
			}
		}
		return -1;
	}

	public void incrementNoOfJobs() {
		noOfJobs ++;
	}

	public void decrementNoOfJobs() {
		noOfJobs --;
	}

	public int getNoOfJobs() {
		return this.noOfJobs;
	}

	public JobFacadeControl getControl(){
		return jobControl;
	}

	public Vector<Job> getVector(){
		return vector;
	}

	public VectorOfJobs(JobFacadeControl jobControl) {
		this.jobControl = jobControl;
	}
}