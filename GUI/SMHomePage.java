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
                guiControl.closeCurrentFrame();
                guiControl.useSearchCustomerScreen(guiControl);
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.usePaymentScreen(guiControl);
            }
        });
        startTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useStartTaskScreen(guiControl);
            }
        });
        updateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useUpdateTaskScreen(guiControl);
            }
        });
        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useViewJobScreen(guiControl);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        configureScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useGenerateReportScreen(guiControl);
            }
        });
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useGenerateReportScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
