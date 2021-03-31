package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateJob extends JPanel {
    private JTextField deadlineTextField;
    private JButton addTaskButton;
    private JTable numberOfJobs;
    private JTextField specialInstructions;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel existingCustomerPanel;
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
                JOptionPane.showMessageDialog(existingCustomerPanel, "Confirmed");
                guiControl.closeCurrentFrame();
                guiControl.useAddTaskScreen(guiControl);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useExistingDeadlineCustomerScreen(guiControl);
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deadline = Integer.parseInt(deadlineTextField.getText());
                //try {
                    //guiControl.getController().getJobControl().addTask(deadline);
                //} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException classNotFoundException) {
                //    classNotFoundException.printStackTrace();
                //}
            }
        });
    }
    public GUIControl getGuiControl() {
        return guiControl;
    }
}
