package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Account.Customer;
import Job.Job;
import Report.Report;

public class PaymentScreen extends JPanel {
    private JTextArea amountDueTextArea;
    private JButton cancelButton;
    private JButton cashButton;
    private JButton cardButton;
    public GUIControl guiControl;
    public JPanel panelMain;
    private JTable table1;
    private JLabel priceField;
    public CardPaymentScreen cardPaymentScreen;
    private Job[] jobs = new Job[1000];
    private float total = 0;


    public PaymentScreen(GUIControl guiControl, JFrame frame, Customer customer) {
        this.guiControl = guiControl;
        //JFrame frame = new JFrame("Payment");
        frame.setContentPane(new PaymentScreen(guiControl, customer).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public PaymentScreen(GUIControl guiControl, Customer customer) {
        this.guiControl = guiControl;

        try {
            String sql = "SELECT * FROM job WHERE CustomeraccountNo = ? AND status = \"completed\"";
            PreparedStatement preparedStatement = guiControl.getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, customer.getAccountNo());
            ResultSet rs = guiControl.getController().getDBC().getDBGateway().read(preparedStatement);

            DefaultTableModel defaultTableModel = new DefaultTableModel();
            table1.setModel(defaultTableModel);
            defaultTableModel.addColumn("Job Number");
            defaultTableModel.addColumn("Price");
            defaultTableModel.setColumnIdentifiers(new Object[]{"Job Number", "Price"});
            //Sets row to proper database rows. Gets from the result set
            int i = 0;
            while (rs.next()) {
                defaultTableModel.addRow(new Object[]{rs.getInt(1), rs.getFloat(8)});
                jobs[i] = new Job(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getFloat(8), rs.getString(9));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table1.addMouseListener(new TableMouseListener(this));
        table1.setCellSelectionEnabled(true);

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
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.useCashPaymentScreen(guiControl, total, customer, jobs);
            }
        });
        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int amount = Integer.parseInt(amountDueTextArea.getText());
                //guiControl.getController().getPaymentControl().addPayment();
                guiControl.useCardPaymentScreen(guiControl, total, customer, jobs);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }

    public Job[] getJobs() {
        return jobs;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public JLabel getPriceField() {
        return priceField;
    }

    //    public static void main(String[] args){
//        JFrame frame = new JFrame("Payment");
//        frame.setContentPane(new Payment(amountDueTextArea).panelMain);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setVisible(true);
//    }
}
