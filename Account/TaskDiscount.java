package Account;

public class TaskDiscount extends Discount {
	private int taskID;
	private float rate;

	public TaskDiscount(int iD, float rate) {
		super(rate);
		throw new UnsupportedOperationException();
	}

	public int getTaskID() {
		return taskID;
	}

	public float getRate() {
		return rate;
	}
}