package Account;

public class Discount {
	private float discountRate;
	public DiscountSet discSet;

	public float getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(float discountRate) {
		this.discountRate = discountRate;
	}

	public Discount(float rate) {
		throw new UnsupportedOperationException();
	}
}