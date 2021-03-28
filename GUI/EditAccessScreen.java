package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditAccessScreen extends JPanel {
    private JTextField textField1;
    private JPanel editAccessPanel;
    private JCheckBox editAccessCheckBox;
    private JButton confirmButton;
    private JButton backButton;
    public GUIControl guiControl;
    int flag = 0;

    public EditAccessScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new EditAccessScreen(guiControl).editAccessPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public EditAccessScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        editAccessCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if (flag == 1){
                    try {
                        guiControl.getController().getAccountControl().updateAccess(id);
                    } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(confirmButton, "Access level has been changed.");
                } else {
                    JOptionPane.showMessageDialog(confirmButton, "Please tick the checkbox.");
                }
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
