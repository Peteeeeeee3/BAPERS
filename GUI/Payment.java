package GUI;

import javax.swing.*;

public class Payment extends Form {
    private JTextArea amountDueTextArea;
    private JTextField jobIDTextField;
    private JButton cancelButton;
    private JButton cashButton;
    private JButton cardButton;

    public JPanel panelMain;

    public Payment(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args){
//        JFrame frame = new JFrame("Payment");
//        frame.setContentPane(new Payment(amountDueTextArea).panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }
}
