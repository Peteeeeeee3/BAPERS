package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VariableDiscountScreen extends JPanel {
    private JTable variableDiscountField;
    private JPanel variableDiscountPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    public GUIControl guiControl;

    public VariableDiscountScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new VariableDiscountScreen(guiControl).variableDiscountPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public VariableDiscountScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
