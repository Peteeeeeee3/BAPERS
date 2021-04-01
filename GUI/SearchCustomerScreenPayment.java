package GUI;

import Account.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchCustomerScreenPayment extends JPanel {
    public JPanel searchCustomerPanel;
    public JButton Search;
    public JTextField searchBar;
    public JTable customerTable;
    public JButton backButton;
    public JButton nextButton;
    public JButton searchButton;
    private JButton createCustomerButton;
    public GUIControl guiControl;
    DefaultTableModel defaultTableModel;
    public Customer customer;
    int flag = 0;

    public SearchCustomerScreenPayment(GUIControl guiControl, JFrame frame) {
        //Initialise//
        this.guiControl = guiControl;
        //JFrame frame = new JFrame("Search Customer");
        frame.setContentPane(new SearchCustomerScreenPayment(guiControl).searchCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public SearchCustomerScreenPayment(GUIControl guiControl) {
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.openPreviousFrame();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    guiControl.usePaymentScreen(guiControl, customer);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Gets the value from text field
                int id = Integer.parseInt(searchBar.getText());
                try {
                    //This if statement checks if the id is already in the database or not
                    if(!getGuiControl().getController().getAccountControl().vecAcc.checkId(id)){
                        //If it isnt then this message will be shown
                        JOptionPane.showMessageDialog(searchButton, "Customer does not exist. Please create.");
                    } else {
                        //Assign customer object to search Customer
                        customer = getGuiControl().getController().getAccountControl().vecAcc.searchCustomer(id);
                        //Calling the fetchData() method from below to create and pull the table from the database
                        fetchData();
                    }
                } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    guiControl.closeCurrentFrame();
                    getGuiControl().useCreateCustomerScreen(guiControl);
            }
        });
    }

    public void fetchData(){
        //This sets and shows the column names for the tables
        defaultTableModel = new DefaultTableModel();
        customerTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Account No");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Company");
        defaultTableModel.addColumn("Phone");
        defaultTableModel.addColumn("Address");
        defaultTableModel.addColumn("Valued");
        //This adds the rows from the database
        defaultTableModel.addRow(new Object[]{customer.getAccountNo(), customer.getName(), customer.getCompany(), customer.getPhone(), customer.getAddress(), customer.getValued()});
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
