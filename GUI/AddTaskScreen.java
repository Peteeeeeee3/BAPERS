package GUI;

import javax.swing.*;

public class AddTaskScreen extends Form {
    private JPanel addTaskPanel;
    private JTable taskDescription;
    private JButton cancelButton;
    private JButton confirmButton;

    public AddTaskScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Add Tasks");
//        frame.setContentPane(new AddTaskScreen().addTaskPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
