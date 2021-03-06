package Account;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class VectorOfUsers {
    private UserAccount user;
    private int noOfUsers = 0;
    private AccountControl accControl;
    private Vector<UserAccount> users = new Vector<UserAccount>();

    private void incrementNoOfUsers() {
        noOfUsers++;
    }

    private void decrementNoOfUsers() {
        noOfUsers--;
    }

    void addUser(UserAccount user) {
        users.add(user);
        incrementNoOfUsers();


    }

    public void removeUser(int id) {
        try {
            String sql = "DELETE FROM staff_member WHERE staffID = ?";
            PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            accControl.getControl().getDBC().write(preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    UserAccount retrieveUser(int staffID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getStaffID() == staffID) {
                return users.get(i);
            }
        }
        return null;
    }

    AccountControl getAccControl() {
        return accControl;
    }

    public int traverse(int staffID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getStaffID() == staffID) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkId(int id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
    String sql = "SELECT staffID FROM staff_member WHERE staffID = ?";
    PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
    preparedStatement.setInt(1, id);
    ResultSet rs = accControl.read(preparedStatement);
    return rs.next();
    }

    public int getLargestID() {
        //retrieves the largest ID in the vector
        //used to generate staffID
        int i = 0, largest = 0;
        while (i < users.size()) {
            if (users.get(i).getStaffID() > largest) {
                largest = users.get(i).getStaffID();
            }
            ++i;
        }
        return largest;
    }

    public Vector<UserAccount> getVector() {
        return users;
    }

    //returns true if matching data is found and false if none is found
    boolean login(int ID, String password) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Query database
        String sql = "SELECT access FROM staff_member WHERE staff_member.staffID = ? AND staff_member.password = ?";
        PreparedStatement prepStat = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
        //ResultSet rs = accControl.read("SELECT access FROM staff_member WHERE staff_member.staffID = " + ID + " AND staff_member.password = '" + password + "';");
        prepStat.setInt(1, ID);
        prepStat.setString(2, password);
        //System.out.println(prepStat.toString());
        //ResultSet rs = prepStat.executeQuery();
        ResultSet rs = accControl.read(prepStat);
        if (rs == null) {
            return false;
        }
        int access = -1;
        //handle result set
        while (rs.next()) {
            access = rs.getInt(1);
        }
        //System.out.println(access + " " + ID + " " + password);
        if (access == 1 || access == 2 || access == 3 || access == 4) {
            accControl.setAccess(access);
        } else {
            return false;
        }
        return true;
    }

    public UserAccount getUser(){return user;}

    public void logout() {
        throw new UnsupportedOperationException();
    }

    VectorOfUsers(AccountControl accountControl) {
        this.accControl = accountControl;
    }

    boolean editAccess(int id, int access) {
//        for (UserAccount ua : users) {
//            if (ua.getStaffID() == id) {
//                ua.setAccess(access);
//                break;
//            }
//        }
        try {
            if (access < 1 || access > 4) {
                return false;
            }
            String sql = "UPDATE `staff_member` SET `access`= ? WHERE staffID = ?;";
            PreparedStatement preparedStatement = accControl.getControl().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, access);
            preparedStatement.setInt(2, id);
            accControl.getControl().getDBC().getDBGateway().write(preparedStatement);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}