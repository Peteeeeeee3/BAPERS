package Account;

import Control.Control;
import Database.I_Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountControl implements Account.I_Account, I_Database {

    public VectorOfAccounts vecAcc;
    public VectorOfUsers vecUser;
    private Control control;
    public OfficeManager officeManager;
    public Receptionist receptionist;
    public UserAccount userAccount;
    public Customer customer;
    public ValuedCustomer valCustomer;
    public DiscountSet discountSet;
    public Discount discount;

    public UserAccount retrieveUser(int staffID) {
        return vecUser.retrieveUser(staffID);
    }

    public Customer retrieveCustomer(int accountNo) {
        return vecAcc.retrieveCustAccount(accountNo);
    }

    public void createCustomer(String company, String name, String address, int phone) {
        for (int i = 0; i < vecAcc.getCustomerVector().size(); i++) {
            if (vecAcc.getCustomerVector().get(i).getName().equals(name)) {
                break;
            }
        }
        vecAcc.addCustAccount(new Customer(company, name, address, phone, this.vecAcc));
    }

    public void createUser(int ID, String password, String name, int access) {
        for (int i = 0; i < vecUser.getVector().size(); i++) {
            if (vecUser.getVector().get(i).getStaffID() == ID) {
                break;
            }
        }
        vecUser.addUser(new UserAccount(ID, password, name, access, this.vecUser));
    }

    public void updateAccess(int id, int newAccess){getOfficeManager().editAccess(id, newAccess);}

    public void upgradeCust(int accountNo) {
        if (customer.getAccountNo() == accountNo) {
            customer = valCustomer;
        }
    }

    public void downgradeCust(int accountNo) {
        if (customer.getAccountNo() == accountNo)
            discountSet.removeDiscount(discount);
    }

    public void editDiscount(int accountNo) {
        throw new UnsupportedOperationException();
    }

    public void makePayment(Object[] jobs) {
        throw new UnsupportedOperationException();
    }

    public AccountControl(Control ctrl) {
        this.vecUser = new VectorOfUsers(this);
        this.vecAcc = new VectorOfAccounts(this);
        this.control = ctrl;
    }


    //////// Override Methods ///////

    @Override
    public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return vecUser.login(ID, password);
    }

    @Override
    public void logout() {

    }

    public void updateAccess(int staffID, Object newAccess) {
        throw new UnsupportedOperationException();
    }

    public Control getControl() {
        return control;
    }

    public OfficeManager getOfficeManager(){return officeManager;}

    @Override
    public void connectDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

    }

    @Override
    public void disconnectDB() throws SQLException {

    }

    @Override
    public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            return control.getDBC().read(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(PreparedStatement sql) {
        try {
            control.getDBC().write(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setAccess(int access) {
        control.setAccess(access);
    }
}

