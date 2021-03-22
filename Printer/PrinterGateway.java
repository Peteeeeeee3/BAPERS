package Printer;

import Report.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.print.PrinterJob;
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
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String date = "";
			if (estimatedDuration < 1000) {
				if (Integer.parseInt(Integer.toString(estimatedDuration).substring(1, 3)) / 60 != 0) {
					estimatedDuration -= 60;
					estimatedDuration += 100;
				}
			} else {
				if (Integer.parseInt(Integer.toString(estimatedDuration).substring(2, 4)) / 60 != 0) {
					estimatedDuration -= 60;
					estimatedDuration += 100;
				}
			}
			if (estimatedDuration >= 2400) {
				estimatedDuration -= 2400;
				//add time
				ret_val += Integer.toString(estimatedDuration).substring(0, 2) + ":" +
						Integer.toString(estimatedDuration).substring(2, 3) + " ";
				//add date
				date += report.getStartDates().get(estD_itr) + 1;

			} else {
				if (estimatedDuration < 1000) {
					ret_val += "0" + Integer.toString(estimatedDuration).substring(0, 1) + ":" +
							Integer.toString(estimatedDuration).substring(1, 3) + " ";
				} else {
					//add time
					ret_val +=Integer.toString(estimatedDuration).substring(0, 2) + ":" +
							Integer.toString(estimatedDuration).substring(2, 4) + " ";
				}
				//add date
				date += report.getStartDates().get(estD_itr);
;			}
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			ret_val += year + "/" + month + "/" + day;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(ret_val);
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
			title += "                           " + date + "\n";
			//customer details
			String customer = "Customer: " + report.getCustID() + " " + report.getCustomerName();
			//estimated time of collection
			String ETC = "Estimated time for collection: " + calc_etc(report);
			String desc_line = "Description of work in progress: ";
			Paragraph p_title = new Paragraph(title), p_customer = new Paragraph(customer),
					p_ETC = new Paragraph(ETC), p_dec_line = new Paragraph(desc_line);
			//add title
			p_title.setIndentationLeft(200.0f);
			doc.add(p_title);
			//add customer info
			doc.add(p_customer);
			//add etc
			doc.add(p_ETC);
			//add filler line
			doc.add(p_dec_line);

			//Create the table
			doc.add(new Paragraph(" "));
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			int pos = 0;
			PdfPCell new_cell = new PdfPCell(new Phrase("Job"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Price, GBP"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Task"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Department"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Start Time"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Time Taken"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Completed by"));
			table.addCell(new_cell);
			table.setHeaderRows(1);

			for (ReportTask rt : report.getInfoVec()) {
				new_cell = new PdfPCell(new Phrase(Integer.toString(rt.getJob())));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(Float.toString(rt.getPrice())));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(Integer.toString(rt.getTask())));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(rt.getDepartment()));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(Integer.toString(rt.getStartTime())));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(Integer.toString(rt.getTimeTaken())));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(rt.getCompletedBy()));
				table.addCell(new_cell);
			}
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void prt_ipr(IndividualPerformanceReport report, String file_name) {
		try {
			//create a document
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			//open document
			doc.open();
			//make layout
			//title
			String title = "Individual Performance Report";
			//time period
			String period = "Period: ";
			String start = "", end = "";
			start = Integer.toString(report.getStartDate()).substring(0, 4) + "/" + Integer.toString(report.getStartDate()).substring(4, 6)
					+ "/" + Integer.toString(report.getStartDate()).substring(6, 8);
			end = Integer.toString(report.getEndDate()).substring(0, 4) + "/" + Integer.toString(report.getEndDate()).substring(4, 6)
					+ "/" + Integer.toString(report.getEndDate()).substring(6, 8);
			period += start + " - " + end;
			//id
			String id = Integer.toString(report.getReportID());
			//correct fonts
			Paragraph p_title = new Paragraph(new Phrase(title, FontFactory.getFont(FontFactory.defaultEncoding, 20.0f))),
					p_period = new Paragraph(period), p_id = new Paragraph(id);
			//correct position
			p_title.setIndentationLeft(130);
			p_period.setIndentationLeft(180);
			p_id.setIndentationLeft(260);
			//add them
			doc.add(p_title);
			doc.add(p_period);
			doc.add(p_id);
			//make space before table
			doc.add(new Paragraph(new Phrase(" ")));
			//add table
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			PdfPCell new_cell = new PdfPCell(new Phrase("Name"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Task IDs"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Department"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Date"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Start time"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Time taken"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Total"));
			table.addCell(new_cell);

			String prev_name = "";
			int total = 0;
			System.out.println(report.getNames().size());
			for (int jobID_itr = 0; jobID_itr < report.getNames().size(); jobID_itr++) {
				if (!prev_name.equals(report.getNames().get(jobID_itr))) {
					new_cell = new PdfPCell(new Phrase(report.getNames().get(jobID_itr)));
					table.addCell(new_cell);
					prev_name = report.getNames().get(jobID_itr);
					total = report.getDurations().get(jobID_itr);
				} else {
					new_cell = new PdfPCell(new Phrase(" "));
					table.addCell(new_cell);
					total += report.getDurations().get(jobID_itr);
				}
				new_cell = new PdfPCell(new Phrase(Integer.toString(report.getTaskIDs().get(jobID_itr))));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(report.getLocations().get(jobID_itr)));
				table.addCell(new_cell);
				//format date
				String date = Integer.toString(report.getDates().get(jobID_itr));
				String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				String day = date.substring(6, 8);
				date = year + "/" + month + "/" + day;
				new_cell = new PdfPCell(new Phrase(date));
				table.addCell(new_cell);
				//format time
				String time = "";
				if (report.getStartTimes().get(jobID_itr) == 0) {
					time = "Not started";
				} else {
					String hours = report.getStartTimes().get(jobID_itr).toString().substring(0, 2);
					String minutes = report.getStartTimes().get(jobID_itr).toString().substring(2, 4);
					time = hours + ":" + minutes;
				}
				new_cell = new PdfPCell(new Phrase(time));
				table.addCell(new_cell);
				new_cell = new PdfPCell(new Phrase(Integer.toString(report.getDurations().get(jobID_itr))));
				table.addCell(new_cell);
				//handle totals
				if (jobID_itr >= report.getNames().size() - 1) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(total)));
				} else if (!report.getNames().get(jobID_itr + 1).equals(report.getNames().get(jobID_itr))) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(total)));
				} else {
					new_cell = new PdfPCell(new Phrase(" "));
				}
				table.addCell(new_cell);
			}
			doc.add(table);

			//close document
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
			//call printing function
			prt_ipr((IndividualPerformanceReport) report, file_name);
		}
		if (report instanceof PerformanceSummary) {
			file_name += "PerformanceSummary " + ((PerformanceSummary) report).getStartDate() + " " + ((PerformanceSummary) report).getEndDate() + " " +
					report.getReportID() + ".pdf";
		}
//		System.out.println("Report print complete!");
	}

	public void cancelPrint() {
		throw new UnsupportedOperationException();
	}

	public PrinterGateway() {}
}