package GUI;

import javax.swing.*;

public class ReportForm extends Form {
	private String state;
	private JButton configureScheduleButton;
	private JPanel reportsPanel;
	private JButton generateReportButton;
	private JTextField staffIDField;
	private JTextField dateField;
	private JPanel mainPanel;
	private JButton goButton;
	private JTable table;
	private JTextField textField;
	private JButton customerJobReportButton;
	private JButton performanceSummaryReportButton;
	private JButton individualReportButton;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ReportForm() {
		throw new UnsupportedOperationException();
	}
}