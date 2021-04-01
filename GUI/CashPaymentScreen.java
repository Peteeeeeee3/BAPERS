package GUI;

import Account.Customer;

import Job.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Similar to card payments where it doesnt work
public class CashPaymentScreen extends JPanel {

    private JPanel cashPaymentPanel;
    private JCheckBox paidCheckBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel pricaLabel;
    public GUIControl guiControl;
    int flag = 0;

    public CashPaymentScreen(GUIControl guiControl, JFrame frame, float total, Customer customer, Job[] jobs) {
        this.guiControl = guiControl;
        frame.setContentPane(new CashPaymentScreen(guiControl, total, customer, jobs).cashPaymentPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CashPaymentScreen(GUIControl guiControl, float total, Customer customer, Job[] jobs) {
        this.guiControl = guiControl;
        pricaLabel.setText(total * 1.2f + "GBP, incl. 20% VAT");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 0) {
                    JOptionPane.showMessageDialog(cashPaymentPanel, "Please click the paid checkbox");
                } else {
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                        LocalDate date_now = LocalDate.now();
                        int date = Integer.parseInt(dtf.format(date_now));
                        guiControl.getController().getPaymentControl().addPayment(total * 1.2f, date, customer, jobs, 20210402, null, "cash");
                        guiControl.closeCurrentFrame();
                        guiControl.useCashPaymentScreen(guiControl, total, customer, jobs);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                        break;
                    case 2:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                        break;
                    case 3:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                        break;
                    case 4:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                        break;
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
