package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMHomePage extends JPanel {
    private JPanel smHomePagePanel;
    private JButton newJobButton;
    private JButton paymentButton;
    private JButton startTaskButton;
    private JButton updateTaskButton;
    private JButton viewJobButton;
    private JButton logoutButton;
    private JTextPane WELCOMETextPane;
    private JButton configureScheduleButton;
    private JButton generateReportButton;
    public GUIControl guiControl;

    public SMHomePage(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new SMHomePage(guiControl).smHomePagePanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public SMHomePage(GUIControl guiControl){
        this.guiControl = guiControl;
        newJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useSearchCustomerScreen(guiControl);
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useSearchCustomerPayment(guiControl);
            }
        });
        startTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useStartTaskScreen(guiControl);
            }
        });
        updateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useUpdateJobScreen(guiControl);
            }
        });
        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useViewJobScreen(guiControl);
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
        configureScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useGenerateReportScreen(guiControl);
            }
        });
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useGenerateReportScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
