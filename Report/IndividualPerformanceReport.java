package Report;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndividualPerformanceReport extends Report {
	private Date startDate, endDate;

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	private Date convertDate(Integer oldFormat) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		return originalFormat.parse(oldFormat.toString());
	}

	public IndividualPerformanceReport(int startDate, int endDate, Report report) throws ParseException {
		super(report);
		this.startDate = convertDate(startDate);
		this.endDate = convertDate(endDate);

		try {
			//select all required information
			//SQL Statement doesn't fully work, copy below to test
			//SELECT `staff_member.name`, `completed_tasks.TasktaskID`, `completed_tasks.location`,
			//					`completed_tasks.date`, `completed_tasks.startTime`, `task.duration` 
			//					FROM `completed_tasks`
			//					JOIN `staff_member` ON `staff_member.staffID` = `completed_tasks.Staff_MemberstaffID`
			//					JOIN `task` ON `task.taskID` = `completed_tasks.TasktaskID`
			//					WHERE 2020-12-06 <= `completed_tasks.date` < 2020-12-24;

			String sql1 = "SELECT `staff_member.name`, `completed_tasks.TasktaskID`, `completed_tasks.location`, " +
					"`completed_tasks.date`, `completed_tasks.startTime`, `task.duration` " +
					"FROM `completed_tasks` " +
					"JOIN `staff_member` ON `staff_member.staffID` = `completed_tasks.Staff_MemberstaffID` " +
					"JOIN `task` ON `task.taskID` = `completed_tasks.TasktaskID` " +
					"WHERE ? <= `completed_tasks.date` < ?;";
			//create Prepared statement
			PreparedStatement preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql1);
			//apply dates
			preparedStatement.setDate(1, (java.sql.Date) this.startDate);
			preparedStatement.setDate(2, (java.sql.Date) this.endDate);
			//handle result set

			//continue from here - Peter

			//calculate total times

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}