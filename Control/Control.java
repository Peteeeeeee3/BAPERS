package Control;

import Account.AccountControl;
import Account.Customer;
import Database.DBControl;
import GUI.GUIControl;
import Job.*;
import Payment.I_Payment;
import Payment.Payment;
import Payment.PaymentControl;
import Payment.Card;
import Printer.PrinterGateway;
import Report.IndividualPerformanceReport;
import Report.PerformanceSummary;
import Report.*;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Control implements I_Control, I_Payment {

	private int access, userID;
	private DBControl DBC;
	private AccountControl accountControl;
	private PaymentControl paymentControl;
	private JobFacadeControl jobControl;
	private ReportFacadeControl reportFacadeControl;
	private PrinterGateway printerGateway;
	private GUIControl guiControl;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, ParseException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl(controller);
		controller.paymentControl = new PaymentControl(controller);
		controller.jobControl = new JobFacadeControl();
		controller.reportFacadeControl = new ReportFacadeControl(controller);
		controller.printerGateway = new PrinterGateway();
		//make a window
		JFrame window = new JFrame();
		// set this to a GUIControl
		controller.guiControl = new GUIControl(controller, window);
		//make it be the login screen
		controller.guiControl.useLogin(window);
		window.setVisible(true);

		////Test Login Start (do not remove)////
		//controller.accountControl.login(6, "password6");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("Oxford","Hanan","32 oxford street",755551315);

		//Test for Create User//
		//controller.accountControl.createUser(8, "password1", "farhan", 1);

		////test Individual Report (do not remove)////
//		IndividualPerformanceReport ipr = new IndividualPerformanceReport(20201206, 20201224, controller.reportFacadeControl);
//		ipr.setRFC(controller.reportFacadeControl);
//		for (int rowsItr = 0; rowsItr < ipr.getNames().size(); rowsItr++) {
//			System.out.println(ipr.getNames().get(rowsItr) + " " + ipr.getStaffIDs().get(rowsItr) + " " +
//					ipr.getTaskIDs().get(rowsItr) + " " + ipr.getLocations().get(rowsItr) + " " +
//					ipr.getDates().get(rowsItr) + " " + ipr.getStartTimes().get(rowsItr) + " " +
//					ipr.getDurations().get(rowsItr));
//		}
//
//		for (java.util.Map.Entry<String, Integer> stringIntegerEntry : ipr.getTotalTimes().entrySet()) {
//			System.out.println(((HashMap.Entry) stringIntegerEntry).getKey() + " " + ((HashMap.Entry) stringIntegerEntry).getValue());
//		}
		////test code end////

		////test Summary Report (do not remove)////
//		PerformanceSummary ps = new PerformanceSummary(20201209, 20201223, controller.reportFacadeControl);
//		ps.setRFC(controller.reportFacadeControl);
//		for (int i = 0; i < ps.getDays().size(); i++) {
//			for (int j = 0; j < ps.getDays().get(i).size(); j++) {
//				for (int k : ps.getDays().get(i).get(j)) {
//					if (k != 0) {
//						int temp = ps.getStartDate() + i;
//						System.out.println(temp + " " + k);
//					}
//				}
//			}
//		}
		////test code end////

        ////Customer Job report print test////
        //controller.printerGateway.print(new CustomerJobReport("Kiandra Markus", 20210321, controller.reportFacadeControl));
		//controller.printerGateway.print(new CustomerJobReport("Mark Sailor", 20210321, controller.reportFacadeControl));
        ////test code end////

		////Individual Performance Report print test////
		//controller.printerGateway.print(new IndividualPerformanceReport(20000101, 20220101, controller.reportFacadeControl));
		////test code end////

		////Performance Summary report print test////
<<<<<<< HEAD
		//controller.printerGateway.print(new PerformanceSummary(20210301, 20210320, controller.reportFacadeControl));
=======
		//controller.printerGateway.print(new PerformanceSummary(20201201, 20210320, controller.reportFacadeControl));
>>>>>>> 0f2ab72144e6cdf1daf62d38aaae1561599c63bc
		////test code tend////
	}



	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	public DBControl getDBC() {
		return DBC;
	}

	public AccountControl getAccountControl() {
		return accountControl;
	}

	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}

	public PaymentControl getPaymentControl() {
		return paymentControl;
	}

	public boolean login(int id, String password) {
		try {
			return accountControl.login(id, new String(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Payment retrievePayment(int iD) {
		return null;
	}

	@Override
	public Payment[] retrieveListOfPayments(Customer customer) {
		return new Payment[0];
	}

	@Override
	public Card[] retrieveCards(Customer customer) {
		return new Card[0];
	}

	@Override
	public void generateInvoice() {

	}

	@Override
	public void addPayment(int amount, int date, Customer customer, Job[] jobs, int dueDate, Card card) throws ClassNotFoundException, SQLException, InstantiationException, ParseException, IllegalAccessException {
		paymentControl.addPayment(amount, date, customer, jobs, dueDate, card);
	}
}