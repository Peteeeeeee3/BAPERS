package Account;

import Job.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Receptionist extends UserAccount {

    public VectorOfAccounts vecAcc;
    public AccountControl accControl;
    public Customer customer;
    public VectorOfJobs vecJob;
    public VectorOfTasks vecTask;
    public Job job;
    public Task task;
    public JobFacadeControl jfc;

    public void acceptJob(Job job) throws SQLException {
        vecJob.addJob(job);
        //Database Version//
        try {
            String sql = "INSERT INTO Job (`jobID`, `summary`, `startTime`, `urgency`) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, job.getID());
            preparedStatement.setString(2, job.getSummary());
            preparedStatement.setInt(3, job.getStartTime());
            preparedStatement.setInt(4, job.getUrgency());
            try{
                vecJob.getControl().getControl().getDBC().getDBGateway().write(preparedStatement);
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createCustomer(Customer customer) {
        vecAcc.addCustAccount(customer);
        //Database Version//
        try {
            String sql = "INSERT INTO Customer (`company`, `name`, `address`, `phone`) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = vecAcc.getAccControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, customer.getCompany());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getPhone());

            try{
                vecAcc.getAccControl().getControl().getDBC().getDBGateway().write(preparedStatement);
            }catch(Exception e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void assignTask(int jobID, int taskID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < vecJob.getVector().size(); ++i) {
            if (job.generateJobNo() == jobID)
                for (int j = 0; i < vecTask.getVector().size(); ++j) {
                    if (task.generateAccountNo() == taskID) {
                        jfc.addTaskToJob(jobID, taskID);
                    }
                }
        }

    }

    public void searchCustomer(int accountNo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < vecAcc.getCustomerVector().size(); ++i) {
            if (customer.generateAccountNo() == accountNo) {
                accountNo = customer.generateAccountNo();
                break;
            }
        }

        //Database version might need fixing//
        String sql = "SELECT *" + " FROM customer" + " WHERE accountNo = ?";
        try {
            PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            ResultSet rs;
            preparedStatement.setInt(1, customer.getAccountNo());
            rs = accControl.getControl().getDBC().getDBGateway().read(preparedStatement);
            while (rs.next()) {
                int accountno = rs.getInt("accountNo");
                String name = rs.getString("name");
                String company = rs.getString("company");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                int valued = rs.getInt("valued");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public void assignUrgency(int jobID, int urgency) throws SQLException {
        for(int i=0; i< vecJob.getVector().size(); ++i) {
            if (vecJob.getVector().get(i).getID() == jobID) {
                job.setUrgency(urgency);
                break;
            }
        }try {
            String sql = "INSERT INTO Job (`urgency`) VALUES (?)";
            PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, job.getUrgency());

            try{
                vecJob.getControl().getControl().getDBC().getDBGateway().write(preparedStatement);
            }catch(Exception e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void recordDeadline(int jobID, int deadline) throws SQLException {
        for(int i=0; i<vecJob.getVector().size(); ++i){
            if(vecJob.getVector().get(i).getID()==jobID){
                job.setEndTime(deadline);
            }
        }
        try {
            String sql = "INSERT INTO Job (`deadline`) VALUES (?)";
            PreparedStatement preparedStatement = vecJob.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, job.getEndTime());
            try {
                vecJob.getControl().getControl().getDBC().getDBGateway().write(preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void recordSpecialInstruction(int taskID, String instructions) throws SQLException {
        for(int i=0; i<vecTask.getVector().size(); ++i){
            if(vecTask.getVector().get(i).getTaskID()==taskID){
                task.setDescription(instructions);
            }
        }
        try {
            String sql = "INSERT INTO Task (`description`) VALUES (?)";
            PreparedStatement preparedStatement = vecTask.getControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, task.getDescription());

            try{
                vecTask.getControl().getControl().getDBC().getDBGateway().write(preparedStatement);
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Receptionist(UserAccount user) {
        super(user.getPassword(), user.getName(), user.getAccess(),user.getVecUser());

        throw new UnsupportedOperationException();
    }
}