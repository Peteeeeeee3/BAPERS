package GUI;

import Job.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartUpdateTaskForJobScreen extends JPanel {
    private JButton backButton;
    private JTable table;
    private JPanel startTaskPanel;
    private JTextField textField1;
    private JButton showButton;
    private JButton updateStatusButton;
    public GUIControl guiControl;
    DefaultTableModel defaultTableModel;

    public JPanel panelMain;

    public StartUpdateTaskForJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new StartUpdateTaskForJobScreen(guiControl).startTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    public StartUpdateTaskForJobScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGuiControl().openPreviousFrame();
                //getGuiControl().closeCurrentFrame();
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = textField1.getText();
                //Checks whats entered in the textfield
                if (status.equals("pending") || status.equals("in progress") || status.equals("completed")){
                    try {
                        //Checks if job with this status is in the database or not
                        if (!getGuiControl().getController().getJobControl().vecTaskForJob.checkStatus(status)){
                            //If not this message pops up
                            JOptionPane.showMessageDialog(showButton, "No tasks with entered status");
                        } else {
                            viewTasksForJob(status);
                        }
                    } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    //Otherwise this message shows up
                    JOptionPane.showMessageDialog(showButton, "Please enter a correct status");
                }
            }
        });
        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Gets the value from the first column and second column of the table.
                int column = 0;
                int columnCompare = 1;
                int columnStatus = 4;
                int row = table.getSelectedRow();
                int value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
                int valueJob = Integer.parseInt(table.getModel().getValueAt(row, columnCompare).toString());
                String status = table.getModel().getValueAt(row, columnStatus).toString();
                if (status.equals("pending")) {
                    getGuiControl().getController().getJobControl().vecTaskForJob.setStatus("in progress", value, valueJob);
                } else if (status.equals("in progress")){
                    getGuiControl().getController().getJobControl().vecTaskForJob.setStatus("completed", value, valueJob);
                }
            }
        });
    }

    //Create the table
    public void initialiseTable(){
        defaultTableModel = new DefaultTableModel();
        table.setModel(defaultTableModel);
        defaultTableModel.addColumn("Task ID");
        defaultTableModel.addColumn("Job Number");
        defaultTableModel.addColumn("Start Time");
        defaultTableModel.addColumn("Duration");
        defaultTableModel.addColumn("Status");
    }

    //Had to define method here to pull all the records with the status. This code was copied from what Munish made
    public void viewTasksForJob(String status) {
        int TasktaskID = 0, JobjobNumber = 0, startTime = 0, duration = 0;


        initialiseTable();

        try {
            String sql = "SELECT * FROM task_of_job WHERE status = ?";
            PreparedStatement stmt = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            ResultSet result;
            stmt.setString(1, status);
            result = getGuiControl().getController().getDBC().read(stmt);

            while (result.next()) {

                TasktaskID = result.getInt(1);

                JobjobNumber = result.getInt(2);

                startTime = result.getInt(3);

                duration = result.getInt(4);

                status = result.getString(5);
                System.out.println(status);
                defaultTableModel.addRow(new Object[]{TasktaskID, JobjobNumber, startTime, duration, status});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
