package GUI;

import Control.Control;

import javax.swing.*;
import java.util.Stack;

public class GUIControl extends JFrame {
	private Control controller;
	private Stack<JFrame> viewingOrder = new Stack<>();
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

	public void usePaymentScreen(JFrame paymentScreen){
		new PaymentScreen(this, paymentScreen);
		setMasterFrame(this);
		viewingOrder.push(paymentScreen);
	}

	public void useCreateCustomerScreen(JFrame cCustomerScreen){
		new CreateCustomerScreen(this, cCustomerScreen);
		setMasterFrame(this);
		viewingOrder.push(cCustomerScreen);
	}

	public void useExistingDeadlineCustomerScreen(JFrame edlCustScreen){
		new ExistingDeadlineCustomerScreen(this, edlCustScreen);
		setMasterFrame(this);
		viewingOrder.push(edlCustScreen);
	}

	public void useAddTaskScreen(JFrame addTask){
		new AddTaskScreen(this, addTask);
		setMasterFrame(this);
		viewingOrder.push(addTask);
	}

	public void useCardPaymentScreen(JFrame cardPayment){
		new CardPaymentScreen(this, cardPayment);
		setMasterFrame(this);
		viewingOrder.push(cardPayment);
	}

	public void useCashPaymentScreen(JFrame cashPayment){
		new CashPaymentScreen(this, cashPayment);
		setMasterFrame(this);
		viewingOrder.push(cashPayment);
	}

	public void closeCurrentFrame(){
		viewingOrder.pop().dispose();
	}


}
