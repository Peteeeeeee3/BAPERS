package GUI;

import Account.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DowngradeCustomerScreen extends JPanel {
    public GUIControl guiControl;
    private JTextField textField1;
    private JButton searchButton;
    private JTable table1;
    private JButton backButton;
    private JButton downgradeButton;
    private JPanel downgradeCustPanel;
    DefaultTableModel defaultTableModel;
    public Customer customer;
    int flag = 0;

    public DowngradeCustomerScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new DowngradeCustomerScreen(guiControl).downgradeCustPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public DowngradeCustomerScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Gets value from the textfield
                int id = Integer.parseInt(textField1.getText());
                try {
                    //Searches for the customer
                    customer = guiControl.getController().getAccountControl().vecAcc.searchCustomer(id);
                    //Displays the data
                    fetchData();
                    //Sets flag to 1
                    flag = 1;
                } catch (Exception classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        downgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if(flag == 1){
                    try {
                        //Checks if the customer has a valued = 1
                        if (customer.getValued() == 1){
                            //if yes then run method
                            getGuiControl().getController().getAccountControl().vecAcc.downgradeCust(id);
                            fetchData();
                            //Shows this message
                            JOptionPane.showMessageDialog(downgradeButton, "Customer has been downgraded. Click search again to see result.");
                        } else {
                            //Show this message
                            JOptionPane.showMessageDialog(downgradeButton, "Customer cannot be downgraded any further.");
                        }

                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                    }
                    case 2 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                    }
                    case 3 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                    }
                    case 4 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                    }
                }
            }
        });
    }

    public void fetchData(){
        defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        defaultTableModel.addColumn("Account No");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Company");
        defaultTableModel.addColumn("Phone");
        defaultTableModel.addColumn("Address");
        defaultTableModel.addColumn("Valued");
        defaultTableModel.addRow(new Object[]{customer.getAccountNo(), customer.getName(), customer.getCompany(), customer.getPhone(), customer.getAddress(), customer.getValued()});
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
