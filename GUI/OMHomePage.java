package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OMHomePage extends JPanel {
    private JButton jobsButton;
    private JButton paymentButton;
    private JButton reportsButton;
    private JButton customerButton;
    private JButton adminButton;
    private JButton tasksButton;
    private JButton logoutButton;
    private JTextPane WELCOMETextPane;
    private JPanel omHomePage;
    public GUIControl guiControl;

    public OMHomePage(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new OMHomePage(guiControl).omHomePage);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public OMHomePage(GUIControl guiControl){
        this.guiControl = guiControl;
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useTaskManageScreen(guiControl);
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useSearchCustomerPayment(guiControl);
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useReportsScreen(guiControl);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useChangeCustLvlScrn(guiControl);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useAdminScreen(guiControl);
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useViewTaskScreen(guiControl);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!guiControl.getViewingOrder().isEmpty()) {
                    while (!guiControl.getViewingOrder().isEmpty()) {
                        guiControl.getViewingOrder().pop();
                    }
                }
                guiControl.useLogin(guiControl);
                guiControl.getController().setAccess(0);
            }
        });
    }
    public GUIControl getGuiControl() {
        return guiControl;
    }
}
