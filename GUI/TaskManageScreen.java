package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManageScreen extends JPanel {
    private JPanel taskManagePanel;
    private JButton startTaskButton;
    private JButton updateTaskButton;
    private JButton viewJobButton;
    private JButton backButton;
    private JButton newJobButton;
    public GUIControl guiControl;

    public TaskManageScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new TaskManageScreen(guiControl).taskManagePanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public TaskManageScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        newJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useSearchCustomerScreen(guiControl);
            }
        });
        startTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useStartTaskScreen(guiControl);
            }
        });
        updateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useUpdateJobScreen(guiControl);
            }
        });
        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useViewJobScreen(guiControl);
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

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
