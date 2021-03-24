package GUI;

import javax.swing.*;

public class HomePage extends Form {
    private JTextPane welcomeMessageTextPane;
    private JButton newJobButton;
    private JButton paymentButton;
    private JButton viewJobButton;
    private JButton logoutButton;
    public JPanel panelMain;

    public HomePage(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String[] args){
//        JFrame frame = new JFrame("HomePage");
//        frame.setContentPane(new HomePage().panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }
}
