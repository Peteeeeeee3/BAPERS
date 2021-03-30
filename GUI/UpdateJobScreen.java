package GUI;

import javax.swing.*;

public class UpdateJobScreen extends JPanel {
    private JButton backButton;
    private JTable JButton;
    public GUIControl guiControl;

    public JPanel panelMain;
    private JButton showButton;

    public UpdateJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new UpdateJobScreen(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public UpdateJobScreen(GUIControl guiControl){
        this.guiControl = guiControl;

    }

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
