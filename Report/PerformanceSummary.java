package Report;

import java.sql.*;
import java.util.*;

public class PerformanceSummary extends Report {
    private int date;
    private int startDate;
    private int endDate;
    private Vector<Vector<Vector<Integer>>> days = new Vector<>();

    public int getDate() {
        return this.date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public int getStartDate() {
        return this.startDate;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public int getEndDate() {
        return this.endDate;
    }
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
	public Vector<Vector<Vector<Integer>>> getDays() {
		return days;
	}

	public PerformanceSummary(int startDate, int endDate, ReportFacadeControl rfc) {
        super();
        this.RFC = rfc;
        this.startDate = startDate;
        this.endDate = endDate;

        //get the values
        String sql = "SELECT SUM(completed_tasks.duration) " +
                "FROM completed_tasks " +
                "JOIN task ON task.taskID = completed_tasks.TasktaskID " +
                "WHERE completed_tasks.date = ? AND completed_tasks.startTime >= ? AND completed_tasks.endTime <= ? AND task.location = ?";
        String location = "";

        try {
            PreparedStatement preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            //loop query for each day
            for (int dateItr = startDate; dateItr < endDate; dateItr++) {
				days.add(new Vector<Vector<Integer>>());
                //loop locations
                for (int locItr = 0; locItr < 4; locItr++) {
                	days.get(dateItr - startDate).add(new Vector<Integer>());
                    //loop per shift
                    if (locItr == 0) {
                        location = "Copy Room";
                    } else if (locItr == 1) {
                        location = "Development";
                    } else if (locItr == 2) {
                        location = "Finishing";
                    } else {
                        location = "Packing";
                    }
                    for (int shiftItr = 0; shiftItr < 3; shiftItr++) {
                    	//add values
                        if (shiftItr == 0) {
                            preparedStatement.setInt(2, 50001);
                            preparedStatement.setInt(3, 143000);
                        }
                        if (shiftItr == 1) {
							preparedStatement.setInt(2, 143001);
							preparedStatement.setInt(3, 220000);
                        }
                        if (shiftItr == 2) {
							preparedStatement.setInt(2, 220001);
							preparedStatement.setInt(3, 50000);
                        }
						preparedStatement.setDate(1, convertDate(dateItr));
						preparedStatement.setString(4, location);
						//make query
						ResultSet rs = RFC.getControl().getDBC().getDBGateway().read(preparedStatement);
						//handle result set
						while (rs.next()) {
							days.get(dateItr - startDate).get(locItr).add(rs.getInt(1));
						}
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}