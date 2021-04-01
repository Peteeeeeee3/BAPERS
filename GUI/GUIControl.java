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
	//Method to set the current screen to homepage screen
	public void useHomepage(JFrame homePage){
		//Creates the new homepage object
		new HomePage(this, homePage);
		setMasterFrame(this);
		//Pushes the current frame onto a stack.
		viewingOrder.push(homePage);
	}
	//Method to set the current screen to the search customer screen
	public void useSearchCustomerScreen(JFrame searchCustomerScreen){
		//creates the new search customer homepage
		new SearchCustomerScreen(this, searchCustomerScreen);
		setMasterFrame(this);
		//Pushes the current frame to the stack
		viewingOrder.push(searchCustomerScreen);
	}
	//Method to set the current screen to the payment screen
	public void usePaymentScreen(JFrame paymentScreen, Customer customer){
		//Creates the new object payment screen
		new PaymentScreen(this, paymentScreen, customer);
		setMasterFrame(this);
		//Pushes the current frame to the stack
		viewingOrder.push(paymentScreen);
	}
	//Method to set the current screen to the payment screen
	public void useCreateCustomerScreen(JFrame cCustomerScreen){
		//Creates the new object payment screen
		new CreateCustomerScreen(this, cCustomerScreen);
		setMasterFrame(this);
		//Pushes the current frame to the stack
		viewingOrder.push(cCustomerScreen);
	}
	//Method to set the current screen to the createJob screen. Name of the method is different because this screen was
	//Rethought and refactored during coding
	public void useExistingDeadlineCustomerScreen(JFrame edlCustScreen){
		//Creates the new object create job screen
		new CreateJob(this, edlCustScreen);
		setMasterFrame(this);
		//Pushes the current frame to the stack
		viewingOrder.push(edlCustScreen);
	}
	//Method to set the current screen to the addTaskToJobScreen
	public void useAddTaskScreen(JFrame addTask){
		//Creates the new object
		new AddTaskToJobScreen(this, addTask);
		setMasterFrame(this);
		//Pushes to stack
		viewingOrder.push(addTask);
	}
	//Method sets current screen to card payment screen
	public void useCardPaymentScreen(JFrame cardPayment, float price, Customer customer, Job[] jobs){
		//Creates new object
		new CardPaymentScreen(this, cardPayment, price, customer, jobs);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(cardPayment);
	}
	//Method sets current screen to cash payment screen
	public void useCashPaymentScreen(JFrame cashPayment, float price, Customer customer, Job[] jobs){
		//Creates new object
		new CashPaymentScreen(this, cashPayment, price, customer, jobs);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(cashPayment);
	}
	//Method sets current screen to office manager home page
	public void useOMHomePage(JFrame omHomePageScreen){
		//Creates object
		new OMHomePage(this, omHomePageScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(omHomePageScreen);
	}
	//Method sets current screen to shift manager home page
	public void useSMHomePage(JFrame smHomePageScreen){
		//Creates object
		new SMHomePage(this, smHomePageScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(smHomePageScreen);
	}
	//Method sets current screen to technician home page
	public void useTechHomePage(JFrame techHomePageScreen){
		//Creates object
		new TechHomePage(this, techHomePageScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(techHomePageScreen);
	}
	//Method sets current screen to admin screen for Office Manager
	public void useAdminScreen(JFrame adminScreen){
		//Creates object
		new AdminScreen(this, adminScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(adminScreen);
	}
	//Method sets current screen to change cust lvl screen
	public void useChangeCustLvlScrn(JFrame changeCustLvl){
		//Creates object
		new ChangeCustomerLevelScreen(this, changeCustLvl);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(changeCustLvl);
	}
	//Method sets current screen to configure schedule screen
	public void useConfigScheduleScreen(JFrame configSched){
		//Creates object
		new ConfigureScheduleScreen(this, configSched);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(configSched);
	}
	//Method sets current screen to customer jobs report
	public void useCustJobReport(JFrame custJobRep){
		//Creates object
		new CustomerJobsReport(this, custJobRep);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(custJobRep);
	}
	//Method sets current screen to downgrade customer screen
	public void useDowngradeCustScreen(JFrame downgradeCust){
		//Creates object
		new DowngradeCustomerScreen(this, downgradeCust);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(downgradeCust);
	}
	//Method sets current screen to fixed disc screen
	public void useFixedDiscountScreen(JFrame fixedDisc){
		//Creates object
		new FixedDiscountScreen(this, fixedDisc);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(fixedDisc);
	}
	//Method sets current screen to flex disc screen
	public void useFlexibleDiscScreen(JFrame flexibleDisc){
		//Creates object
		new FixedDiscountScreen(this, flexibleDisc);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(flexibleDisc);
	}
	//Method sets current screen to gen report screen
	public void useGenerateReportScreen(JFrame genReport){
		//Creates object
		new GenerateReport(this, genReport);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(genReport);
	}
	//Method sets current screen to gen individual report screen
	public void useGenIndividReportScreen(JFrame genIndividReport){
		//Creates object
		new Gen_IndividualReport(this, genIndividReport);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(genIndividReport);
	}
	//Method sets current screen to reports screen
	public void useReportsScreen(JFrame selectReports){
		//Creates object
		new ReportsScreen(this, selectReports);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(selectReports);
	}
	//Method sets current screen to select discount screen
	public void useSelectDiscountScreen(JFrame selectDiscountScreen){
		//Creates object
		new SelectDiscountScreen(this, selectDiscountScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(selectDiscountScreen);
	}
	//Method sets currents screen to start task screen
	public void useStartTaskScreen(JFrame startTaskScreen){
		//Creates object
		new StartUpdateTaskForJobScreen(this, startTaskScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(startTaskScreen);
	}
	//Method sets current screen to task manage screen
	public void useTaskManageScreen(JFrame taskManageScreen){
		//Creates object
		new TaskManageScreen(this, taskManageScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(taskManageScreen);
	}
	//Method sets current screen to update job screen
	public void useUpdateJobScreen(JFrame updateJobScreen){
		//Creates object
		new UpdateJobScreen(this, updateJobScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(updateJobScreen);
	}
	//Method sets current screen to upgrade cust screen
	public void useUpgradeCustScreen(JFrame upgradeCustScreen){
		//Creates object
		new UpgradeCustomerScreen(this, upgradeCustScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(upgradeCustScreen);
	}
	//Method sets current screen to variable disc screen
	public void useVariableDiscScreen(JFrame variableDiscScreen){
		//Creates object
		new VariableDiscountScreen(this, variableDiscScreen);
		setMasterFrame(this);
		//Pushes onto stack
		viewingOrder.push(variableDiscScreen);
	}
	//Method sets current screen to view task screen
	public void useViewTaskScreen(JFrame viewTaskScreen){
		//Creates object
		new ViewTaskScreen(this, viewTaskScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(viewTaskScreen);
	}
	//Method sets current screen to view job screen
	public void useViewJobScreen(JFrame viewJobScreen){
		//Creates object
		new ViewJobScreen(this, viewJobScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(viewJobScreen);
	}
	//Method sets currents creen to add new task screen
	public void useAddNewTaskScreen(JFrame addNewTaskScreen){
		//Creates object
		new AddNewTaskScreen(this, addNewTaskScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(addNewTaskScreen);
	}
	//method sets current screen to create user screen
	public void useCreateUserScreen(JFrame createUser){
		//Creates object
		new CreateUserScreen(this, createUser);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(createUser);
	}
	//Method sets current screen to remove user screen
	public void useRemoveUserScreen(JFrame removeUserScreen){
		//Creates object
		new RemoveUserScreen(this, removeUserScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(removeUserScreen);
	}
	//Method sets current screen to edit access screen
	public void useEditAccessScreen(JFrame editAccessScreen){
		//Creates object
		new EditAccessScreen(this, editAccessScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(editAccessScreen);
	}
	//Method sets current screen to DB connect screen
	public void useDBConnectionScreen(JFrame dbConnectScreen){
		//Creates object
		new DBConnectionScreen(this, dbConnectScreen);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(dbConnectScreen);
	}
	//Method sets current screen to report display screen
	public void useReportDisplayScreen(JFrame reportDisplayScreen, Report report) {
		//Creates object
		new ReportDisplayScreen(this, reportDisplayScreen, report);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(reportDisplayScreen);
	}
	//Method sets current screen to summary report display screen
	public void useSummaryReportDisplay(JFrame summaryReportDisplay, Report report) {
		//Creates object
		new SummaryReportDisplay(this, summaryReportDisplay, report);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(summaryReportDisplay);
	}
	//Method sets current screen to generate performance summary
	public void useGenPerformanceSummary(JFrame genPerformanceSummary) {
		//Creates object
		new Gen_SummaryReport(this, genPerformanceSummary);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(genPerformanceSummary);
	}
	//Method sets current screen to search customer screen
	public void useSearchCustomerPayment(JFrame frame) {
		//Creates object
		new SearchCustomerScreenPayment(this, frame);
		setMasterFrame(this);
		//Push onto stack
		viewingOrder.push(frame);
	}
	//Closing current frame method
	public void closeCurrentFrame(){
		//Gets the stack and peeks at the top. Sets the frame visibility at the top to false so that it goes invisible.
		//Not popped
		getViewingOrder().peek().setVisible(false);
	}
	//An attempt at going back one frame. Never worked unfortunately
	public void openPreviousFrame() {
		getViewingOrder().pop().dispose();
		getViewingOrder().peek().setVisible(true);
	}
	//Getting the controller
	public int getAccess() {
		return controller.getAccess();
	}
}
