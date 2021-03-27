package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechHomePage extends JPanel{
    private JButton startTaskButton;
    private JPanel techHomePagePanel;
    private JButton updateTaskButton;
    private JButton viewJobButton;
    private JButton logoutButton;
    private JTextPane WELCOMETextPane;
    public GUIControl guiControl;

    public TechHomePage(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new TechHomePage(guiControl).techHomePagePanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public TechHomePage(GUIControl guiControl){
        this.guiControl = guiControl;
        startTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useStartTaskScreen(guiControl);
            }
        });
        updateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useUpdateTaskScreen(guiControl);
            }
        });
        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useViewJobScreen(guiControl);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
