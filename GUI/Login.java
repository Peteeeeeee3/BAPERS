package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.security.Guard;
=======
>>>>>>> 0f2ab72144e6cdf1daf62d38aaae1561599c63bc


public class Login extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panelMain;
    private GUIControl guiControl;

    public Login(GUIControl guiControl, JFrame frame) {
        //initialize
        this.guiControl = guiControl;
        //JFrame frame = new JFrame("Login");
        //stuff I just copied from the main function that was in this class, worked no problem
        frame.setContentPane(new Login().panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);


//
        loginButton.addActionListener(new ActionListener() {
            @Override
            //this would handle the button press. commented it out but did not be the cause of the issue, but it occurred when i implemented this
            public void actionPerformed(ActionEvent e) {
//                int id = Integer.parseInt(usernameField.getText());
//                char[] pw = passwordField.getPassword();
//                getGuiControl().getController().login(id, new String(pw));
            }
        });
    }

    public Login() {}

    public GUIControl getGuiControl() {
        return guiControl;
    }

//    public static void main(String[] args) {
//
//    }

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
