package Report;

import java.sql.*;
import java.util.*;

public class PerformanceSummary extends Report {
    private int date;
    private int startDate;
    private int endDate;
    private Vector<SummaryInfo> info_vec = new Vector<SummaryInfo>();

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
    public Vector<SummaryInfo> getInfo_vec() {
        return info_vec;
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

        try {
            PreparedStatement preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            //loop query for each day
            ResultSet rs;
            String location = "";
            for (int date = startDate; date <= endDate; date++) {
                preparedStatement.setInt(1, date);
                for (int loc = 0; loc < 4; loc++) {
                    if (loc == 0) {
                        location = "Copy Room";
                    } else if (loc == 1) {
                        location = "Development";
                    } else if (loc == 2) {
                        location = "Finishing";
                    } else {
                        location = "Packing";
                    }
                    preparedStatement.setString(4,location);
                    for (int shift = 0; shift < 3; shift++) {
                        if (shift == 0) {
                            preparedStatement.setInt(2, 501);
                            preparedStatement.setInt(3, 1430);
                        } else if (shift == 1) {
                            preparedStatement.setInt(2, 1431);
                            preparedStatement.setInt(3, 2200);
                        } else {
                            preparedStatement.setInt(2, 2201);
                            preparedStatement.setInt(3, 500);
                        }
                        rs = RFC.getControl().getDBC().getDBGateway().read(preparedStatement);

                        while (rs.next()) {
                            info_vec.add(new SummaryInfo(location, rs.getInt(1), shift, date));
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}