package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Similar to card payments where it doesnt work
public class CashPaymentScreen extends JPanel {

    private JPanel cashPaymentPanel;
    private JCheckBox paidCheckBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel pricaLabel;
    public GUIControl guiControl;
    int flag = 0;

    public CashPaymentScreen(GUIControl guiControl, JFrame frame, float total) {
        this.guiControl = guiControl;
        frame.setContentPane(new CashPaymentScreen(guiControl, total).cashPaymentPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CashPaymentScreen(GUIControl guiControl, float total) {
        this.guiControl = guiControl;
        pricaLabel.setText(total * 1.2f + "GBP, incl. 20% VAT");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 0) {
                    JOptionPane.showMessageDialog(cashPaymentPanel, "Please click the paid checkbox");
                } else {
                    //guiControl.getController().getPaymentControl().addPayment();
                    guiControl.closeCurrentFrame();
                    guiControl.useCashPaymentScreen(guiControl, total);
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
