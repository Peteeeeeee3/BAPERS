package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateJob extends JPanel {
    private JButton addTaskButton;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel existingCustomerPanel;
    private JTextField startTime;
    private JTextField startDate;
    private JTextField priority;
    private JTextField price;
    private JTextField instruct;
    private JTextField status;
    private JButton addATaskToButton;
    private JTextField customerID;
    private JTextField paymentID;
    public GUIControl guiControl;

    public CreateJob(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CreateJob(guiControl).existingCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public CreateJob(GUIControl guiControl) {
        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sTime = Integer.parseInt(startTime.getText());
                int sDate = Integer.parseInt(startDate.getText());
                int prio = Integer.parseInt(priority.getText());
                int pric = Integer.parseInt(price.getText());
                int custID = Integer.parseInt(customerID.getText());
                int payID = Integer.parseInt(paymentID.getText());
                String instruction = instruct.getText();
                String stat = status.getText();
                getGuiControl().getController().getJobControl().acceptJob(custID, payID, sTime, sDate, prio, instruction, pric, stat);
            }
        });

    }
    public GUIControl getGuiControl() {
        return guiControl;
    }
}
