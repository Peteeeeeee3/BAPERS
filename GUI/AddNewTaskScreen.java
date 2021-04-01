package GUI;

import Account.OfficeManager;

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
    private JTextField location;
    private JTextField price;
    private JTextField duration;
    private JTextField idTextField;
    public GUIControl guiControl;

    //Setting up how the GUI works here
    public AddNewTaskScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new AddNewTaskScreen(guiControl).addNewTaskPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }


    public AddNewTaskScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        // When creating a button the action listener goes here and functions go inside the action listener
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = descriptionTextField.getText();
                String loc = location.getText();
                //Getting the values from the text fields
                int durat = Integer.parseInt(duration.getText());
                float p = Float.parseFloat(price.getText());
                //Calling the method from job control
                getGuiControl().getController().getJobControl().addTask(desc, loc, p, durat);
                JOptionPane.showMessageDialog(confirmButton, "The follow tasks have been added");

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.openPreviousFrame();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
