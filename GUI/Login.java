package GUI;

import Account.UserAccount;
import Account.VectorOfUsers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JPanel {
    private JButton loginButton;
    private JPanel panelMain;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public GUIControl guiControl;

    public Login(GUIControl guiControl, JFrame frame) {
        //JFrame frame = new JFrame("Login");
        //stuff I just copied from the main function that was in this class, worked no problem
        frame.setContentPane(new Login(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    public Login(GUIControl guiControl) {

        this.guiControl = guiControl;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(usernameField.getText());
                char[] pw = passwordField.getPassword();
                getGuiControl().getController().login(id, new String(pw));
                if (guiControl.getController().getAccountControl().userAccount.getAccess() == 1) {
                    guiControl.closeCurrentFrame();
                    guiControl.useHomepage(guiControl);
                } else if (guiControl.getController().getAccountControl().userAccount.getAccess() == 4){
                    guiControl.closeCurrentFrame();
                    guiControl.useOMHomePage(guiControl);
                } else if(guiControl.getController().getAccountControl().userAccount.getAccess() == 3) {
                    guiControl.closeCurrentFrame();
                    guiControl.useSMHomePage(guiControl);
                } else if(guiControl.getController().getAccountControl().userAccount.getAccess() == 2){
                    guiControl.closeCurrentFrame();
                    guiControl.useTechHomePage(guiControl);
                } else {
                    JOptionPane.showMessageDialog(loginButton, "Cannot log you in");
                }
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
