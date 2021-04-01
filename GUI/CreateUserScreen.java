package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUserScreen extends JPanel {
    private JPanel createUserPanel;
    private JTextField userIDTextField;
    private JTextField nameTextField;
    private JPasswordField passwordTextField;
    private JTextField accessTextField;
    private JButton addUserButton;
    private JButton backButton;
    public GUIControl guiControl;

    public CreateUserScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CreateUserScreen(guiControl).createUserPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    public CreateUserScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting the values from the text fields
                String name = nameTextField.getText();
                char[] pw = passwordTextField.getPassword();
                int access = Integer.parseInt(accessTextField.getText());
                //Calling it from accountControl
                getGuiControl().getController().getAccountControl().createUser(new String(pw), name, access);
                JOptionPane.showMessageDialog(addUserButton, "User has been successfully added");
                guiControl.closeCurrentFrame();
                guiControl.useAdminScreen(guiControl);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
