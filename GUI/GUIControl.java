package GUI;

import Control.Control;
import Job.Job;
import Report.*;
import Account.*;

import javax.swing.*;
import java.util.Stack;

public class GUIControl extends JFrame {
	private Control controller;
	private final Stack<JFrame> viewingOrder = new Stack<>();
	private JPanel current;
	private JFrame masterFrame;

	public Control getController() {
		return controller;
	}
	public JPanel getCurrent() {
		return current;
	}
	public void setCurrent(JPanel current) {
		this.current = current;
	}
	public Stack<JFrame> getViewingOrder() {
		return viewingOrder;
	}
	public void setMasterFrame(JFrame masterFrame) {
		this.masterFrame = masterFrame;
	}
	public JFrame getMasterFrame() {
		return masterFrame;
	}

	public GUIControl(Control controller, JFrame frame) {
		super("BAPERS");
		//initializes GUIControl
		this.controller = controller;
		this.masterFrame = frame;
		this.useLogin(masterFrame);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setContentPane(current);
	}

	//sets changes the current screen to login
	public void useLogin(JFrame login) {
		new Login(this, login);
		setMasterFrame(this);
		viewingOrder.push(login);
	}

	public void useHomepage(JFrame homePage){
		new HomePage(this, homePage);
		setMasterFrame(this);
		viewingOrder.push(homePage);
	}

	public void useSearchCustomerScreen(JFrame searchCustomerScreen){
		new SearchCustomerScreen(this, searchCustomerScreen);
		setMasterFrame(this);
		viewingOrder.push(searchCustomerScreen);
	}

	public void usePaymentScreen(JFrame paymentScreen, Customer customer){
		new PaymentScreen(this, paymentScreen, customer);
		setMasterFrame(this);
		viewingOrder.push(paymentScreen);
	}

	public void useCreateCustomerScreen(JFrame cCustomerScreen){
		new CreateCustomerScreen(this, cCustomerScreen);
		setMasterFrame(this);
		viewingOrder.push(cCustomerScreen);
	}

	public void useExistingDeadlineCustomerScreen(JFrame edlCustScreen){
		new CreateJob(this, edlCustScreen);
		setMasterFrame(this);
		viewingOrder.push(edlCustScreen);
	}

	public void useAddTaskScreen(JFrame addTask){
		new AddTaskToJobScreen(this, addTask);
		setMasterFrame(this);
		viewingOrder.push(addTask);
	}

	public void useCardPaymentScreen(JFrame cardPayment, float price, Customer customer, Job[] jobs){
		new CardPaymentScreen(this, cardPayment, price, customer, jobs);
		setMasterFrame(this);
		viewingOrder.push(cardPayment);
	}

	public void useCashPaymentScreen(JFrame cashPayment, float price, Customer customer, Job[] jobs){
		new CashPaymentScreen(this, cashPayment, price, customer, jobs);
		setMasterFrame(this);
		viewingOrder.push(cashPayment);
	}

	public void useOMHomePage(JFrame omHomePageScreen){
		new OMHomePage(this, omHomePageScreen);
		setMasterFrame(this);
		viewingOrder.push(omHomePageScreen);
	}

	public void useSMHomePage(JFrame smHomePageScreen){
		new SMHomePage(this, smHomePageScreen);
		setMasterFrame(this);
		viewingOrder.push(smHomePageScreen);
	}

	public void useTechHomePage(JFrame techHomePageScreen){
		new TechHomePage(this, techHomePageScreen);
		setMasterFrame(this);
		viewingOrder.push(techHomePageScreen);
	}

	public void useAdminScreen(JFrame adminScreen){
		new AdminScreen(this, adminScreen);
		setMasterFrame(this);
		viewingOrder.push(adminScreen);
	}

	public void useChangeCustLvlScrn(JFrame changeCustLvl){
		new ChangeCustomerLevelScreen(this, changeCustLvl);
		setMasterFrame(this);
		viewingOrder.push(changeCustLvl);
	}

	public void useConfigScheduleScreen(JFrame configSched){
		new ConfigureScheduleScreen(this, configSched);
		setMasterFrame(this);
		viewingOrder.push(configSched);
	}

	public void useCustJobReport(JFrame custJobRep){
		new CustomerJobsReport(this, custJobRep);
		setMasterFrame(this);
		viewingOrder.push(custJobRep);
	}

	public void useDowngradeCustScreen(JFrame downgradeCust){
		new DowngradeCustomerScreen(this, downgradeCust);
		setMasterFrame(this);
		viewingOrder.push(downgradeCust);
	}

	public void useFixedDiscountScreen(JFrame fixedDisc){
		new FixedDiscountScreen(this, fixedDisc);
		setMasterFrame(this);
		viewingOrder.push(fixedDisc);
	}

