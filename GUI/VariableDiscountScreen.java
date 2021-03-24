package GUI;

import javax.swing.*;

public class VariableDiscountScreen extends Form {
    private JTable variableDiscountField;
    private JPanel variableDiscountPanel;
    private JButton cancelButton;
    private JButton confirmButton;

    public VariableDiscountScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Variable Discount Screen");
//        frame.setContentPane(new VariableDiscountScreen().variableDiscountPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
