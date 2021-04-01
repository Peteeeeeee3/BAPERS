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
    public ValuedCustomer valCustomer;
    public DiscountSet discountSet;
    public Discount discount;

    public UserAccount retrieveUser(int staffID) {
        return vecUser.retrieveUser(staffID); // retrieves all the staff members from the vector.
    }

    public Customer retrieveCustomer(int accountNo) {
        return vecAcc.retrieveCustAccount(accountNo); // retrieves all the customer from the vector
    }

    public void createCustomer(String company, String name, String address, long phone) {
        // loops through the vector and gets the name.
        for (int i = 0; i < vecAcc.getCustomerVector().size(); i++) {
            if (vecAcc.getCustomerVector().get(i).getName().equals(name)) {
                break;
            }
        }
        vecAcc.addCustAccount(new Customer(company, name, address, phone, this.vecAcc)); //once found then adds a new customer to the vector.
    }

    public void createUser(String password, String name, int access) {
        //loops through the vector and gets the name of the user.
        for (int i = 0; i < vecUser.getVector().size(); i++) {
            if (vecUser.getVector().get(i).getName().equals(name)) {
                break;
            }
        }
        vecUser.addUser(new UserAccount(password, name, access, this.vecUser)); //stores a new user to the vector.
    }

    public void removeUser(int id){
        for (int i = 0; i < vecUser.getVector().size(); i++){
            if (vecUser.getVector().get(i).getStaffID() == id){
                break;
            }
        }
        vecUser.removeUser(id);
    }

    public boolean updateAccess(int id, int newAccess){
        return vecUser.editAccess(id, newAccess); // updates the user access level, from Receptionist to Technician
    }

    public void upgradeCust(int accountNo) {
        vecAcc.upgradeCustomer(accountNo); //upgrades the customer to values customer based of the account number.
    }

    public void downgradeCust(int accountNo) {
        vecAcc.downgradeCust(accountNo); //downgrades the customer to normal customer based on the account number.
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
    // each control class must contain the methods to allow the it to be called within the rest of classes and allows you to connect to the database.

    @Override
    public boolean login(int ID, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return vecUser.login(ID, password); // logs in user
    }

    @Override
    public void logout() { // logout a user.

    }

    public Control getControl() {
        return control; //connect to the main control.
    }

    @Override // connect to the database
    public void connectDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

    }

    @Override// disconnect from database
    public void disconnectDB() throws SQLException {

    }

    @Override // reads in from the sql
    public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            return control.getDBC().read(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override //writes into the sql.
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

