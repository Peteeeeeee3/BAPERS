package GUI;

import Job.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTaskScreen extends JPanel{
    private JTable table1;
    private JButton backButton;
    private JButton removeButton;
    private JButton addButton;
    private JPanel viewTaskPanel;
    private JTextField textField1;
    private JButton searchButton;
    public GUIControl guiControl;
    DefaultTableModel defaultTableModel;
    int flag = 0;
    public Task task;

    public ViewTaskScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new ViewTaskScreen(guiControl).viewTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public ViewTaskScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //guiControl.getController().getJobControl().removeTask();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGuiControl().closeCurrentFrame();
                getGuiControl().useAddNewTaskScreen(guiControl);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                task = getGuiControl().getController().getJobControl().vecTasks.viewTask(id);
                FetchData();
            }
        });
    }

    public void FetchData(){
        defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        defaultTableModel.addColumn("Task ID");
        defaultTableModel.addColumn("Task Description");
        defaultTableModel.addColumn("Location");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Duration");

        defaultTableModel.addRow(new Object[]{task.getTaskID() ,task.getDescription(), task.getLocation(), task.getPrice(), task.getDuration()});
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
