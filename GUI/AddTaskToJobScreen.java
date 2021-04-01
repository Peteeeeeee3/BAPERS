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
    //Sets the screen visibility size etc...
    public AddTaskToJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new AddTaskToJobScreen(guiControl).addTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }
    //Where the button functions and other functions are defined
    public AddTaskToJobScreen(GUIControl guiControl) {

        this.guiControl = guiControl;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Switches between frames when cancel button is closed using switch case.
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
        //Calls the view all tasks method and view jobs method defined below
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
                //Gets column from selected row when table is displayed
                int column = 0;
                //This gets the row
                int row = table.getSelectedRow();
                //Parses the value at the specific column and row. Assigned to taskID
                int taskID = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
                int columnJobID = 0;
                //At the second table displayed
                int rowJob = table2.getSelectedRow();
                //Parses the value at the specific column and row, assigned to jobID
                int jobID = Integer.parseInt(table2.getModel().getValueAt(rowJob, columnJobID).toString());
                int columnDurat = 4;
                //Creates the object DateTimeFormatter with variable name formatter. Sets the pattern so that it looks like HHmmSS
                //(HOUR, MINUTES, SECONDS)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
                //Creates object LocalTime with variable name `now` to get the localTime
                LocalTime now = LocalTime.now();
                //Sets so that it can be referred to timeString. Accesses `now` to format it according to `formatter`
                String timeString = now.format(formatter);
                //Parses
                int localTime = Integer.parseInt(timeString);
                //Gets whatever is at the current row and column, parses it
                int duration = Integer.parseInt(table.getModel().getValueAt(row, columnDurat).toString());
                //Calls method AddTaskToTaskOfJob with the proper parameters passed
                AddTaskToTaskOfJob(taskID, jobID, localTime, duration, "pending");
                //Calls method AddTojobtaskofjob with the proper parameters passsed
                AddTojobtaskofjob(taskID, jobID);
            }
        });
    }
    //Creates the table first table to be displayed
    public void initialiseTable(){
        //Creates new default table model
        defaultTableModel = new DefaultTableModel();
        //Sets the JTable model to default model. Done so it can access functions addcolumn
        table.setModel(defaultTableModel);
        //Adds the column
        defaultTableModel.addColumn("Task ID");
        defaultTableModel.addColumn("Description");
        defaultTableModel.addColumn("Location");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Duration");
    }
    //Creates second table to be displayed
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
    //Method viewalltasks
    public void viewAllTasks(){
        int taskID = 0, duration = 0;
        String taskDesc = "", location = "";
        double price = 0;
        initialiseTable();
        try{
            //SQL string
            String sql = "SELECT * FROM task";
            //Sets PreparedStatement prepstat, passes through the string sql into prepareStatement, navigates through the code
            PreparedStatement prepStat = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            //Sets ResultSet to rs
            ResultSet rs;
            //Passes through prepstat into the read function. Set rs to that so can perform action on it
            rs = getGuiControl().getController().getDBC().read(prepStat);
            //Loop while still something in the result set
            while (rs.next()){
                //gets the values from database and sets it to proper variables
                taskID = rs.getInt(1);
                taskDesc = rs.getString(2);
                location = rs.getString(3);
                price = rs.getDouble(4);
                duration = rs.getInt(5);
                //Gets the values and adds rows based on the variables, in this specific order for the table on the screen
                defaultTableModel.addRow(new Object[]{taskID, taskDesc, location, price, duration});
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //Exact same as viewTask except for the job table
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
        //Error handling
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //Method to talk to database to get the job task of job. Writes it
    public void AddTojobtaskofjob(int JobjobNumber, int TaskOfJobID){
        try {
            //SQL statement to insert values to the database
            String sql = "INSERT INTO `job_task_of_job`(`JobjobNumber`, `Task_of_JobTasktaskID`) VALUES (?,?)";
            PreparedStatement preparedStatement = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            //Setting prepared statement so that can be pushed to database. 2nd parameter is variable for value added
            preparedStatement.setInt(1, JobjobNumber);
            preparedStatement.setInt(2, TaskOfJobID);
            getGuiControl().getController().getDBC().write(preparedStatement);
        //Error handling
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public GUIControl getGuiControl() {
        return guiControl;
    }

}
