package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateJob extends JPanel {
    private JButton addTaskButton;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel existingCustomerPanel;
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
                //Getting the values from the text fields as well as
                //Similar to comment in AddTaskToJob, formats date&time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
                LocalTime now = LocalTime.now();
                String timeString = now.format(formatter);
                int localTime = Integer.parseInt(timeString);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate date_now = LocalDate.now();
                int date = Integer.parseInt(dtf.format(date_now));
                int prio = Integer.parseInt(priority.getText());
                int pric = Integer.parseInt(price.getText());
                int custID = Integer.parseInt(customerID.getText());
                int payID = Integer.parseInt(paymentID.getText());
                String instruction = instruct.getText();
                String stat = status.getText();
                //Calling it from the Job control
                getGuiControl().getController().getJobControl().acceptJob(custID, payID, localTime, date, prio, instruction, pric, stat);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                        break;
                    case 2:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                        break;
                    case 3:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                        break;
                    case 4:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                        break;
                }
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
