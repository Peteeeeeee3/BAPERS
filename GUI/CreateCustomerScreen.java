package GUI;

import javax.swing.*;

public class CreateCustomerScreen extends Form {
    private JPanel createCustomerPanel;
    private JTextField emailField;
    private JTextField companyField;
    private JTextField addressField;
    private JTextField phoneField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField nameField;

    public CreateCustomerScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Create Customer Screen");
//        frame.setContentPane(new CreateCustomerScreen().createCustomerPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
