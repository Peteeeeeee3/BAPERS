package GUI;

import Account.Customer;
import Report.CustomerJobReport;
import com.itextpdf.text.pdf.PdfPCell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomerJobsReport extends JPanel {
    private JButton backButton;
    private JTextField textField1;
    private JButton goButton;
    private JTable table1;
    private JButton nextButton;
    public JPanel panelMain;
    public GUIControl guiControl;
    private DefaultTableModel defaultTableModel;
    private Customer customer;

    public CustomerJobsReport(GUIControl guiControl, JFrame frame) {
        this.guiControl = guiControl;
        frame.setContentPane(new CustomerJobsReport(guiControl).panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public CustomerJobsReport(GUIControl guiControl) {
        this.guiControl = guiControl;
        backButton.addActionListener(new ActionListener() {
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
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creates object customer from return value search customer. Avoids null pointer exception. Navigates through all packages
                customer = guiControl.getController().getAccountControl().vecAcc.searchCustomer(Integer.parseInt(textField1.getText()));
                String valued = "";
                if (customer.getValued() == 1) {
                    valued += "Valued";
                } else {
                    valued += "Regular";
                }
                //Creates the table model so that a table can be dispalyed
                defaultTableModel = new DefaultTableModel();
                table1.setModel(defaultTableModel);
                //Sets the column names
                defaultTableModel.addColumn("Account Number");
                defaultTableModel.addColumn("Name");
                defaultTableModel.addColumn("Phone");
                defaultTableModel.addColumn("Customer Type");
                //Checks to see if a customer name exists or not. If it doeesnt exist then it shows N/A as the
                if (customer.getName().equals("")) {
                    defaultTableModel.addRow(new Object[]{"N/A", "N/A", "N/A", "N/A"});
                } else {
                //Otherwise gets the values from customer class, and adds them as the rows
                    defaultTableModel.addRow(new Object[]{
                            customer.getAccountNo(), customer.getName(), customer.getPhone(), valued
                    });
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Error prevention.
                if (customer != null) {
                    //Formats date
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate date = LocalDate.now();
                    //Creates report to display on next screen
                    guiControl.useReportDisplayScreen(guiControl, new CustomerJobReport(customer.getName(),
                            Integer.parseInt(formatter.format(date).toString()), guiControl.getController().getReportFacadeControl()));
                }
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
