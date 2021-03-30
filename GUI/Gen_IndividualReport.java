package GUI;

import javax.swing.*;

public class Gen_IndividualReport extends JPanel {
    private JButton backButton;
    private JTextField staffIDTextField;
    private JTextField mmDdYyyyTextField;
    private JTextField mmDdYyyyTextField1;
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

    public Gen_IndividualReport(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
