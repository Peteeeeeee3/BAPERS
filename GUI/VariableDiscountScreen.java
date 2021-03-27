package GUI;

import javax.swing.*;

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

    public VariableDiscountScreen(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
