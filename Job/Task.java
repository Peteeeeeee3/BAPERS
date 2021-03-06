package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.*;


public class Task {
	private int taskID;
	private String location;
	private String description;
	private float price;
	private int duration;
	public VectorOfTasks vecTask;
    


    public int currentTaskID;

	public int getTaskID() {
		return this.taskID;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int generateAccountNo() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		String sql = "SELECT `taskID` FROM `Task` WHERE Task.duration = ? AND Task.location = ? AND Task.price = ? AND Task.taskDescription=?";
		PreparedStatement preparedStatement = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

		preparedStatement.setInt(1, duration);
		preparedStatement.setString(2, location);
		preparedStatement.setFloat(3, price);
		preparedStatement.setString(4, description);

		ResultSet rs = vecTask.getControl().getControl().getDBC().read(preparedStatement);
		//Find the largest ID, this indicates this is the newest object and hence belongs to this one
		// as this function only gets called in the constructor
		int finalValue = -1, checkValue = -1;
		//get values from result set
		while (rs.next()) {
			//apply value to correct variable
			if (finalValue == -1) {
				finalValue = rs.getInt(1);
			} else {
				checkValue = rs.getInt(1);
			}
			//see if the check value is larger than the final value (which will be returned)
			if (checkValue > finalValue) {
				finalValue = checkValue;
			}
		}
		return finalValue;
	}

	public void upload(){
		String sql = "INSERT INTO Task (`location`, `taskDescription`, `price`, `duration`) VALUES (?,?,?,?)";    //insert statement by passing through all the parameters
		try(PreparedStatement prepStat = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)){
			this.taskID = generateAccountNo();
			prepStat.setString(1, location);  //assigning each parameter to a column
			prepStat.setString(2, description);
			prepStat.setDouble(3, price);
			prepStat.setInt(4, duration);
			vecTask.getControl().getControl().getDBC().getDBGateway().write(prepStat);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public Task (String location, String description, float price, int duration, VectorOfTasks vecTask) {
		this.location=location;
		this.description=description;
		this.price=price;
		this.duration=duration;
		this.vecTask = vecTask;
		upload();
	}

	public Task(int id, String description, String location, float price, int duration){
		this.taskID = id;
		this.location = location;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

    public void insertTask(String taskDescription, String location, double price, int duration)
	{

		// these parameters will be applied through the gui

		try {
			String insert = "INSERT INTO Task(`taskDescription`, `location`, price, duration)" + "VALUES(?, ?, ?, ?)";
			// sql insert statement created
			// parameters are defined before due to autoincrement taking up the first position. so its defined to start from taskDescripton which is the second position in the table


			PreparedStatement stmt = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(insert);
			// prepared statement created through the database gateway


			stmt.setString(1, taskDescription);
			stmt.setString(2, location);
			stmt.setDouble(3, price);
			stmt.setInt(4, duration);

			// each specific index is given its corresponding parameter.

			stmt.executeUpdate();
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void editTask(String taskDescription, String location, double price, int duration) {
		try {
			String query = "UPDATE Task SET taskDescription = ? , location = ? , price = ? , duration = ? WHERE taskID = ?";     //assigning ? values from sql statement to parameters to be entered
			PreparedStatement stmt = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(query);
			stmt.setString(1, taskDescription); //assigning the parameters to each
			stmt.setString(2, location);
		    stmt.setDouble(3, price);
			stmt.setInt(4, duration);

     		stmt.executeUpdate();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTask(int taskID){
		try{ 											//to delete a task in the task database

			PreparedStatement stmt = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement("DELETE FROM Task WHERE taskID = ?");  //delete
			stmt.executeUpdate();
			stmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}