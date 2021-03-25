package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchBar.getText());
                getGuiControl().getController().getAccountControl().retrieveCustomer(id);
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
