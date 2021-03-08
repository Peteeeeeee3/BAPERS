package GUI;

import javax.swing.*;

public class AdminConfigureForm extends Form {
	private String state;
	private JButton upgradeCustomerButton;
	private JPanel configureCustomerPanel;
	private JButton downgradeCustomerButton;
	private JButton createCustomerButton;
	private JPanel configureSchedulePanel;
	private JComboBox dropdownList;
	private JTextArea intervalTextArea;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public AdminConfigureForm() {
		throw new UnsupportedOperationException();
	}
}