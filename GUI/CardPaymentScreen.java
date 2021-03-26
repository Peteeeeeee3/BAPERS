package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Payment.Payment;
import Payment.Card;


public class CardPaymentScreen extends JPanel {
    private JCheckBox paidCheckBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel cardPaymentPanel;
    private JTextField Last4Digits;
    private JTextField ExpiryDate;
    private JTextField CardType;
    public GUIControl guiControl;
    public Payment payment;
    public Card card;


    int flag = 0;

    Date convertDate(Integer oldFormat) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date utilDate = originalFormat.parse(oldFormat.toString());
        return new java.sql.Date(utilDate.getTime());
    }

    public CardPaymentScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CardPaymentScreen(guiControl).cardPaymentPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public CardPaymentScreen(GUIControl guiControl) {

        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 0){
                    JOptionPane.showMessageDialog(cardPaymentPanel, "Please click the paid checkbox");
                } else {
                    String cardType = CardType.getText();
                    int last4Digits = Integer.parseInt(Last4Digits.getText());
                    int expiryDate = Integer.parseInt(ExpiryDate.getText());
                    Card card = new Card(cardType, expiryDate, last4Digits, payment);
                    try {
                        convertDate(expiryDate);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    guiControl.getController().getPaymentControl().vecCard.addCard(card);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
        paidCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
