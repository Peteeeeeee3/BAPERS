package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Control.Control;
import Job.JobHistory;
import Account.Customer;
import Database.I_Database;


public class JobFacadeControl implements I_Job, I_Database {
	public Vector<JobHistory> jobHist = new Vector<JobHistory>();
	public VectorOfTasks vecTasks;
	public VectorOfJobs vecJobs;
	private Control control;
	public VectorOfTasksForJob tfj;
	public Task task;

	public void addTask(String description, String location, float price, int duration){
		for (int i = 0; i < vecTasks.getVector().size(); ++i) {
			if (vecTasks.getVector().get(i).getDescription().equals(description)) {
				break;
			}
			//another condition is required here or statement can be ignored as it will always happen - Peter
			// this would return the error pop up message.
		}
		vecTasks.addTask(new Task(location, description, price, duration, this.vecTasks));
	}

	public void removeTask(int taskID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		for(int i=0; i<vecTasks.getVector().size(); ++i){
			if(vecTasks.getVector().get(i).generateAccountNo()==taskID){
				vecTasks.removeTask(taskID);
			}

		}
	}


	public void acceptJob(int ID ,String summary, int startTime, int urgency){
		Job job = null;
		for (int i = 0; i < vecJobs.getVector().size(); ++i){
			if (vecJobs.getVector().get(i).getID() == ID){
				job = vecJobs.getVector().get(i);
				break;
			}
			if (job == null){
				return; // the error pop up message
			}
		}
		vecJobs.addJob(new Job(ID, summary, startTime, urgency, vecJobs));
	}

	public void viewTask(int taskID){
		vecTasks.viewTask(taskID);
	}

	public void addTaskToJob(int jobID, int taskID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		TaskForJob taskForJob ;
		for (int i=0; i<vecJobs.getVector().size(); ++i){
			if(vecJobs.getVector().get(i).getID()==jobID){
				for(int j=0; j<vecTasks.getVector().size(); ++j){
					if(vecTasks.getVector().get(i).getTaskID()==taskID){
						vecTasks.addTask(task);
						break;
					}
				}

			}
		}
	}

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

	public JobFacadeControl(Control ctrl){
		this.control = ctrl;
		this.vecTasks = new VectorOfTasks(this);
		vecJobs = new VectorOfJobs(this);
	}

	public Task getTask(){return task;}

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