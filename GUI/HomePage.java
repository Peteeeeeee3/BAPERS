package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    private JTextPane welcomeMessageTextPane;
    private JButton newJobButton;
    private JButton paymentButton;
    private JButton viewJobButton;
    private JButton logoutButton;
    public JPanel panelMain;
    public GUIControl guiControl;

    public HomePage(GUIControl guiControl, JFrame frame) {
        //Initialise//
        this.guiControl = guiControl;
        frame.setContentPane(new HomePage(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

    public HomePage(GUIControl guiControl){
        this.guiControl = guiControl;

        newJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.useSearchCustomerScreen(guiControl);
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.usePaymentScreen(guiControl);
            }
        });

        viewJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });

    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
