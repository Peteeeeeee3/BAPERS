package Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;

public class IndividualPerformanceReport extends Report {
	private int startDate, endDate;
	private Vector<String> names = new Vector<>(), locations = new Vector<>();
	private Vector<Integer> staffIDs = new Vector<>(), taskIDs = new Vector<>(), durations = new Vector<>(), totalTimes = new Vector<>();
	private Vector<Date> dates = new Vector<>();
	private Vector<Time> startTimes = new Vector<>();

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
	public Vector<Integer> getTotalTimes() {
		return totalTimes;
	}
	public Vector<Date> getDates() {
		return dates;
	}
	public Vector<Time> getStartTimes() {
		return startTimes;
	}
	public int getStartDate() {
		return startDate;
	}
	public int getEndDate() {
		return endDate;
	}

	private Date convertDate(Integer oldFormat) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date utilDate = originalFormat.parse(oldFormat.toString());
		return new java.sql.Date(utilDate.getTime());
	}

	private void handleResultSet(ResultSet rs) {
		try {
			while (rs.next()) {
				names.add(rs.getString(1));
				staffIDs.add(rs.getInt(2));
				taskIDs.add(rs.getInt(3));
				locations.add(rs.getString(4));
				dates.add(rs.getDate(5));
				startTimes.add(rs.getTime(6));
				durations.add(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IndividualPerformanceReport(int startDate, int endDate, ReportFacadeControl rfc) throws ParseException {
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
					"WHERE completed_tasks.date >= ? AND completed_tasks.date <= ?";
			//create Prepared statement
			PreparedStatement preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql1);
			//apply dates
			preparedStatement.setDate(1, convertDate(this.startDate));
			preparedStatement.setDate(2, convertDate(this.endDate));
			//handle result set
			ResultSet rs = RFC.getControl().getDBC().getDBGateway().read(preparedStatement);
			handleResultSet(rs);


			//calculate total times

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}