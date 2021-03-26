package GUI;

import javax.swing.*;

public class TaskManageScreen extends Form {
    private JPanel taskManagePanel;
    private JButton startTaskButton;
    private JButton updateTaskButton;
    private JButton viewJobButton;
    private JButton backButton;

    public TaskManageScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Manage Tasks");
//        frame.setContentPane(new TaskManageScreen().taskManagePanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
