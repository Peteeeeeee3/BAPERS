package Report;

public interface I_ReportFacade {

	public void generateIndividualPerformanceReport(IndividualPerformanceReport report);

	public void generatePerformanceSummary(PerformaneSummary report);

	public void generateCustomerJobReport(CustomerJobReport report);
}