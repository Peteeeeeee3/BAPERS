package GUI;

import javax.swing.*;

public class CustomerJobsReport extends Form {
    private JButton backButton;
    private JTextField textField1;
    private JButton goButton;
    private JTable table1;
    private JButton nextButton;
    public JPanel panelMain;

    public CustomerJobsReport(GUIControl guiControl) {
        super(guiControl);
    }


//    public static void main(String[] args){
//        JFrame frame = new JFrame("CustomerJobsReport");
//        frame.setContentPane(new CustomerJobsReport().panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }
}