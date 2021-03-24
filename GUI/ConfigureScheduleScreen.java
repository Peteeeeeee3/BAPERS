package GUI;

import javax.swing.*;

public class ConfigureScheduleScreen extends Form {
    private JPanel configureSchedulePanel;
    private JComboBox dropdownList;
    private JTextArea intervalTextArea;
    private JButton cancelButton;
    private JButton confirmButton;

    public ConfigureScheduleScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Configure Schedule");
//        frame.setContentPane(new ConfigureScheduleScreen().configureSchedulePanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
