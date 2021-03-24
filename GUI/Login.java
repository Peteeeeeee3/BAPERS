package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    public JPanel panelMain;

//    public static void main(String[] args){
//        JFrame frame = new JFrame("Login");
//        frame.setContentPane(new Login().panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
