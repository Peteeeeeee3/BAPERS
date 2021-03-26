package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen extends Form {
    private JButton createUserButton;
    private JPanel adminPanel;
    private JButton removeUserButton;
    private JButton editAccessButton;
    private JButton backButton;
    private JButton backupSystemButton;
    private JButton restoreSystemButton;
    private JButton configureScheduleButton;

    public AdminScreen(GUIControl guiControl) {
        super(guiControl);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Admin Screen");
//        frame.setContentPane(new AdminScreen().adminPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
