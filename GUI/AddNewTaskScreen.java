package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddNewTaskScreen extends JPanel {
    private JTextField taskIDTextField;
    private JTextField descriptionTextField;
    private JButton backButton;
    private JButton confirmButton;
    private JPanel addNewTaskPanel;
    public GUIControl guiControl;

    public AddNewTaskScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new AddNewTaskScreen(guiControl).addNewTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(taskIDTextField.getText());
                String desc = descriptionTextField.getText();
                try {
                    if(taskIDTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(confirmButton, "Please fill out all fields");
                    } else {
                        guiControl.getController().getJobControl().addTask(id);
                        JOptionPane.showMessageDialog(confirmButton, "The follow tasks have been added");
                    }
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
    }

    public AddNewTaskScreen(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
