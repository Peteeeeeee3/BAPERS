package GUI;

import Job.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateJobScreen extends JPanel {
    private JButton backButton;
    private JTable table;
    public GUIControl guiControl;
    public JPanel panelMain;
    private JButton showButton;
    private JButton updateJobButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    public Job job;
    DefaultTableModel defaultTableModel;
    int flag = 0;

    public UpdateJobScreen(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new UpdateJobScreen(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public UpdateJobScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        comboBox1.addItem(new ComboItem("Pending", "pending"));
        comboBox1.addItem(new ComboItem("In Progress", "in progress"));
        comboBox1.addItem(new ComboItem("Completed", "completed"));
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = textField1.getText();
                if(status.equals("pending") || status.equals("in progress") || status.equals("completed")){
                    try {
                        if (!getGuiControl().getController().getJobControl().vecJobs.checkStatus(status)){
                            JOptionPane.showMessageDialog(showButton, "No jobs with entered status");
                        } else {
                            try {
                                viewActiveJobs(status);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(showButton, "Please choose a correct status");
                }
            }
        });
        updateJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table.getSelectedRow();
                int value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
                Object item = comboBox1.getSelectedItem();
                assert item != null;
                String status = ((ComboItem)item).getValue();
                getGuiControl().getController().getJobControl().vecJobs.setStatus(status, value);
            }
        });
    }

    public void initialiseTable(){
        defaultTableModel = new DefaultTableModel();
        table.setModel(defaultTableModel);
        defaultTableModel.addColumn("Job Number");
        defaultTableModel.addColumn("Customer Account No");
        defaultTableModel.addColumn("Payment ID");
        defaultTableModel.addColumn("Start Time");
        defaultTableModel.addColumn("Start Date");
        defaultTableModel.addColumn("Priority");
        defaultTableModel.addColumn("Special Instructions");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Status");
    }

    public void viewActiveJobs(String status) throws SQLException {
        int JobNumber = 0, CustomerAccountNum = 0, PaymentpaymentID = 0, startTime = 0, startDate = 0, priority = 0;
        String specialInstructions = "";
        double price = 0;

        initialiseTable();

        try {
            String sql = "SELECT * FROM job WHERE status = ?";
            PreparedStatement stmt = getGuiControl().getController().getDBC().getDBGateway().getConnection().prepareStatement(sql);
            ResultSet result;
            stmt.setString(1, status);
            result = getGuiControl().getController().getDBC().read(stmt);

            while (result.next()) {

                JobNumber = result.getInt(1);
                System.out.println(JobNumber);

                CustomerAccountNum = result.getInt(2);
                System.out.println(CustomerAccountNum);

                PaymentpaymentID = result.getInt(3);
                System.out.println(PaymentpaymentID);

                startTime = result.getInt(4);
                System.out.println(startTime);

                startDate = result.getInt(5);
                System.out.println(startDate);

                priority = result.getInt(6);
                System.out.println(priority);

                specialInstructions = result.getString(7);
                System.out.println(specialInstructions);

                price = result.getDouble(8);
                System.out.println(price);

                status = result.getString(9);
                System.out.println(status);
                defaultTableModel.addRow(new Object[]{JobNumber, CustomerAccountNum, PaymentpaymentID, startTime, startDate, priority, specialInstructions, price, status});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
