package GUI;

import javax.swing.*;

public class UpdateTask extends JPanel {
    private JButton backButton;
    private JTable JButton;
    public GUIControl guiControl;

    public JPanel panelMain;

    public UpdateTask(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new UpdateTask(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public UpdateTask(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
