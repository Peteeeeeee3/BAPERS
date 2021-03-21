package Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CustomerJobReport extends Report {
    private String customerName;
    private int custID;
    private int dateOfReport;
    private Vector<ReportTask> infoVec = new Vector<ReportTask>();
    private Vector<Integer> startTimes = new Vector<>(), priorities = new Vector<>(), startDates = new Vector<>();

    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getCustID() {
        return custID;
    }
    public int getDateOfReport() {
        return dateOfReport;
    }
    public Vector<Integer> getPriorities() {
        return priorities;
    }
    public Vector<Integer> getStartTimes() {
        return startTimes;
    }
    public Vector<Integer> getStartDates() {
        return startDates;
    }
    public Vector<ReportTask> getInfoVec() {
        return infoVec;
    }

    private int getID(String customerName) {
		int accNo = -1;

		try {
			PreparedStatement preparedStatement;
			String sql_id = "SELECT accountNo " +
					"FROM customer " +
					"WHERE name = ?";

			preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql_id);
			preparedStatement.setString(1, customerName);
			ResultSet rs = RFC.getControl().getDBC().getDBGateway().read(preparedStatement);
			while (rs.next()) {
				accNo = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accNo;
	}

	private void getInfo(int id) {
		try {
			PreparedStatement preparedStatement;
			String sql_main = "SELECT job.jobNumber, task.price, task.taskID, task.location, task_of_job.startTime, " +
					"task_of_job.duration, staff_member.name, job.startTime, job.priority, job.startDate " +
					"FROM job " +
					"JOIN job_task_of_job ON job_task_of_job.JobjobNumber = job.jobNumber " +
					"JOIN task_of_job ON task_of_job.TasktaskID = job_task_of_job.Task_of_JobTasktaskID " +
					"AND task_of_job.JobjobNumber = job_task_of_job.JobjobNumber " +
					"JOIN task ON task.taskID = task_of_job.TasktaskID " +
					"JOIN completed_tasks ON completed_tasks.TasktaskID = task.taskID " +
					"JOIN staff_member ON staff_member.staffID = completed_tasks.Staff_MemberstaffID " +
					"WHERE job.CustomeraccountNo = ? AND completed_tasks.date >= job.startDate AND job.status != \"finished\"";

			preparedStatement = RFC.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql_main);
			if (id != -1) {
				preparedStatement.setInt(1, id);
			} else {
				System.out.println("accNo = " + id);
				throw new Exception();
			}
			handleResultSet(RFC.getControl().getDBC().getDBGateway().read(preparedStatement));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleResultSet(ResultSet rs) {
    	try {
			while (rs.next()) {
				infoVec.add(new ReportTask(rs.getInt(1), rs.getFloat(2), rs.getInt(3),
						rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
				startTimes.add(rs.getInt(8));
				priorities.add(rs.getInt(9));
				startDates.add(rs.getInt(10));
				//System.out.println(rs.getInt(8) + " " + rs.getInt(9));
			}
		} catch (Exception e) {
    		e.printStackTrace();
		}
	}

    public CustomerJobReport(String customerName, int date, ReportFacadeControl rfc) {
        super();
        this.customerName = customerName;
        dateOfReport = date;
		RFC = rfc;
        custID = getID(customerName);
		getInfo(custID);
    }
}