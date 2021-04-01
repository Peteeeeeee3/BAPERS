package Account;

import java.util.Set;
import java.util.Vector;

public class DiscountSet {
	private int noOfDiscounts = 0;
	private Vector<Discount> discounts;

	public void addDiscount(Discount discount) { // adds discount and stored into vector
		discounts.add(discount);
		incrementNoOfDiscounts(); // increment the size of the vector.
	}

	public void removeDiscount(Discount discount) {
		discounts.remove(discount); //removes discount from the vector
		decrementNoOfDiscounts(); // reduces vector size
	}

	public void incrementNoOfDiscounts() {
		noOfDiscounts++; // increments the size of the vector
	}

	public void decrementNoOfDiscounts() {
		noOfDiscounts--; //decrements the size of the vector
	}

	public int getNoOfDiscounts() {
		return noOfDiscounts;
	}

	public Vector<Discount> getDiscounts() {
		return discounts; //retrieves all values within the vector.
	}

	public DiscountSet() {
		throw new UnsupportedOperationException();
	}
}