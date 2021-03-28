package GUI;

import javax.swing.*;

public class CustomerJobsReport extends JPanel {
    private JButton backButton;
    private JTextField textField1;
    private JButton goButton;
    private JTable table1;
    private JButton nextButton;
    public JPanel panelMain;
    public GUIControl guiControl;

    public CustomerJobsReport(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CustomerJobsReport(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CustomerJobsReport(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
