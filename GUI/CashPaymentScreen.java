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
    public GUIControl guiControl;
    int flag = 0;

    public CashPaymentScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CashPaymentScreen(guiControl).cashPaymentPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CashPaymentScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 0){
                    JOptionPane.showMessageDialog(cashPaymentPanel, "Please click the paid checkbox");
                } else {
                    //guiControl.getController().getPaymentControl().addPayment();
                    guiControl.closeCurrentFrame();
                    guiControl.useCashPaymentScreen(guiControl);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useCashPaymentScreen(guiControl);
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
