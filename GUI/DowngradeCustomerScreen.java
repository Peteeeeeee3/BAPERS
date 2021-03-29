package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DowngradeCustomerScreen extends JPanel {
    public GUIControl guiControl;
    private JTextField textField1;
    private JButton searchButton;
    private JTable table1;
    private JButton backButton;
    private JButton downgradeButton;
    int flag = 0;

    public DowngradeCustomerScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new SearchCustomerScreen(guiControl).searchCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public DowngradeCustomerScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                try {
                    guiControl.getController().getAccountControl().receptionist.searchCustomer(id);
                    flag = 1;
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        downgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if(flag == 1){
                    try {
                        guiControl.getController().getAccountControl().getOfficeManager().upgradeCustomer(id);
                        JOptionPane.showMessageDialog(downgradeButton, "Customer has been downgraded");
                    } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

}
