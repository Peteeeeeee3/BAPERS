package GUI;

import javax.swing.*;

public class CashPaymentScreen extends Form {


    private JPanel cashPaymentPanel;
    private JCheckBox paidCheckBox;
    private JButton confirmButton;
    private JButton cancelButton;

    public CashPaymentScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Payment Screen");
//        frame.setContentPane(new CashPaymentScreen().cashPaymentPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
