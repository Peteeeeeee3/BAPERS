
package Job;

import java.sql.SQLException;
import java.util.Vector;
import Job.Task;

public class VectorOfTasks {
	public JobFacadeControl jfc;
	public VectorOfTasks task;
	public Vector<Task> tasks = new Vector<Task>();
	private int noOfTasks = 0;

	public void addTask(Task task) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		tasks.add(task);
		incrementNoOfTasks();
		//System.out.println("Data: " + task.getTaskID() + task.getLocation() + task.getDescription() + task.getPrice() + task.getDuration() + "have been inserted");

	}

	public void removeTask(int taskID) {
		tasks.remove(taskID);
		decrementNoOfTasks();
	}

	public Task traverse(int taskID) {
		for (int i = 0; i < tasks.size(); ++i) {
			if (tasks.get(i).getTaskID() == taskID) {
				return tasks.get(i);
			}
		}
		return null;

	}

	public void incrementNoOfTasks() {
		noOfTasks++;

	}

	public void decrementNoOfTasks() {
		noOfTasks--;
	}

	public int getNoOfTasks() {
		return this.noOfTasks;
	}


	public Vector<Task> getVector() {
		return this.tasks;
	}

	public JobFacadeControl getControl(){
		return jfc;
	}

	public VectorOfTasks(VectorOfTasks task, JobFacadeControl jfc) {
		this.task = task;
		this.jfc = jfc;
	}

}


