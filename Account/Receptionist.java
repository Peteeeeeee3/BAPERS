package Account;

import Job.*;

import java.sql.PreparedStatement;
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
        String sql = "INSERT INTO Job (`jobID`, `summary`, `startTime`, `urgency`) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, job.getID());
        preparedStatement.setString(2, job.getSummary());
        preparedStatement.setInt(3, job.getStartTime());
        preparedStatement.setInt(4, job.getUrgency());


    }

    public void createCustomer(Customer customer) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        vecAcc.addCustAccount(customer);
        //Database Version//
        String sql = "INSERT INTO Customer (`company`, `name`, `address`, `phone`) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getCompany());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setInt(4, customer.getPhone());


    }

    public void assignTask(int jobID, int taskID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for(int i=0 ; i<vecJob.getVector().size(); ++i ){
            if(job.generateJobNo()==jobID)
                for(int j=0; i<vecTask.getVector().size(); ++j){
                    if(task.generateAccountNo()==taskID){
                        jfc.addTaskToJob(jobID,taskID);
                    }
                }
        }

    }

    public Customer searchCustomer(int accountNo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < vecAcc.getCustomerVector().size(); ++i) {
            if (customer.generateAccountNo() == accountNo) {
                accountNo = customer.generateAccountNo();
                break;
            }
        }
        return searchCustomer(accountNo);
    }

    public void assignUrgency(int jobID, int urgency) throws SQLException {
        for(int i=0; i< vecJob.getVector().size(); ++i) {
            if (vecJob.getVector().get(i).getID() == jobID) {
                job.setUrgency(urgency);
                break;
            }
        }
        String sql = "INSERT INTO Job (`urgency`) VALUES (?)";
        PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, job.getUrgency());

    }

    public void recordDeadline(int jobID, int deadline) throws SQLException {
        for(int i=0; i<vecJob.getVector().size(); ++i){
            if(vecJob.getVector().get(i).getID()==jobID){
                job.setEndTime(deadline);
            }
        }
        String sql = "INSERT INTO Job (`deadline`) VALUES (?)";
        PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, job.getEndTime());

    }

    public void recordSpecialInstruction(int taskID, String instructions) throws SQLException {
        for(int i=0; i<vecTask.getVector().size(); ++i){
            if(vecTask.getVector().get(i).getTaskID()==taskID){
                task.setDescription(instructions);
            }
        }

        String sql = "INSERT INTO Task (`description`) VALUES (?)";
        PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, task.getDescription());

    }

    public Receptionist(UserAccount user) {
        super(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess());
        throw new UnsupportedOperationException();
    }
}