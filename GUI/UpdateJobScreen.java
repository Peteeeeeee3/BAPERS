package GUI;

import Job.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateJobScreen extends JPanel {
    private JButton backButton;
    private JTable table;
    public GUIControl guiControl;
    public JPanel panelMain;
    private JButton showButton;
    public Job job;
    DefaultTableModel defaultTableModel;

    public UpdateJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new UpdateJobScreen(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    public UpdateJobScreen(GUIControl guiControl){
        this.guiControl = guiControl;

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    job = getGuiControl().getController().getJobControl().vecJobs.viewActiveJobs();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                fetchData();
            }
        });
    }

    public void fetchData(){
        defaultTableModel = new DefaultTableModel();
        table.setModel(defaultTableModel);
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
