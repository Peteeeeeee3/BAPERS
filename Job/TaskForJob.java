package Job;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Database.*;


public class TaskForJob extends Task {
	private int posInVec;
	private boolean isDelayed = false;
	private boolean isComplete = false;
	public VectorOfTasksForJob vecTaskJ;
    
    

	public void updateTask() {
		throw new UnsupportedOperationException();
	}

	public void alertManager() {
		throw new UnsupportedOperationException();
	}

	public boolean delayCheck() {
		throw new UnsupportedOperationException();
	}

	public void onDelay() {
		throw new UnsupportedOperationException();
	}

	public int getPosInVec() {
		return this.posInVec;
	}

	public void setPosInVec(int posInVec) {
		this.posInVec = posInVec;
	}

	public boolean getIsDelayed() {
		return this.isDelayed;
	}

	public void setIsDelayed(boolean isDelayed) {
		this.isDelayed = isDelayed;
	}

	public boolean getIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	//public void upload() {
	//	String sql = "INSERT INTO Task_of_Job (`TasktaskID`, `status`, `JobjobNo` ) VALUES (?, ?, ? );";
	//		try(PreparedStatement prepStat = vecTaskJ.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)){
	//			prepStat.setString(1, Tasktas);
	//			prepStat.setString(2, description);
	//			prepStat.setFloat(3, price);
	//			prepStat.setInt(4, duration);
	//			vecTaskJ.getControl().getControl().getDBC().getDBGateway().write(prepStat);
	//		} catch (Exception e){
	//			e.printStackTrace();
	//		}
	//}


	public TaskForJob(Task task) throws SQLException {
		super(task.getLocation(), task.getDescription(), task.getPrice(), task.getDuration(), task.vecTask);
	}
    
    public void completeTask(int tasktaskID, int jobjobID, int staffMemberstaffID, double startTime, double timeOfCompletion, String date ) throws SQLException {

		String task_status_complete = "complete";

		try {

			String update = "UPDATE Task_of_Job SET status=" + task_status_complete + "WHERE JobjobNumber =" + jobjobID + "AND TasktaskID =" + tasktaskID;
			PreparedStatement stmt = db.getConnection().prepareStatement(update);
			stmt.executeUpdate();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}


		try {
			String insert = "INSERT INTO Completed_Tasks VALUES(?, ?, ?, ?, ?)";
			PreparedStatement stmt2 = db.getConnection().prepareStatement(insert);
			stmt2.setInt(1, tasktaskID);
			stmt2.setInt(2, staffMemberstaffID);
			stmt2.setDouble(3, startTime);
			stmt2.setDouble(4, timeOfCompletion);
			stmt2.setString(5, date);

			stmt2.executeUpdate();
			stmt2.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}