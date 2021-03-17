package Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
	private int taskID;
	private String location;
	private String description;
	private float price;
	private int duration;
	public VectorOfTasks vecTask;

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

	public void upload() throws SQLException {
		String sql = "INSERT INTO Task (`loaction`, `description`, `price`, `duration` ) VALUES (?, ?, ?, ?);";
		PreparedStatement prepStat = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
		//write to DB
		vecTask.getControl().getControl().getDBC().getDBGateway().write(prepStat);
	}

	public Task (String location, String description, float price, int duration) throws SQLException {
		this.location=location;
		this.description=description;
		this.price=price;
		this.duration=duration;
		upload();
	}
}