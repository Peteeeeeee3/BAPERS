package Job;

public class TaskForJob extends Task {
	private int posInVec;
	private boolean isDelayed = false;
	private boolean isComplete = false;
	public VectorOfTasksForJob vecTaskJ;

	public void updateTask() {
		throw new UnsupportedOperationException();
	}

	public void alertManager() {
		throw new UnsupportedOperationException();
	}

	public boolean delayCheck() {
		throw new UnsupportedOperationException();
	}

	public void onDelay() {
		throw new UnsupportedOperationException();
	}

	public int getPosInVec() {
		return this.posInVec;
	}

	public void setPosInVec(int posInVec) {
		this.posInVec = posInVec;
	}

	public boolean getIsDelayed() {
		return this.isDelayed;
	}

	public void setIsDelayed(boolean isDelayed) {
		this.isDelayed = isDelayed;
	}

	public boolean getIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public TaskForJob(Task task) {
		throw new UnsupportedOperationException();
	}
}