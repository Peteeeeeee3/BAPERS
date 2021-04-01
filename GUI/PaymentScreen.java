package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Payment.Card;
import Payment.Payment;
import Payment.VectorOfPayments;

public class PaymentScreen extends JPanel {
    private JTextArea amountDueTextArea;
    private JTextField jobIDTextField;
    private JButton cancelButton;
    private JButton cashButton;
    private JButton cardButton;
    public GUIControl guiControl;
    public JPanel panelMain;
    public CardPaymentScreen cardPaymentScreen;

    public PaymentScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        //JFrame frame = new JFrame("Payment");
        frame.setContentPane(new PaymentScreen(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public PaymentScreen(GUIControl guiControl) {
        this.guiControl = guiControl;

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useCashPaymentScreen(guiControl);
            }
        });
        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(amountDueTextArea.getText());
                //guiControl.getController().getPaymentControl().addPayment();
                guiControl.useCardPaymentScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
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
