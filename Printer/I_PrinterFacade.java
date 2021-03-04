package Printer;

import Report.Report;

public interface I_PrinterFacade {

	public void print(Report report);

	public void cancelPrint();
}