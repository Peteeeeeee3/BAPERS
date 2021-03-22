package GUI;

import javax.swing.*;

public class CustomerDataForm<JField> extends Form {
	private String state;
	private JPanel searchCustPanel;
	private JTextField searchTextField;
	private JTable custTable;
	private JButton searchButton;
	private JScrollBar scrollBar;
	private JTextField deadlineField;
	private JButton addTaskButton;
	private JTable noOfJobs;
	private JField specialInstructionsField;
	private JPanel existingCustPanel;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public CustomerDataForm(Form form) {
		throw new UnsupportedOperationException();
	}
}