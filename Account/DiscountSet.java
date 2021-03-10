package Account;

import java.util.Set;
import java.util.Vector;

public class DiscountSet {
	private int noOfDiscounts = 0;
	private Vector<Discount> discounts;

	public void addDiscount(Discount discount) {
		discounts.add(discount);
		incrementNoOfDiscounts();
	}

	public void removeDiscount(Discount discount) {
		discounts.remove(discount);
		decrementNoOfDiscounts();
	}

	public void incrementNoOfDiscounts() {
		noOfDiscounts++;
	}

	public void decrementNoOfDiscounts() {
		noOfDiscounts--;
	}

	public int getNoOfDiscounts() {
		return noOfDiscounts;
	}

	public Vector<Discount> getDiscounts() {
		return discounts;
	}

	public DiscountSet() {
		throw new UnsupportedOperationException();
	}
}