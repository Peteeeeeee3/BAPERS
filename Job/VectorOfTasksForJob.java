package Job;

import java.sql.SQLException;
import java.util.Vector;
import Job.TaskForJob;

public class VectorOfTasksForJob {
	private int noOfTasks = 0;
	private Job job;
	public TaskForJob taskForJob;
	public JobFacadeControl jfc;
	private Vector<TaskForJob> taskJ = new Vector<TaskForJob>();

	public void addTask(TaskForJob task) throws SQLException {
		taskJ.add(new TaskForJob(task));
		incrementNoOfTasks();
	}

	public TaskForJob retrieveTask(int i) {
		return taskJ.get(i);
	}

	public TaskForJob traverse(int taskID) {
		for (int i = 0; i < taskJ.size(); ++i) {
			if (taskJ.get(i).getTaskID() == taskID) {
				return taskJ.get(i);
			}
		}
		return null;
	}

	public int getNoOfTasks() {
		return this.noOfTasks;
	}

	public void incrementNoOfTasks() {
		noOfTasks++;
	}

	public void decrementNoOfTasks() {
		noOfTasks--;
	}

	public Vector<TaskForJob> getVector() {
		return taskJ;
	}


	public JobFacadeControl getControl() {
		return jfc;
	}

	public VectorOfTasksForJob(TaskForJob TaskForJob, JobFacadeControl jfc) {
		this.taskForJob=TaskForJob;
		this.jfc=jfc;
		throw new UnsupportedOperationException();
	}

}