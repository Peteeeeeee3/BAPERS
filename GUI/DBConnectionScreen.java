package GUI;

import javax.swing.*;

public class DBConnectionScreen extends JPanel {
    private JTextField textField1;
    private JPanel dbConnectionPanel;
    private JButton enterButton;
    public GUIControl guiControl;

    public DBConnectionScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new DBConnectionScreen(guiControl).dbConnectionPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public DBConnectionScreen(GUIControl guiControl){
        this.guiControl = guiControl;
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
