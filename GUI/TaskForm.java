package GUI;

import javax.swing.*;

public class TaskForm extends Form {
	private String state;
	private JPanel taskManagePanel;
	private JPanel addTaskPanel;
	private JButton startTaskButton;
	private JButton viewJobButton;
	private JTable taskDescription;
	private JButton updateTaskButton;
	private JPanel mainPanel;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TaskForm() {
		throw new UnsupportedOperationException();
	}
}