package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsScreen extends JPanel {
    private JButton configureScheduleButton;
    private JPanel reportsPanel;
    private JButton generateReportButton;
    private JButton cancelButton;
    public GUIControl guiControl;

    public ReportsScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new ReportsScreen(guiControl).reportsPanel);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,300);
            frame.setVisible(true);
    }



    public ReportsScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useGenerateReportScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}

