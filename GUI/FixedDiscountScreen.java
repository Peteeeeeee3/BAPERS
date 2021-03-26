package GUI;

import javax.swing.*;

public class FixedDiscountScreen extends Form {
    private JPanel fixedDiscountPanel;
    private JTextField amountField;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextArea formatTextArea;

    public FixedDiscountScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Fixed Discount Screen");
//        frame.setContentPane(new FixedDiscountScreen().fixedDiscountPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
