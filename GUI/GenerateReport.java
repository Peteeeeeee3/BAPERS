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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                    }
                    case 2 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                    }
                    case 3 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                    }
                    case 4 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                    }
                }
            }
        });
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
                guiControl.useGenPerformanceSummary(guiControl);
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
