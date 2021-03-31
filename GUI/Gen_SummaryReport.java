package GUI;

import Report.PerformanceSummary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gen_SummaryReport {
    private GUIControl guiControl;
    private JTextArea ta_startDate;
    private JPanel gen_sumPanel;
    private JTextArea ta_endDate;
    private JButton cancelButton;
    private JButton generateButton;

    public Gen_SummaryReport(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new Gen_SummaryReport(guiControl).gen_sumPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public Gen_SummaryReport(GUIControl guiControl) {
        this.guiControl = guiControl;
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useSummaryReportDisplay(guiControl, new PerformanceSummary(Integer.parseInt(ta_startDate.getText()),
                        Integer.parseInt(ta_endDate.getText()), guiControl.getController().getReportFacadeControl()));
            }
        });
    }
}
