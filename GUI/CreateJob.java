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

    //Where the screen is created
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
                //Getting the values from the text fields
                int sTime = Integer.parseInt(startTime.getText());
                int sDate = Integer.parseInt(startDate.getText());
                int prio = Integer.parseInt(priority.getText());
                int pric = Integer.parseInt(price.getText());
                int custID = Integer.parseInt(customerID.getText());
                int payID = Integer.parseInt(paymentID.getText());
                String instruction = instruct.getText();
                String stat = status.getText();
                //Calling it from the Job control
                getGuiControl().getController().getJobControl().acceptJob(custID, payID, sTime, sDate, prio, instruction, pric, stat);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
        addATaskToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useAddTaskScreen(guiControl);
            }
        });
    }
    public GUIControl getGuiControl() {
        return guiControl;
    }
}
