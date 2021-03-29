
package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Job.Task;

public class VectorOfTasks {
	public JobFacadeControl jfc;
	public VectorOfTasks task;
	public Vector<Task> tasks = new Vector<Task>();
	private int noOfTasks = 0;

	public void addTask(Task task) {
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

	public void viewTask(int taskID){

		String find = "SELECT * FROM Task WHERE taskID=" + taskID;
		try (PreparedStatement preparedStatement = jfc.getControl().getDBC().getDBGateway().getConnection().prepareStatement(find)){
			preparedStatement.setInt(1,taskID);
			ResultSet rs = jfc.getControl().getDBC().read(preparedStatement);
			while (rs.next()){
				String description = rs.getString(2);
				System.out.println(description);
				String location = rs.getString(3);
				System.out.println(location);
				double price = rs.getInt(4);
				System.out.println(price);
				int duration = rs.getInt(5);
				System.out.println(duration);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Task> getVector() {
		return this.tasks;
	}

	public JobFacadeControl getControl(){
		return jfc;
	}

	public VectorOfTasks(JobFacadeControl jfc) {
		this.jfc = jfc;
	}

}


