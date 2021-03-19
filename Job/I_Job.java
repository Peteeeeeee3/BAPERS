package Job;

import Account.Customer;

import java.sql.SQLException;

public interface I_Job {

	public void addTask(int taskID)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void removeTask(int taskID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void addTaskToJob(int jobID, int taskID);

	public void removeTaskFromJob(int jobID, int taskID);

	public void createJobHistory(Customer customer);

	public Job[] retrieveMJobs(int custID);

	public Job retrieveSJob(int jobID);

	public TaskForJob[] retrieveMTasksJ(int jobID);

	public void retrieveSTaskJ(int taskID);

	public Task[] retrieveAvailableTasks();
}