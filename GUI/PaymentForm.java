package GUI;

public class PaymentForm extends Form {
	private String state;
	private JPanel cashPaymentPanel;
	private JCheckBox paidCheckBox;
	private JButton cardTypeButton;
	private JButton expiryDateButton;
	private JButton l4DButton;
	private JCheckBox paidCheckBox2;
	private JPanel cardPaymentPanel;
	private JTextField jobIDField;
	private JTextArea amountDueField;
	private JButton cashButton;
	private JButton cardButton;
	private Object mainPanel;
	public GUIControl unnamed_GUIControl_;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public PaymentForm() {
		throw new UnsupportedOperationException();
	}
}