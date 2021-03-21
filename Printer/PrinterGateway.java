package Printer;

import Report.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PrinterGateway {

	private String calc_etc(CustomerJobReport report) {
		String ret_val = "";
		int estimatedDuration = 0, estD_itr = 0, check;
		for (int i = 0; i < report.getStartTimes().size(); i++) {
			check = report.getStartTimes().get(i) + report.getPriorities().get(i);
			if (check > estimatedDuration) {
				estimatedDuration = check;
				estD_itr = i;
			}
		}
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			if (estimatedDuration >= 2400) {
				estimatedDuration -= 2400;
				ret_val += Integer.parseInt(Integer.toString(estimatedDuration).substring(0, 2)) + ":" +
						Integer.parseInt(Integer.toString(estimatedDuration).substring(2, 3)) + " " +
						originalFormat.parse(report.getStartDates().get(estD_itr).toString() + 1);
			} else {
				ret_val += Integer.parseInt(Integer.toString(estimatedDuration).substring(0, 2)) + ":" +
						Integer.parseInt(Integer.toString(estimatedDuration).substring(2, 3)) + " " +
						originalFormat.parse(report.getStartDates().get(estD_itr).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ret_val);
		return ret_val;
	}

	private void prt_cjr(CustomerJobReport report, String file_name) {
		try {
			//create a document
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			//open document
			doc.open();
			//make layout
			//title
			String title = "JOB SHEET " + report.getReportID();
			//date
			//get today's date and format it
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate date_now = LocalDate.now();
			String date = "Date: " + dtf.format(date_now);
			//customer details
			String customer = "Customer: " + report.getCustID() + " " + report.getCustomerName();
			//estimated time of collection
			String ETC = "Estimated time for collection: " + calc_etc(report);
			String desc_line = "Description of work in progress: ";
			Paragraph p_title = new Paragraph(title), p_date = new Paragraph(date), p_customer = new Paragraph(customer),
					p_ETC = new Paragraph(ETC), p_dec_line = new Paragraph(desc_line);
			doc.add(p_title);
			doc.add(p_date);
			doc.add(p_customer);
			doc.add(p_ETC);
			doc.add(p_dec_line);

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void print(Report report) {
		//set file name
		String file_name = "C:\\Users\\Peter\\Documents\\School\\UNI\\Team Project\\Code\\reports\\";
		//get today's date and format it
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate date = LocalDate.now();
		//check for report type and apply correct name
		if (report instanceof CustomerJobReport) {
			file_name += "CustomerJobReport " + ((CustomerJobReport) report).getCustomerName() + " " + date + " " + report.getReportID() + ".pdf";
			//call printing function
			prt_cjr((CustomerJobReport) report, file_name);
		}
		if (report instanceof IndividualPerformanceReport) {
			file_name += "IndividualPerformanceReport " + ((IndividualPerformanceReport) report).getStartDate() + " " +
					((IndividualPerformanceReport) report).getEndDate() + " " + report.getReportID() + ".pdf";
		}
		if (report instanceof PerformanceSummary) {
			file_name += "PerformanceSummary " + ((PerformanceSummary) report).getStartDate() + " " + ((PerformanceSummary) report).getEndDate() + " " +
					report.getReportID() + ".pdf";
		}
		//System.out.println("Report print complete!");
	}

	public void cancelPrint() {
		throw new UnsupportedOperationException();
	}

	public PrinterGateway() {}
}