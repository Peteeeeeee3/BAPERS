package GUI;

import Job.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewJobScreen extends JPanel {
    private JPanel viewJobPanel;
    private JTable jobTable;
    private JButton backButton;
    private JTextField jobIDTextField;
    private JButton searchButton;
    private JButton updateJobButton;
    public GUIControl guiControl;
    DefaultTableModel defaultTableModel;
    public Job job;
    private JScrollPane scrollPane;

    public ViewJobScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new ViewJobScreen(guiControl).viewJobPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public ViewJobScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(jobIDTextField.getText());
                job = getGuiControl().getController().getJobControl().vecJobs.viewJob(id);
                if (job.getID() == 0){
                    JOptionPane.showMessageDialog(searchButton, "This job does not exist.");
                } else {
                    fetchData();
                }
            }
        });
    }

    public void fetchData(){
        defaultTableModel = new DefaultTableModel();
        jobTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Job Number");
        defaultTableModel.addColumn("Customer Account No");
        defaultTableModel.addColumn("Payment ID");
        defaultTableModel.addColumn("Start Time");
        defaultTableModel.addColumn("Start Date");
        defaultTableModel.addColumn("Priority");
        defaultTableModel.addColumn("Special Instructions");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Status");

        defaultTableModel.addRow(new Object[]{job.getID(), job.getCustomerid(), job.getPaymentid(), job.getStartTime(), job.getStartDate(), job.getUrgency(), job.getSummary(), job.getPrice(), job.getStatus()});
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
