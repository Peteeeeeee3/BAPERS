package Report;

import Control.Control;

import java.util.Vector;

public class ReportFacadeControl implements I_ReportFacade {
	private Vector<Report> reports;
	private Control control;


	public void generateReport(Report report) {
		report.setRFC(this);
		reports.add(report);
	}

	public void printReport() {

	}

	public Control getControl() {
		return control;
	}

	public ReportFacadeControl(Control control) {
		this.control = control;
	}
}