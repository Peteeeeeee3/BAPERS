package GUI;

import javax.swing.*;

public class StartTask extends JPanel {
    private JButton backButton;
    private JTable JTable;
    public GUIControl guiControl;

    public JPanel panelMain;

    public StartTask(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new StartTask(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public StartTask(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
