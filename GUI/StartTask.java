package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public StartTask(GUIControl guiControl){
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGuiControl().openPreviousFrame();
                //getGuiControl().closeCurrentFrame();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
