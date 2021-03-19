package Account;

import Job.Job;
import Job.VectorOfJobs;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Receptionist extends UserAccount {
    public VectorOfAccounts vecAcc;
    public AccountControl accControl;
    public Customer customer;
    public VectorOfJobs vecJob;
    public Job job;


    public void acceptJob( Job job) throws SQLException {
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

    public void assignTask(int jobID, int taskID) {
        throw new UnsupportedOperationException();
    }

    public Customer searchCustomer(int accountNo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for(int i=0; i<vecAcc.getCustomerVector().size(); ++i ){
            if(customer.generateAccountNo()==accountNo){
                accountNo=customer.generateAccountNo();
                break;
            }
        }
        return searchCustomer(accountNo);
    }

    public void assignUrgency(int jobID, int urgency) {
        throw new UnsupportedOperationException();
    }

    public void recordDeadline(int deadline) {
        throw new UnsupportedOperationException();
    }

    public void recordSpecialInstruction(String instructions) {
        throw new UnsupportedOperationException();
    }

    public Receptionist(UserAccount user) {
        super(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess());
        throw new UnsupportedOperationException();
    }
}