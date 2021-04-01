package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen extends JPanel {
    private JButton createUserButton;
    private JPanel adminPanel;
    private JButton removeUserButton;
    private JButton editAccessButton;
    private JButton backButton;
    private JButton backupSystemButton;
    private JButton restoreSystemButton;
    private JButton configureScheduleButton;
    public GUIControl guiControl;

    public AdminScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new AdminScreen(guiControl).adminPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public AdminScreen(GUIControl guiControl) {
        this.guiControl = guiControl;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useCreateUserScreen(guiControl);
            }
        });
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useRemoveUserScreen(guiControl);
            }
        });
        editAccessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useEditAccessScreen(guiControl);
            }
        });
        configureScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useConfigScheduleScreen(guiControl);
            }
        });
        restoreSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.getController().getDBC().getDBGateway().restoreToDB();
                JOptionPane.showMessageDialog(restoreSystemButton, "Successfully restored!");
            }
        });
        backupSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.getController().getDBC().getDBGateway().backupToDB();
                JOptionPane.showMessageDialog(restoreSystemButton, "Successful backup!");
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
