package Job;

import java.util.Vector;
import Job.Task;

public class VectorOfTasks {
	private int noOfTasks = 0;
	public JobFacadeControl jfc;
	public Vector<Task> task = new Vector<Task>();

	public void addTask(Task task) {
		throw new UnsupportedOperationException();
	}

	public void removeTask(int taskID) {
		throw new UnsupportedOperationException();
	}

	public int traverse(int taskID) {
		throw new UnsupportedOperationException();
	}

	public void incrementNoOfTasks() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfTasks() {
		throw new UnsupportedOperationException();
	}

	public int getNoOfTasks() {
		return this.noOfTasks;
	}

	public VectorOfTasks() {
		throw new UnsupportedOperationException();
	}
}