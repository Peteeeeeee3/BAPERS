package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExistingDeadlineCustomerScreen {
    private JTextField deadlineTextField;
    private JButton addTaskButton;
    private JTable numberOfJobs;
    private JTextField specialInstructions;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel existingCustomerPanel;

    public ExistingDeadlineCustomerScreen() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(existingCustomerPanel, "Confirmed");
            }
        });
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Customers");
//        frame.setContentPane(new ExistingDeadlineCustomerScreen().existingCustomerPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
