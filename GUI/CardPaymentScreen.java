package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Payment.Payment;
import Payment.Card;
import Report.Invoice;
import Report.Report;

//Card Payment needs work. Currently doesnt work so please do change up where needed. Can delete whole functions from here
public class CardPaymentScreen extends JPanel {
    private JCheckBox paidCheckBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel cardPaymentPanel;
    private JTextField Last4Digits;
    private JTextField ExpiryDate;
    private JTextField CardType;
    private JLabel priveLabel;
    public GUIControl guiControl;
    public Payment payment;
    public Card card;
    private float total;
    private Report report;


    int flag = 0;

    Date convertDate(Integer oldFormat) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date utilDate = originalFormat.parse(oldFormat.toString());
        return new java.sql.Date(utilDate.getTime());
    }

    public CardPaymentScreen(GUIControl guiControl, JFrame frame, float total) {
        this.guiControl = guiControl;
        this.total = total;
        frame.setContentPane(new CardPaymentScreen(guiControl, total, (Invoice) report).cardPaymentPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CardPaymentScreen(GUIControl guiControl, float total, Invoice report) {
        this.guiControl = guiControl;
        this.total = total;
        this.report = report;
        priveLabel.setText(total * 1.2f + "GBP, incl. 20% VAT");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 0) {
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
                    guiControl.getController().getPrinterGateway().print(report);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                    }
                    case 2 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                    }
                    case 3 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                    }
                    case 4 -> {
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                    }
                }
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
