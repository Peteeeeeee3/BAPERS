package GUI;

import Account.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateCustomerScreen extends JPanel {
    private JPanel createCustomerPanel;
    private JTextField emailField;
    private JTextField companyField;
    private JTextField addressField;
    private JTextField phoneField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField nameField;
    public GUIControl guiControl;

    //Where the screen is constructed and displayed
    public CreateCustomerScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CreateCustomerScreen(guiControl).createCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    //Where button functions are called
    public CreateCustomerScreen(GUIControl guiControl) {
        this.guiControl = guiControl;
        DefaultTableModel defaultListModel = new DefaultTableModel();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting the inputs from the fields
                String name = nameField.getText();
                long phone = Long.parseLong(phoneField.getText());
                String address = addressField.getText();
                String company = companyField.getText();
                String email = emailField.getText();
                //Calling the method from the accountControl
                getGuiControl().getController().getAccountControl().vecAcc.customers.add(new Customer(company, name, address, phone, guiControl.getController().getAccountControl().vecAcc));
                //uiControl.closeCurrentFrame();
                guiControl.useExistingDeadlineCustomerScreen(guiControl);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
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
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
