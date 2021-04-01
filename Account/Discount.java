package Account;

public class Discount {
	private float discountRate;
// getter and setter for discount rate
	public float getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(float discountRate) {
		this.discountRate = discountRate;
	}

	public Discount(float rate) {
		this.discountRate = rate;
	}
}