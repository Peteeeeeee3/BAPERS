package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//This is where AddTaskToJob should be going
public class AddTaskToJobScreen extends JPanel {
    private JPanel addTaskPanel;
    private JTable table;
    private JButton cancelButton;
    private JButton confirmButton;
    private JButton addSelectedTaskButton;
    private JTable table2;
    public GUIControl guiControl;
    DefaultTableModel defaultTableModel;

    public AddTaskToJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new AddTaskToJobScreen(guiControl).addTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public AddTaskToJobScreen(GUIControl guiControl) {

        this.guiControl = guiControl;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                    }
                    case 2 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                    }
                    case 3 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                    }
                    case 4 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                    }
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllTasks();
                viewJobs();
            }
        });
        addSelectedTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table.getSelectedRow();
                int taskID = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
                int columnJobID = 0;
                int rowJob = table2.getSelectedRow();
                int jobID = Integer.parseInt(table2.getModel().getValueAt(rowJob, columnJobID).toString());
                int columnDurat = 4;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
                LocalTime now = LocalTime.now();
                String timeString = now.format(formatter);
                int localTime = Integer.parseInt(timeString);
                int duration = Integer.parseInt(table.getModel().getValueAt(row, columnDurat).toString());
                AddTaskToTaskOfJob(taskID, jobID, localTime, duration, "pending");
                AddTojobtaskofjob(taskID, jobID);
            }
        });
    }

    public void initialiseTable(){
        defaultTableModel = new DefaultTableModel();
        table.setModel(defaultTableModel);
        defaultTableModel.addColumn("Task ID");
        defaultTableModel.addColumn("Description");
        defaultTableModel.addColumn("Location");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Duration");
    }

    public void initialiseTable2(){
        defaultTableModel = new DefaultTableModel();
        table2.setModel(defaultTableModel);
        defaultTableModel.addColumn("Job Number");
        defaultTableModel.addColumn("Customer Account No");
        defaultTableModel.addColumn("Payment ID");
        defaultTableModel.addColumn("Start Time");
        defaultTableModel.addColumn("Start Date");
        defaultTableModel.addColumn("Priority");
        defaultTableModel.addColumn("Special Instructions");
        defaultTableModel.addColumn("Price");
    }

    public void viewAllTasks(){
        int taskID = 0, duration = 0;
        String taskDesc = "", location = "";
        double price = 0;
        initialiseTable();
        try{
            String sql = "SELECT * FROM task";
            PreparedStatement prepStat = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            ResultSet rs;
            rs = getGuiControl().getController().getDBC().read(prepStat);
            while (rs.next()){
                taskID = rs.getInt(1);
                taskDesc = rs.getString(2);
                location = rs.getString(3);
                price = rs.getDouble(4);
                duration = rs.getInt(5);
                defaultTableModel.addRow(new Object[]{taskID, taskDesc, location, price, duration});
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void viewJobs(){
        int jobid = 0, custID = 0, paymentID = 0, startTime = 0, startDate = 0, priority = 0;
        String specialInstruction = "", status = "";
        double price = 0;
        initialiseTable2();
        try {
            String sql = "SELECT * FROM job";
            PreparedStatement prepStat = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            ResultSet rs;
            rs = getGuiControl().getController().getDBC().read(prepStat);
            while (rs.next()){
                jobid = rs.getInt(1);
                custID = rs.getInt(2);
                paymentID = rs.getInt(3);
                startTime = rs.getInt(4);
                startDate = rs.getInt(5);
                priority = rs.getInt(6);
                specialInstruction = rs.getString(7);
                price = rs.getDouble(8);
                status = rs.getString(9);
                defaultTableModel.addRow(new Object[]{jobid, custID, paymentID, startTime, startDate, priority, specialInstruction, price, status});
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddTaskToTaskOfJob(int tasktaskID, int JobjobNumber, int startTime, int duration, String status){
        try{
            String sql = "INSERT INTO `task_of_job`(`TasktaskID`, `JobjobNumber`, `startTime`, `duration`, `status`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, tasktaskID);
            preparedStatement.setInt(2, JobjobNumber);
            preparedStatement.setInt(3, startTime);
            preparedStatement.setInt(4, duration);
            preparedStatement.setString(5, status);
            getGuiControl().getController().getDBC().write(preparedStatement);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddTojobtaskofjob(int JobjobNumber, int TaskOfJobID){
        try {
            String sql = "INSERT INTO `job_task_of_job`(`JobjobNumber`, `Task_of_JobTasktaskID`) VALUES (?,?)";
            PreparedStatement preparedStatement = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, JobjobNumber);
            preparedStatement.setInt(2, TaskOfJobID);
            getGuiControl().getController().getDBC().write(preparedStatement);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public GUIControl getGuiControl() {
        return guiControl;
    }

}
