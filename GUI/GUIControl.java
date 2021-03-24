package GUI;

import Control.Control;

import javax.swing.*;
import java.util.Stack;

public class GUIControl extends JFrame {
	private Control controller;
	private Stack<JFrame> viewingOrder;
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
	public void useLogin(JFrame current) {
		new Login(this, current);
		setMasterFrame(this);
	}
}
