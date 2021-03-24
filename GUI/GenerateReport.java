package GUI;

import javax.swing.*;

public class GenerateReport extends Form {
    private JButton backButton;
    private JButton individualReportButton;
    private JButton peformanceSummaryReportButton;
    private JButton customerJobsReportButton;
    public JPanel panelMain;

    public GenerateReport(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args){
//        JFrame frame = new JFrame("GenerateReport");
//        frame.setContentPane(new GenerateReport().panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }
}
