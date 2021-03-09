package Payment;

import Account.Customer;

public class Card {
	private String type;
	private int expiryDate;
	private int last4Digits;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getLast4Digits() {
		return this.last4Digits;
	}

	public void setLast4Digits(int last4Digits) {
		this.last4Digits = last4Digits;
	}

	public Card(String type, int expiryDate, int last4Digits) {
		this.type = type;
		this.expiryDate = expiryDate;
		this.last4Digits = last4Digits;
	}
}