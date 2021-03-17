package Report;

import Control.Control;

import java.util.Vector;

public class ReportFacadeControl implements I_ReportFacade {
	private Vector<Report> reports;
	private Control control;


	public void generateIndividualPerformanceReport(IndividualPerformanceReport report) {
		report.setRFC(this);
//		String name, department;
//		int taskID, startTime, timeTaken, total, totalEffort;
//		String sql = "SELECT * FROM ";
	}

	public void generatePerformanceSummary(PerformaneSummary report) {
		throw new UnsupportedOperationException();
	}

	public void generateCustomerJobReport(CustomerJobReport report) {
		throw new UnsupportedOperationException();
	}

	public Control getControl() {
		return control;
	}

	public ReportFacadeControl(Control control) {
		this.control = control;
	}
}