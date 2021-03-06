package Control;

import Account.AccountControl;
import Account.Customer;
import Account.OfficeManager;
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

public class Control implements I_Control {

	//Variables.
	private int access, userID;
	private DBControl DBC;
	private AccountControl accountControl;
	private PaymentControl paymentControl;
	private JobFacadeControl jobControl;
	private ReportFacadeControl reportFacadeControl;
	private PrinterGateway printerGateway;
	private GUIControl guiControl;

	//main class to run entire program.
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, ParseException {
		//controller setup
		Control controller = new Control();
		controller.accountControl = new AccountControl(controller);
		controller.paymentControl = new PaymentControl(controller);
		controller.jobControl = new JobFacadeControl(controller);
		controller.reportFacadeControl = new ReportFacadeControl(controller);
		controller.printerGateway = new PrinterGateway();

		//make a window
		JFrame window = new JFrame();
		// set this to a GUIControl
		controller.guiControl = new GUIControl(controller, window);
		//make it be the login screen on launch.
		controller.guiControl.useLogin(window);
		//makes window visibile.
		window.setVisible(true);

		////Test Login Start (do not remove)////
		//controller.accountControl.login(6, "password6");
		////Test Login End////

		//Test for Creat Customer//
		//controller.accountControl.createCustomer("BMW","Marko Hentel","76 Baker Road",759372845);

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

		//controller.printerGateway.print(new PerformanceSummary(20210301, 20210320, controller.reportFacadeControl));
//		controller.getAccountControl().createCustomer("Microsoft", "Mark Alexander", "1 Lukas Street, FS8 62H", 989767656);
//		Job job = new Job(2, "A", 1400, 24, controller.accountControl.vecAcc.customers.get(0).getJobs());
//		job.vecTaskJ.addTask(new TaskForJob(new Task("Copy Room", "Hi", 5.99f, 5, controller.getJobControl().vecTasks)));
//		job.vecTaskJ.addTask(new TaskForJob(new Task("Copy Room", "Bye", 5.99f, 5, controller.getJobControl().vecTasks)));
//      controller.printerGateway.print(new Invoice("Julie Jolly", "No Company", "Everywhere Road 27", 2, 1, 1456634535, 20210320, job, 0.4f));
		//controller.printerGateway.print(new PerformanceSummary(20201201, 20210320, controller.reportFacadeControl));
		////test code tend////
//		try {
//			controller.getDBC().getDBGateway().backupToDB();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println(controller.getDBC().getDBGateway().checkOS());
	}

	//control method
	public Control() throws ClassNotFoundException, SQLException {
		DBC = new DBControl();
	}

	//get Database control.
	public DBControl getDBC() {
		return DBC;
	}

	//get Account Control.
	public AccountControl getAccountControl() {
		return accountControl;
	}

	//set account control.
	public void setAccountControl(AccountControl accountControl) {
		this.accountControl = accountControl;
	}

	//get payment control.
	public PaymentControl getPaymentControl() {
		return paymentControl;
	}

	//set JobControl.
	public void setJobControl(JobFacadeControl jobControl){this.jobControl = jobControl;}

	//get Job Control.
	public JobFacadeControl getJobControl(){ return jobControl; }

	//login method.
	public boolean login(int id, String password) {
		try {
			//returns id and password.
			return accountControl.login(id, new String(password));
		} catch (Exception e) {
			//handle exceptions.
			e.printStackTrace();
		}
		return false;
	}
	//Edit access.
	public OfficeManager editAccess(int id, int access){
		try{
			accountControl.updateAccess(id, access);
		} catch (Exception e){
			//handle exceptions.
			e.printStackTrace();
		}
		return null;
	}

	//return ReportFacadeControl.
	public ReportFacadeControl getReportFacadeControl() {
		return reportFacadeControl;
	}

	//return access.
	public int getAccess() {
		return access;
	}

	//set access method.
	public void setAccess(int access) {
		this.access = access;
	}

	//return printerGateway.
	public PrinterGateway getPrinterGateway() {
		return printerGateway;
	}

	//retrieve payment.
	public Payment retrievePayment(int iD) {
		return null;
	}

	//return payment list.
	public Payment[] retrieveListOfPayments(Customer customer) {
		return new Payment[0];
	}

	//return cards.
	public Card[] retrieveCards(Customer customer) {
		return new Card[0];
	}

	//generate invoice method.
	public void generateInvoice() {

	}

	//add payment method.
	public void addPayment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card, String type) throws ClassNotFoundException, SQLException, InstantiationException, ParseException, IllegalAccessException {
		//add payment.
		paymentControl.addPayment(amount, date, customer, jobs, dueDate, card, type);
	}
}