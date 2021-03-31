package GUI;

import Report.Report;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Report.*;

public class Gen_IndividualReport extends JPanel {
    private JButton backButton;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton generateButton;
    private JPanel individualReportsPanel;
    public GUIControl guiControl;

    public JPanel panelMain;

    public Gen_IndividualReport(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new Gen_IndividualReport(guiControl).individualReportsPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public Gen_IndividualReport(GUIControl guiControl){
        this.guiControl = guiControl;
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useReportDisplayScreen(guiControl, new IndividualPerformanceReport(Integer.parseInt(startDateField.getText()), Integer.parseInt(endDateField.getText()), guiControl.getController().getReportFacadeControl()));
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
