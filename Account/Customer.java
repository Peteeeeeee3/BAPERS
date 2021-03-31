package Account;

import Job.*;
import Payment.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private String company;
    private int accountNo;
    private String name;
    private String address;
    private int phone;
    private VectorOfAccounts vecAcc;
    private int valued;
    public Payment payment;
    private DiscountSet discountSet;
    //private VectorOfJobs jobs = new VectorOfJobs(vecAcc.getAccControl().getControl().getJobControl());


    public void addJob() {
        throw new UnsupportedOperationException();
    }

    public void makePayment(Object[] jobs) {
        throw new UnsupportedOperationException();
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getValued(){ return valued;}

    public VectorOfAccounts getVecAcc() {
        return vecAcc;
    }

    public DiscountSet getDiscountSet() {
        return discountSet;
    }

    public void setDiscountSet(DiscountSet discountSet) {
        this.discountSet = discountSet;
    }

    //public VectorOfJobs getJobs() {
    //    return jobs;
    //}

    public int generateAccountNo() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String sql = "SELECT `accountNo` FROM `Customer` WHERE Customer.name = ? AND Customer.company = ? AND Customer.address = ? AND phone=?";
        PreparedStatement preparedStatement = vecAcc.getAccControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, company);
        preparedStatement.setString(3, address);
        preparedStatement.setLong(4, phone);

        ResultSet rs = vecAcc.getAccControl().getControl().getDBC().read(preparedStatement);
        //Find the largest ID, this indicates this is the newest object and hence belongs to this one
        // as this function only gets called in the constructor
        int finalValue = -1, checkValue = -1;
        //get values from result set
        while (rs.next()) {
            //apply value to correct variable
            if (finalValue == -1) {
                finalValue = rs.getInt(1);
            } else {
                checkValue = rs.getInt(1);
            }
            //see if the check value is larger than the final value (which will be returned)
            if (checkValue > finalValue) {
                finalValue = checkValue;
            }
        }
        return finalValue;
    }

    public Customer(String company, String name, String address, int phone, VectorOfAccounts vecAcc) {
        this.company = company;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.vecAcc = vecAcc;
        // i implemented this in Receptionist for when she creates a customer.
        String sql = "INSERT INTO `customer`(`name`, `company`, `phone`, `address`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = vecAcc.getAccControl().getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql)) {
            this.accountNo = generateAccountNo();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, company);
            preparedStatement.setLong(3, phone);
            preparedStatement.setString(4, address);
            vecAcc.getAccControl().getControl().getDBC().write(preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer(String company, String name, String address, int phone, VectorOfAccounts vecAcc, int accountNo, int valued)  {
        this.company = company;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.vecAcc = vecAcc;
        this.accountNo = accountNo;
        this.valued = valued;
    }
}