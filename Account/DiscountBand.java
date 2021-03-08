package Account;

public class DiscountBand extends Discount {
	private float range_min;
	private float range_max;

	public float getRange_min() {
		return this.range_min;
	}

	public void setRange_min(float range_min) {
		this.range_min = range_min;
	}

	public float getRange_max() {
		return this.range_max;
	}

	public void setRange_max(float range_max) {
		this.range_max = range_max;
	}

	public DiscountBand(float max, float min, float rate) {
		super(rate);
		throw new UnsupportedOperationException();
	}
}