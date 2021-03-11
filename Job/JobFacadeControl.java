package Job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Account.OfficeManager;
import Account.VectorOfAccounts;
import Account.VectorOfUsers;
import Control.Control;
import Job.JobHistory;
import Account.Customer;

public abstract class JobFacadeControl implements I_Job {
	public Vector<JobHistory> jobHist = new Vector<JobHistory>();
	public VectorOfTasks vecTasks;
	private Control control;

	public void addTask(int taskID, String location, String description, float price, int duration) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Task task=null;
		for(int i=0; i<vecTasks.getVector().size();++i){
			if(vecTasks.getVector().get(i).getTaskID()==taskID){
				task=vecTasks.getVector().get(i);
				break;
			}
			if(task==null){
				return;
			}
		}
		vecTasks.addTask(new Task(taskID, task.getLocation(),task.getDescription(),task.getPrice(),task.getDuration()));

	}

	public void removeTask(int taskID){
		Task task=null;
		for(int i=0; i<vecTasks.getVector().size();++i) {
			if (vecTasks.getVector().get(i).getTaskID() == taskID) {
				vecTasks.removeTask(taskID);
				break;
			}
		}
	}

	public void addTaskToJob(String location, String description, float price, int duration){}

	public  void removeTaskFromJob(int jobID, int taskID){}

	public void createJobHistory(Customer customer){}

	public Job[] retrieveMJobs(int custID){
		throw new UnsupportedOperationException();
	}

	public Job retrieveSJob(int jobID){
		throw new UnsupportedOperationException();

	}

	public TaskForJob[] retrieveMTasksJ(int jobID){
		throw new UnsupportedOperationException();

	}

	public void retrieveSTaskJ(int taskID){}

	public Task[] retrieveAvailableTasks(){
		throw new UnsupportedOperationException();
	}

	public JobFacadeControl() {
		vecTasks= new VectorOfTasks(vecTasks,this);
	}

	public void addControl(Control control) {
		this.control = control;
	}


	public void connectDB() throws ClassNotFoundException, SQLException {

	}


	public void disconnectDB() throws SQLException {

	}


	public ResultSet read(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return control.getDBC().read(sql);
	}


	public void write(String sql) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		control.getDBC().write(sql);
	}




}