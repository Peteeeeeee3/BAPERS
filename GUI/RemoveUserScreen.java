package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RemoveUserScreen extends JPanel{
    private JPanel removeUserPanel;
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JButton backButton;
    private JButton confirmButton;
    public GUIControl guiControl;
    int flag = 0;

    public RemoveUserScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new RemoveUserScreen(guiControl).removeUserPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public RemoveUserScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if (flag == 1){
                    getGuiControl().getController().getAccountControl().removeUser(id);
                    JOptionPane.showMessageDialog(confirmButton, "User has been removed");
                } else {
                    JOptionPane.showMessageDialog(confirmButton, "Please tick the remove check box");
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
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
