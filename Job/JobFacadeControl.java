package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Job.JobHistory;
import Account.Customer;

import Control.Control;
import Database.I_Database;


public abstract class JobFacadeControl implements I_Job, I_Database {
	public Vector<JobHistory> jobHist = new Vector<JobHistory>();
	public VectorOfTasks vecTasks;
	private VectorOfJobs vecJobs;
	private Control control;

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

	public JobFacadeControl(){
		vecJobs = new VectorOfJobs(vecJobs, this);
	}

	public void addControl(Control control){
		this.control = control;
	}

	@Override
	public void connectDB() throws ClassNotFoundException, SQLException {
	}

	@Override
	public void disconnectDB()throws SQLException{}

	@Override
	public ResultSet read(PreparedStatement sql)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {return control.getDBC().read(sql);}

	@Override
	public void write(PreparedStatement sql)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {control.getDBC().write(sql);}

	public Control getControl(){return control;}

}