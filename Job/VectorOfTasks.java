
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

	public Task viewTask(int taskID){

		String location = "", description = "";
		int duration = 0, id = 0;
		float price = 0;

		String find = "SELECT * FROM Task WHERE taskID = ?";
		try (PreparedStatement preparedStatement = jfc.getControl().getDBC().getDBGateway().getConnection().prepareStatement(find)){
			preparedStatement.setInt(1,taskID);
			ResultSet rs = jfc.getControl().getDBC().read(preparedStatement);
			while (rs.next()){
				id = rs.getInt(1);
				description = rs.getString("taskDescription");
				System.out.println(description);
				location = rs.getString("location");
				System.out.println(location);
				price = rs.getInt("price");
				System.out.println(price);
				duration = rs.getInt("duration");
				System.out.println(duration);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Task(id, description, location, price, duration);
	}

	public void deleteTask(int taskID){
		try{
			PreparedStatement stmt = jfc.getControl().getDBC().getDBGateway().getConnection().prepareStatement("DELETE FROM task WHERE taskID = ?");
			stmt.setInt(1, taskID);
			jfc.getControl().getDBC().getDBGateway().write(stmt);
		}
		catch (Exception e) {
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