	public void useFlexibleDiscScreen(JFrame flexibleDisc){
		new FixedDiscountScreen(this, flexibleDisc);
		setMasterFrame(this);
		viewingOrder.push(flexibleDisc);
	}

	public void useGenerateReportScreen(JFrame genReport){
		new GenerateReport(this, genReport);
		setMasterFrame(this);
		viewingOrder.push(genReport);
	}

	public void useGenIndividReportScreen(JFrame genIndividReport){
		new Gen_IndividualReport(this, genIndividReport);
		setMasterFrame(this);
		viewingOrder.push(genIndividReport);
	}

	public void useReportsScreen(JFrame selectReports){
		new ReportsScreen(this, selectReports);
		setMasterFrame(this);
		viewingOrder.push(selectReports);
	}

	public void useSelectDiscountScreen(JFrame selectDiscountScreen){
		new SelectDiscountScreen(this, selectDiscountScreen);
		setMasterFrame(this);
		viewingOrder.push(selectDiscountScreen);
	}

	public void useStartTaskScreen(JFrame startTaskScreen){
		new StartUpdateTaskForJobScreen(this, startTaskScreen);
		setMasterFrame(this);
		viewingOrder.push(startTaskScreen);
	}

	public void useTaskManageScreen(JFrame taskManageScreen){
		new TaskManageScreen(this, taskManageScreen);
		setMasterFrame(this);
		viewingOrder.push(taskManageScreen);
	}

	public void useUpdateJobScreen(JFrame updateJobScreen){
		new UpdateJobScreen(this, updateJobScreen);
		setMasterFrame(this);
		viewingOrder.push(updateJobScreen);
	}

	public void useUpgradeCustScreen(JFrame upgradeCustScreen){
		new UpgradeCustomerScreen(this, upgradeCustScreen);
		setMasterFrame(this);
		viewingOrder.push(upgradeCustScreen);
	}

	public void useVariableDiscScreen(JFrame variableDiscScreen){
		new VariableDiscountScreen(this, variableDiscScreen);
		setMasterFrame(this);
		viewingOrder.push(variableDiscScreen);
	}

	public void useViewTaskScreen(JFrame viewTaskScreen){
		new ViewTaskScreen(this, viewTaskScreen);
		setMasterFrame(this);
		viewingOrder.push(viewTaskScreen);
	}

	public void useViewJobScreen(JFrame viewJobScreen){
		new ViewJobScreen(this, viewJobScreen);
		setMasterFrame(this);
		viewingOrder.push(viewJobScreen);
	}

	public void useAddNewTaskScreen(JFrame addNewTaskScreen){
		new AddNewTaskScreen(this, addNewTaskScreen);
		setMasterFrame(this);
		viewingOrder.push(addNewTaskScreen);
	}

	public void useCreateUserScreen(JFrame createUser){
		new CreateUserScreen(this, createUser);
		setMasterFrame(this);
		viewingOrder.push(createUser);
	}

	public void useRemoveUserScreen(JFrame removeUserScreen){
		new RemoveUserScreen(this, removeUserScreen);
		setMasterFrame(this);
		viewingOrder.push(removeUserScreen);
	}

	public void useEditAccessScreen(JFrame editAccessScreen){
		new EditAccessScreen(this, editAccessScreen);
		setMasterFrame(this);
		viewingOrder.push(editAccessScreen);
	}

	public void useDBConnectionScreen(JFrame dbConnectScreen){
		new DBConnectionScreen(this, dbConnectScreen);
		setMasterFrame(this);
		viewingOrder.push(dbConnectScreen);
	}

	public void useReportDisplayScreen(JFrame reportDisplayScreen, Report report) {
		new ReportDisplayScreen(this, reportDisplayScreen, report);
		setMasterFrame(this);
		viewingOrder.push(reportDisplayScreen);
	}

	public void useSummaryReportDisplay(JFrame summaryReportDisplay, Report report) {
		new SummaryReportDisplay(this, summaryReportDisplay, report);
		setMasterFrame(this);
		viewingOrder.push(summaryReportDisplay);
	}

	public void useGenPerformanceSummary(JFrame genPerformanceSummary) {
		new Gen_SummaryReport(this, genPerformanceSummary);
		setMasterFrame(this);
		viewingOrder.push(genPerformanceSummary);
	}

	public void useSearchCustomerPayment(JFrame frame) {
		new SearchCustomerScreenPayment(this, frame);
		setMasterFrame(this);
		viewingOrder.push(frame);
	}

	public void closeCurrentFrame(){
		getViewingOrder().peek().setVisible(false);
	}

	public void openPreviousFrame() {
		getViewingOrder().pop().dispose();
		getViewingOrder().peek().setVisible(true);
	}

	public int getAccess() {
		return controller.getAccess();
	}
}
