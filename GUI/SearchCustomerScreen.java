package GUI;

import Account.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchCustomerScreen extends JPanel {
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

    public SearchCustomerScreen(GUIControl guiControl, JFrame frame) {
        //Initialise//
        this.guiControl = guiControl;
        //JFrame frame = new JFrame("Search Customer");
        frame.setContentPane(new SearchCustomerScreen(guiControl).searchCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public SearchCustomerScreen(GUIControl guiControl) {
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    guiControl.closeCurrentFrame();
                    guiControl.useExistingDeadlineCustomerScreen(guiControl);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchBar.getText());
                try {
                    if(!getGuiControl().getController().getAccountControl().vecAcc.checkId(id)){
                        JOptionPane.showMessageDialog(searchButton, "Customer does not exist. Please create.");
                    } else {
                        customer = getGuiControl().getController().getAccountControl().vecAcc.searchCustomer(id);
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
        defaultTableModel = new DefaultTableModel();
        customerTable.setModel(defaultTableModel);
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
