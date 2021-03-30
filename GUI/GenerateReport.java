package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReport extends JPanel {
    private JButton backButton;
    private JButton individualReportButton;
    private JButton peformanceSummaryReportButton;
    private JButton customerJobsReportButton;
    public JPanel panelMain;
    public GUIControl guiControl;

    public GenerateReport(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new GenerateReport(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public GenerateReport(GUIControl guiControl){
        this.guiControl = guiControl;
        individualReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useGenIndividReportScreen(guiControl);
            }
        });
        peformanceSummaryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useGenIndividReportScreen(guiControl);
            }
        });
        customerJobsReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useCustJobReport(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
