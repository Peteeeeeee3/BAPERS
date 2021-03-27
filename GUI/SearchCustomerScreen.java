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
    public JScrollBar scrollBar;
    public GUIControl guiControl;
    public DefaultTableModel datatable;
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
        JScrollPane scrollPane = new JScrollPane(customerTable);
        frame.getContentPane().add(scrollPane);

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
                if(flag == 0){
                    guiControl.closeCurrentFrame();
                    guiControl.useCreateCustomerScreen(guiControl);
                } else {
                    guiControl.useExistingDeadlineCustomerScreen(guiControl);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchBar.getText());
                try {
                    getGuiControl().getController().getAccountControl().receptionist.searchCustomer(id);
                    flag = 1;
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                guiControl.useCreateCustomerScreen(guiControl);
            }
        });
    }

    public void displayJTable(int id){

    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
