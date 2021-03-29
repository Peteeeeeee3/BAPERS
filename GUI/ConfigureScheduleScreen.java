package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigureScheduleScreen extends JPanel {
    private JPanel configureSchedulePanel;
    private JComboBox dropdownList;
    private JTextField intervalTextField;
    private JButton cancelButton;
    private JButton confirmButton;
    public GUIControl guiControl;
    public ComboBoxClass configureScheduleComboBox;
    int flag = 0;

    public ConfigureScheduleScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new ConfigureScheduleScreen(guiControl).configureSchedulePanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public ConfigureScheduleScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        dropdownList.addItem(new ComboBoxClass("Days", "days"));
        dropdownList.addItem(new ComboBoxClass("Hours", "hours"));
        dropdownList.addItem(new ComboBoxClass("Weeks", "weeks"));

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(intervalTextField.getText());
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        dropdownList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object item = dropdownList.getSelectedItem();
                String value = ((ComboBoxClass)item).getValue();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
