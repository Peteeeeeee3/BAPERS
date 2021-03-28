package Account;

public class Technician extends UserAccount {

	public void updateStatus() {
		throw new UnsupportedOperationException();
	}

	public void startTask(int jobID, int taskID) {
		throw new UnsupportedOperationException();
	}

	public Technician(UserAccount user) {
		super(user.getStaffID(), user.getPassword(), user.getName(), user.getAccess(), user.getVecUser());
		throw new UnsupportedOperationException();
	}
}