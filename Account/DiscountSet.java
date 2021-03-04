package Account;

import java.util.Vector;
import Account.Discount;

public class DiscountSet {
	private int noOfDiscounts = 0;
	public ValuedCustomer vCust;
	public Vector<Discount> discount = new Vector<Discount>();

	public void addDiscount(Discount discount) {
		throw new UnsupportedOperationException();
	}

	public void removeDiscount(Discount discount) {
		throw new UnsupportedOperationException();
	}

	public void incrementNoOfDiscounts() {
		throw new UnsupportedOperationException();
	}

	public void decrementNoOfDiscounts() {
		throw new UnsupportedOperationException();
	}

	public DiscountSet() {
		throw new UnsupportedOperationException();
	}
}