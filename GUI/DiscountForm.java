package GUI;

public class DiscountForm extends Form {
	private String state;
	private JPanel fixedDiscountPanel;
	private JTextArea formatTextArea;
	private JTextField amountField;
	private JButton flexibleDiscountButton;
	private JButton fixedDiscountButton;
	private JPanel selectDiscountPanel;
	private JButton variableDiscountButton;
	private JTable variableDiscountTable;
	private JPanel variableDiscountPanel;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public DiscountForm(Form form) {
		throw new UnsupportedOperationException();
	}
}