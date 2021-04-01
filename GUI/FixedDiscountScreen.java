package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FixedDiscountScreen extends JPanel {
    private JPanel fixedDiscountPanel;
    private JTextField amountField;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextArea formatTextArea;
    public GUIControl guiControl;
    public UpgradeCustomerScreen upgradeCustomerScreen;

    public FixedDiscountScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new FixedDiscountScreen(guiControl).fixedDiscountPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public FixedDiscountScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float amount = Integer.parseInt(amountField.getText());
                int id = Integer.parseInt(upgradeCustomerScreen.searchTextField.getText());
                if(amountField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(confirmButton, "Please enter an amount.");
                } else {
                    try {
                        getGuiControl().getController().getAccountControl().vecAcc.defineFlatDiscount(id, amount);
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(confirmButton, "Confirmed");
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
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
