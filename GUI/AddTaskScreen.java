package GUI;

import javax.swing.*;

//This is where AddTaskToJob should be going
public class AddTaskScreen extends JPanel {
    private JPanel addTaskPanel;
    private JTable taskDescription;
    private JButton cancelButton;
    private JButton confirmButton;
    public GUIControl guiControl;

    public AddTaskScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new AddTaskScreen(guiControl).addTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public AddTaskScreen(GUIControl guiControl) {
        this.guiControl = guiControl;
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
