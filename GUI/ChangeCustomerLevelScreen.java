package GUI;

import javax.swing.*;

public class ChangeCustomerLevelScreen extends Form {
    private JButton upgradeCustomerButton;
    private JPanel configureCustomerPanel;
    private JButton downgradeCustomerButton;
    private JButton createCustomerButton;
    private JButton backButton;

    public ChangeCustomerLevelScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Change Customer Levels");
//        frame.setContentPane(new ChangeCustomerLevelScreen().configureCustomerPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
