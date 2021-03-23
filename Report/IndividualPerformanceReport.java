package Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class IndividualPerformanceReport extends Report {
	private int startDate, endDate;
	private Vector<String> names = new Vector<>(), locations = new Vector<>();
	private Vector<Integer> staffIDs = new Vector<>(), taskIDs = new Vector<>(), durations = new Vector<>();
	private Vector<Integer> dates = new Vector<>();
	private Vector<Integer> startTimes = new Vector<>();
	private HashMap<String, Integer> totalTimes = new HashMap<>();

	public Vector<String> getNames() {
		return names;
	}
	public Vector<String> getLocations() {
		return locations;
	}
	public Vector<Integer> getStaffIDs() {
		return staffIDs;
	}
	public Vector<Integer> getTaskIDs() {
		return taskIDs;
	}
	public Vector<Integer> getDurations() {
		return durations;
	}
	public Vector<Integer> getDates() {
		return dates;
	}
	public Vector<Integer> getStartTimes() {
		return startTimes;
	}
	public HashMap<String, Integer> getTotalTimes() {
		return totalTimes;
	}
	public int getStartDate() {
		return startDate;
	}
	public int getEndDate() {
		return endDate;
	}



	private void handleResultSet(ResultSet rs) {
		try {
			while (rs.next()) {
				names.add(rs.getString(1));
				staffIDs.add(rs.getInt(2));
				taskIDs.add(rs.getInt(3));
				locations.add(rs.getString(4));
				dates.add(rs.getInt(5));
				startTimes.add(rs.getInt(6));
				durations.add(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//sort the tasks into the correct order
		for (int i = 0; i < names.size(); i++) {

		}
	}

	private void calculateTotalTimes() {
		HashSet<String> usedNames = new HashSet<>();

		//add all of the times for the same name and check name has not yet been used
		//add total to map once final

		//loop through to check names
		for (String name : names) {
			int total = 0;
			//only add if this name has not been used
			if (!usedNames.contains(name)) {
				//loop to add values
				for (int innerItr = 0; innerItr < durations.size(); innerItr++) {
					//check if its the same person
					if (names.get(innerItr).equals(name)) {
						//add to total
						total += durations.get(innerItr);
					}
				}
				//add pair to map
				totalTimes.put(name, total);
				//add name to set
				usedNames.add(name);
			}
		}
	}

	public IndividualPerformanceReport(int startDate, int endDate, ReportFacadeControl rfc) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.RFC = rfc;

		try {
			//select all required information and handle totals locally
			String sql1 = "SELECT staff_member.name, staff_member.staffID, completed_tasks.TasktaskID, task.location, completed_tasks.date, " +
					"completed_tasks.startTime, task.duration " +
					"FROM completed_tasks " +
					"JOIN staff_member ON staff_member.staffID = completed_tasks.Staff_MemberstaffID " +
					"JOIN task ON task.taskID = completed_tasks.TasktaskID " +
					"WHERE completed_tasks.date >= ? AND completed_tasks.date <= ? " +
					"ORDER BY staff_member.name ASC";
			//create Prepared statement
			PreparedStatement preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql1);
			//apply dates
//			preparedStatement.setDate(1, convertDate(this.startDate));
//			preparedStatement.setDate(2, convertDate(this.endDate));
			preparedStatement.setInt(1, startDate);
			preparedStatement.setInt(2, endDate);
			//handle result set
			ResultSet rs = RFC.getControl().getDBC().getDBGateway().read(preparedStatement);
			handleResultSet(rs);
			calculateTotalTimes();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}