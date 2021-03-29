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
                int id = Integer.parseInt(textField1.getText());
                try {
                    customer = guiControl.getController().getAccountControl().vecAcc.searchCustomer(id);
                    fetchData();
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
                        getGuiControl().getController().getAccountControl().vecAcc.downgradeCust(id);
                        fetchData();
                        JOptionPane.showMessageDialog(downgradeButton, "Customer has been downgraded. Click search again to see result.");
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
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
        if(customer.getAccountNo() != 0) {
            flag = 0;
        } else {
            flag = 1;
        }
        defaultTableModel.addRow(new Object[]{customer.getAccountNo(), customer.getName(), customer.getCompany(), customer.getPhone(), customer.getAddress(), customer.getValued()});
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
