package Job;

import java.util.Vector;
import Job.JobHistory;
import Account.Customer;

public abstract class JobFacadeControl implements I_Job {
	public Vector<JobHistory> jobHist = new Vector<JobHistory>();
	public VectorOfTasks vecTasks;

	public abstract void addTask(Task task);

	public abstract void removeTask(Task taskID);

	public abstract void addTaskToJob(String location, String description, float price, int duration);

	public abstract void removeTaskFromJob(int jobID, int taskID);

	public abstract void createJobHistory(Customer customer);

	public abstract Job[] retrieveMJobs(int custID);

	public abstract Job retrieveSJob(int jobID);

	public abstract TaskForJob[] retrieveMTasksJ(int jobID);

	public abstract void retrieveSTaskJ(int taskID);

	public abstract Task[] retrieveAvailableTasks();

	public JobFacadeControl() {
		throw new UnsupportedOperationException();
	}
}