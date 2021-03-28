package GUI;

import javax.swing.*;
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

    public CreateCustomerScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CreateCustomerScreen(guiControl).createCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public CreateCustomerScreen(GUIControl guiControl) {
        this.guiControl = guiControl;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int phone = Integer.parseInt(phoneField.getText());
                String address= addressField.getText();
                String company = companyField.getText();
                String email = emailField.getText();
                getGuiControl().getController().getAccountControl().createCustomer(company,name,address,phone);
                guiControl.closeCurrentFrame();
                guiControl.useExistingDeadlineCustomerScreen(guiControl);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useCreateCustomerScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
