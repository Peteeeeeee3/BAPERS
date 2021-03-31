package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Job.TaskForJob;

public class VectorOfTasksForJob {
	private int noOfTasks = 0;
	private Job job;
	public JobFacadeControl jobControl;
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

	public boolean checkStatus(String status) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT status FROM task_of_job WHERE status = ?";
		PreparedStatement preparedStatement = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		preparedStatement.setString(1, status);
		ResultSet rs = jobControl.getControl().getDBC().read(preparedStatement);
		return rs.next();
	}

	public void setStatus(String status, int TasktaskID) {  //this method is called by updateTask but can also be called manually. It updates the job status in the database.

		try {
			String sql = "UPDATE task_of_job SET status = ? WHERE TasktaskID = ?";
			PreparedStatement stmt = jobControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setInt(2, TasktaskID);
			jobControl.getControl().getDBC().write(stmt);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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

	public VectorOfTasksForJob(JobFacadeControl jobControl) {
		this.jobControl = jobControl;
	}

}