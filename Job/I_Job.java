package Job;

import java.sql.SQLException;
import Account.Customer;

public interface I_Job {

	public void addTask(Task task);

	public void removeTask(Task taskID);

	public void addTaskToJob(String location, String description, float price, int duration);

	public void removeTaskFromJob(int jobID, int taskID);

	public void createJobHistory(Customer customer);

	public Job[] retrieveMJobs(int custID);

	public Job retrieveSJob(int jobID);

	public TaskForJob[] retrieveMTasksJ(int jobID);

	public void retrieveSTaskJ(int taskID);

	public Task[] retrieveAvailableTasks();
}