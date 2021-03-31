package GUI;

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
    private JComboBox comboBox1;
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
        comboBox1.addItem(new ComboItem("Pending", "pending"));
        comboBox1.addItem(new ComboItem("In Progress", "in progress"));
        comboBox1.addItem(new ComboItem("Completed", "completed"));
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
                if (status.equals("pending") || status.equals("in progress") || status.equals("completed")){
                    try {
                        if (!getGuiControl().getController().getJobControl().vecTaskForJob.checkStatus(status)){
                            JOptionPane.showMessageDialog(showButton, "No tasks with entered status");
                        } else {
                            viewTasksForJob(status);
                        }
                    } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(showButton, "Please enter a correct status");
                }
            }
        });

        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int columnCompare = 1;
                int row = table.getSelectedRow();
                int value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
                int valueJob = Integer.parseInt(table.getModel().getValueAt(row, columnCompare).toString());
                Object item = comboBox1.getSelectedItem();
                assert item != null;
                String status = ((ComboItem)item).getValue();
                getGuiControl().getController().getJobControl().vecTaskForJob.setStatus(status, value, valueJob);
            }
        });
    }

    public void initialiseTable(){
        defaultTableModel = new DefaultTableModel();
        table.setModel(defaultTableModel);
        defaultTableModel.addColumn("Task ID");
        defaultTableModel.addColumn("Job Number");
        defaultTableModel.addColumn("Start Time");
        defaultTableModel.addColumn("Duration");
        defaultTableModel.addColumn("Status");
    }

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
