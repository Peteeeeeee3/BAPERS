package GUI;

import Account.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//The way this is coded is exactly the same to how downgrade customer was coded. Fully commented there
public class UpgradeCustomerScreen extends JPanel {
    public GUIControl guiControl;
    public JTextField searchTextField;
    private JButton searchButton;
    private JTable table1;
    private JButton backButton;
    private JButton upgradeButton;
    private JPanel upgradeCustomerPanel;
    private JButton nextButton;
    DefaultTableModel defaultTableModel;
    int flag = 0;
    public Customer customer;

    public UpgradeCustomerScreen(GUIControl guiControl, JFrame frame){
    this.guiControl = guiControl;
        frame.setContentPane(new UpgradeCustomerScreen(guiControl).upgradeCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public UpgradeCustomerScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchTextField.getText());
                customer = guiControl.getController().getAccountControl().vecAcc.searchCustomer(id);
                fetchData();
                flag = 1;
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                        break;
                    case 2:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                        break;
                    case 3:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                        break;
                    case 4:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                        break;
                }
            }
        });
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchTextField.getText());
                if (flag == 1){
                    getGuiControl().getController().getAccountControl().upgradeCust(id);
                    //Creates the table
                    fetchData();
                    //Type handling between valued and not valued
                    if (customer.getValued() == 0){
                        JOptionPane.showMessageDialog(upgradeButton, "Customer has been upgraded. Click search again to see result.");
                    } else {
                        JOptionPane.showMessageDialog(upgradeButton, "This customer is already a valued customer. Cannot upgrade further.");
                    }
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customer.getValued() == 1){
                    guiControl.closeCurrentFrame();
                    getGuiControl().useSelectDiscountScreen(guiControl);
                } else {
                    JOptionPane.showMessageDialog(nextButton, "Cannot select discount plan. Please upgrade customer first");
                }
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
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
}
