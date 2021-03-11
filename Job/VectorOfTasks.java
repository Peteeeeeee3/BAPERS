package Job;

import java.sql.SQLException;
import java.util.Vector;
import Job.Task;

public class VectorOfTasks {
	private int noOfTasks = 0;
	public JobFacadeControl jfc;
	public Vector<Task> tasks = new Vector<Task>();

	public void addTask(Task task) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		tasks.add(new Task(generateTaskID(), task.getLocation(),task.getDescription(),task.getPrice(),task.getDuration()));
		incrementNoOfTasks();

		jfc.write("INSET INTO Task_Table" + "(taskID, location, desrciption, price, duration" + task.getTaskID() + task.getLocation() + task.getDescription() + task.getPrice() + task.getDuration() + ")");
		System.out.println("Data: " + task.getTaskID() + task.getLocation() + task.getDescription() + task.getPrice() + task.getDuration());
	}

	public void removeTask(int taskID) {
		tasks.remove(taskID);
		decrementNoOfTasks();
	}

	public int traverse(int taskID) {
		for( int i=0; i<tasks.size(); ++i){
			if(tasks.get(i).getTaskID()==taskID){
				return i;
			}
		}
		return -1;
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

	public int generateTaskID(){
		int i=0, largest=0;
		while(i<tasks.size()){
			if(tasks.get(i).getTaskID()<largest){
				largest=tasks.get(i).getTaskID();
			}
			++i;

		}
		return largest;
	}

	public Vector<Task> getVector(){
		return this.tasks;
	}

	public VectorOfTasks(VectorOfTasks task, JobFacadeControl jfc) {
		this.tasks=tasks;
		this.jfc=jfc;
	}
}