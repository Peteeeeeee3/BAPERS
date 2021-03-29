package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpgradeCustomerScreen extends JPanel {
    public GUIControl guiControl;
    public JTextField searchTextField;
    private JButton searchButton;
    private JTable table1;
    private JButton backButton;
    private JButton nextButton;
    private JPanel upgradeCustomerPanel;
    int flag = 0;

    public UpgradeCustomerScreen(GUIControl guiControl, JFrame frame){
    this.guiControl = guiControl;
        frame.setContentPane(new SearchCustomerScreen(guiControl).searchCustomerPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public UpgradeCustomerScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchTextField.getText());
                //try {
                //    guiControl.getController().getAccountControl().receptionist.searchCustomer(id);
                //    flag = 1;
                //} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException classNotFoundException) {
                //    classNotFoundException.printStackTrace();
                //}
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
                guiControl.openPreviousFrame();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchTextField.getText());
                if (flag == 1){
                    //try {
                    //    getGuiControl().getController().getAccountControl().getOfficeManager().upgradeCustomer(id);
                    //} catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                    //    throwables.printStackTrace();
                    //}
                    //guiControl.closeCurrentFrame();
                    //guiControl.useSelectDiscountScreen(guiControl);
                }
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }


}
