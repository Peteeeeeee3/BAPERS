package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Configure schedule needs to be worked on
public class ConfigureScheduleScreen extends JPanel {
    private JPanel configureSchedulePanel;
    private JComboBox dropdownList;
    private JTextField intervalTextField;
    private JButton cancelButton;
    private JButton confirmButton;
    public GUIControl guiControl;
    public ComboItem configureScheduleComboBox;
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
        dropdownList.addItem(new ComboItem("Days", "days"));
        dropdownList.addItem(new ComboItem("Hours", "hours"));
        dropdownList.addItem(new ComboItem("Weeks", "weeks"));

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(intervalTextField.getText());
            }
        });
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
        dropdownList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object item = dropdownList.getSelectedItem();
                String value = ((ComboItem)item).getValue();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
