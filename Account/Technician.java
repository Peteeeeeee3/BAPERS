package Account;

import Job.Job;
import Job.VectorOfJobs;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Technician extends UserAccount {
	public VectorOfJobs vecJob;
	public Job job;

	public void updateStatus(int jobID, String status) throws SQLException {

		String sql = "INSERT INTO `job` (`satuts`) VALUE (?)";
		PreparedStatement preparedStatement= vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

		PreparedStatement prepStatment = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement("SELECT `jobNumber` FROM `job` WHERE VALUE `jobNumber=?` ");
		prepStatment.setInt(1,jobID);
		vecJob.getControl().getControl().getDBC().getDBGateway().read(prepStatment);

		for(int i=0; i<vecJob.getVector().size(); ++i){
			if(jobID==job.getID()){
				preparedStatement.setString(1,status);
				vecJob.getControl().getControl().getDBC().getDBGateway().write(preparedStatement);
			}
		}


	}

	public void startTask(int jobID, int taskID) {
		throw new UnsupportedOperationException();
	}

	public Technician(UserAccount user) {
		super(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess(),user.getVecUser());
		throw new UnsupportedOperationException();
	}
}