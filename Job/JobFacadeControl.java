package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Control.Control;
import Job.JobHistory;
import Account.Customer;
import Database.I_Database;


public abstract class JobFacadeControl implements I_Job, I_Database {
	public Vector<JobHistory> jobHist = new Vector<JobHistory>();
	public VectorOfTasks vecTasks;
	private VectorOfJobs vecJobs;
	private Control control;

	public void addTask(int taskID, String location, String description, float price, int duration) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Task task = null;
		for (int i = 0; i < vecTasks.getVector().size(); ++i) {
			if (vecTasks.getVector().get(i).getTaskID() == taskID) {
				task = vecTasks.getVector().get(i);
				break;
			}
			//another condition is required here or statement can be ignored as it will always happen - Peter
			if (task == null) {
				return;
			}
		}
		vecTasks.addTask(new Task(taskID, task.getLocation(), task.getDescription(), task.getPrice(), task.getDuration()));
	}

	public void removeTask(Task taskID){
	}


	public void acceptJob(int ID ,String summary, int startTime, int urgency){
		Job job = null;
		for (int i = 0; i < vecJobs.getVector().size(); ++i){
			if (vecJobs.getVector().get(i).getID() == ID){
				job = vecJobs.getVector().get(i);
				break;
			}
			if (job == null){
				return;
			}
		}
		vecJobs.addJob(new Job(ID, summary, startTime, urgency));
	}



	public void addTaskToJob(String location, String description, float price, int duration){}

	public void removeTaskFromJob(int jobID, int taskID){}

	public void createJobHistory(Customer customer){}

	public Job[] retrieveMJobs(int custID){
		throw new UnsupportedOperationException();
	}

	public Job retrieveSJob(int jobID){
		throw new UnsupportedOperationException();
	}

	public  TaskForJob[] retrieveMTasksJ(int jobID){
		throw new UnsupportedOperationException();
	}

	public void retrieveSTaskJ(int taskID){}

	public Task[] retrieveAvailableTasks(){
		throw new UnsupportedOperationException();
	}

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