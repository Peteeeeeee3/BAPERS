package Job;

import java.util.Vector;
import Job.TaskForJob;

public class VectorOfTasksForJob {
	private int noOfTasks = 0;
	public Job job;
	public Vector<TaskForJob> taskJ = new Vector<TaskForJob>();

	public void addTask(TaskForJob task) {
		throw new UnsupportedOperationException();
	}

	public TaskForJob retrieveTask() {
		throw new UnsupportedOperationException();
	}

	public TaskForJob traverse(int taskID) {
		throw new UnsupportedOperationException();
	}

	public int getNoOfTasks() {
		return this.noOfTasks;
	}

	public void incrementNoOfTasks() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfTasks() {
		throw new UnsupportedOperationException();
	}

	public VectorOfTasksForJob() {
		throw new UnsupportedOperationException();
	}
}