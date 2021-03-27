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
                int id = Integer.parseInt(userIDTextField.getText());
                String name = nameTextField.getText();
                char[] pw = passwordTextField.getPassword();
                int access = Integer.parseInt(accessTextField.getText());
                try {
                    guiControl.getController().getAccountControl().createUser(id, new String(pw), name, access);
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                JOptionPane.showMessageDialog(addUserButton, "User has been successfully added");
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
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

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
