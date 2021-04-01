package Printer;

import Report.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.Pfm2afm;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class PrinterGateway {

	private String calc_etc(CustomerJobReport report) {
		String ret_val = "";
		int estimatedDuration = 0, estD_itr = 0, check;
		//get longest duration
		for (int i = 0; i < report.getStartTimes().size(); i++) {
			check = report.getStartTimes().get(i) + report.getPriorities().get(i);
			if (check > estimatedDuration) {
				estimatedDuration = check;
				estD_itr = i;
			}
		}
		//format date
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String date = "";
			//format time
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
			//add table to document
			doc.add(table);

			//close document
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void prt_psr(PerformanceSummary report, String file_name) {
		try {
			//create document
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			doc.open();
			//add descriptive values e.g. title, time interval, etc.
			String title = "Summary Performance Report";
			String period = "Period: ";
			String yearS = Integer.toString(report.getStartDate()).substring(0, 4);
			String monthS = Integer.toString(report.getStartDate()).substring(4, 6);
			String dayS = Integer.toString(report.getStartDate()).substring(6, 8);
			String yearE = Integer.toString(report.getEndDate()).substring(0, 4);
			String monthE = Integer.toString(report.getEndDate()).substring(4, 6);
			String dayE = Integer.toString(report.getEndDate()).substring(6, 8);
			period += yearS + "/" + monthS + "/" + dayS + " - " + yearE + "/" + monthE + "/" + dayE;
			String _break = " ";
			String shift1 = "Day Shift 1 (5 am - 2:30 pm)";
			//add above values as components of the document
			Paragraph p_title = new Paragraph(new Phrase(title, FontFactory.getFont(FontFactory.defaultEncoding, 20.0f))),
					p_period = new Paragraph(period), p_break = new Paragraph(_break), p_shift1 = new Paragraph(shift1);
			//appropriate positioning
			p_title.setIndentationLeft(130);
			p_period.setIndentationLeft(180);
			p_shift1.setIndentationLeft(180);

			//add to document
			doc.add(p_title);
			doc.add(p_period);
			doc.add(p_break);
			doc.add(p_shift1);
			doc.add(new Paragraph(" "));


			//shift one table
			PdfPTable shift1_table = new PdfPTable(5);
			PdfPCell new_cell = new PdfPCell(new Phrase("Date"));
			shift1_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Copy Room"));
			shift1_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Development"));
			shift1_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Finishing"));
			shift1_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Packing"));
			shift1_table.addCell(new_cell);

			//shift 1
			int datePos = 0;
			//search for each object
			for (SummaryInfo si : report.getInfo_vec()) {
				//first column
				if (datePos % 5 == 0) {
					int date = si.getDate();
					String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
							Integer.toString(date).substring(6, 8);
					new_cell = new PdfPCell(new Phrase(dateStr));
					shift1_table.addCell(new_cell);
					datePos++;
				}
				//second column
				if (si.getShift() == 1 && si.getLocation().equals("Copy Room")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift1_table.addCell(new_cell);
					datePos++;
					//third column
				} else if (si.getShift() == 1 && si.getLocation().equals("Development")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift1_table.addCell(new_cell);
					datePos++;
					//fourth column
				} else if (si.getShift() == 1 && si.getLocation().equals("Finishing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift1_table.addCell(new_cell);
					datePos++;
					//fifth column
				} else if (si.getShift() == 1 && si.getLocation().equals("Packing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift1_table.addCell(new_cell);
					datePos++;
				}
			}
			doc.add(shift1_table);

			//shift two table
			doc.add(new Paragraph(" "));
			String shift2 = "Day Shift 2 (2:30 pm - 10 pm)";
			Paragraph p_shift2 = new Paragraph(shift2);
			p_shift2.setIndentationLeft(180);
			doc.add(p_shift2);
			doc.add(new Paragraph(" "));

			PdfPTable shift2_table = new PdfPTable(5);
			new_cell = new PdfPCell(new Phrase("Date"));
			shift2_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Copy Room"));
			shift2_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Development"));
			shift2_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Finishing"));
			shift2_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Packing"));
			shift2_table.addCell(new_cell);

			//shift 2
			datePos = 0;
			for (SummaryInfo si : report.getInfo_vec()) {
				if (datePos % 5 == 0) {
					int date = si.getDate();
					String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
							Integer.toString(date).substring(6, 8);
					new_cell = new PdfPCell(new Phrase(dateStr));
					shift2_table.addCell(new_cell);
					datePos++;
				}
				if (si.getShift() == 1 && si.getLocation().equals("Copy Room")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift2_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Development")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift2_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Finishing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift2_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Packing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift2_table.addCell(new_cell);
					datePos++;
				}
			}
			doc.add(shift2_table);

			//shift three table
			doc.add(new Paragraph(" "));
			String shift3 = "Night Shift (10 pm - 5 am)";
			Paragraph p_shift3 = new Paragraph(shift3);
			p_shift3.setIndentationLeft(180);
			doc.add(p_shift3);
			doc.add(new Paragraph(" "));

			PdfPTable shift3_table = new PdfPTable(5);
			new_cell = new PdfPCell(new Phrase("Date"));
			shift3_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Copy Room"));
			shift3_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Development"));
			shift3_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Finishing"));
			shift3_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Packing"));
			shift3_table.addCell(new_cell);

			//shift 3
			datePos = 0;
			for (SummaryInfo si : report.getInfo_vec()) {
				if (datePos % 5 == 0) {
					int date = si.getDate();
					String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
							Integer.toString(date).substring(6, 8);
					new_cell = new PdfPCell(new Phrase(dateStr));
					shift3_table.addCell(new_cell);
					datePos++;
				}
				if (si.getShift() == 1 && si.getLocation().equals("Copy Room")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift3_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Development")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift3_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Finishing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift3_table.addCell(new_cell);
					datePos++;
				} else if (si.getShift() == 1 && si.getLocation().equals("Packing")) {
					new_cell = new PdfPCell(new Phrase(Integer.toString(si.getValue())));
					shift3_table.addCell(new_cell);
					datePos++;
				}
			}
			doc.add(shift3_table);

			//shift summary
			doc.add(new Paragraph(" "));
			//format date
			String dateS = Integer.toString(report.getStartDate()).substring(0, 4) + "/" +
					Integer.toString(report.getStartDate()).substring(4, 6) + "/" +
					Integer.toString(report.getStartDate()).substring(6, 8);
			String dateE = Integer.toString(report.getEndDate()).substring(0, 4) + "/" +
					Integer.toString(report.getEndDate()).substring(4, 6) + "/" +
					Integer.toString(report.getEndDate()).substring(6, 8);
			String shiftSummary = "For Period (" + dateS + " - " + dateE + ")";
			Paragraph p_shiftSummary = new Paragraph(shiftSummary);
			p_shiftSummary.setIndentationLeft(180);
			doc.add(p_shiftSummary);
			doc.add(new Paragraph(" "));

			PdfPTable shiftSum_table = new PdfPTable(5);
			new_cell = new PdfPCell(new Phrase("Date"));
			shiftSum_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Copy Room"));
			shiftSum_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Development"));
			shiftSum_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Finishing"));
			shiftSum_table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Packing"));
			shiftSum_table.addCell(new_cell);

			//shift totals
			datePos = 0;
			int columnItr = 0;
			//loop for each summary info
			for (SummaryInfo si : report.getInfo_vec()) {
				//display appropriate text in cell
				if (datePos % 5 == 0 && columnItr == 0) {
					new_cell = new PdfPCell(new Phrase(" "));
					shiftSum_table.addCell(new_cell);
					datePos++;
					columnItr++;
				} else if (datePos % 5 == 0 && columnItr == 1) {
					new_cell = new PdfPCell(new Phrase("Day Shift 1"));
					shiftSum_table.addCell(new_cell);
					datePos++;
					columnItr++;
				} else if (datePos % 5 == 0 && columnItr == 2) {
					new_cell = new PdfPCell(new Phrase("Day Shift 2"));
					shiftSum_table.addCell(new_cell);
					datePos++;
					columnItr++;
				} else if (datePos % 5 == 0 && columnItr == 3) {
					new_cell = new PdfPCell(new Phrase("Night Shift"));
					shiftSum_table.addCell(new_cell);
					datePos++;
					columnItr++;
				} else if (datePos % 5 == 0 && columnItr == 4) {
					new_cell = new PdfPCell(new Phrase("Total"));
					shiftSum_table.addCell(new_cell);
					datePos++;
					columnItr++;
				}

				//add and display totals
				int total = 0;
				if (datePos % 5 == 1) {
					for (SummaryInfo si2 : report.getInfo_vec()) {
						if (si.getLocation().equals("Copy Room")) {
							total += si2.getValue();
						}
					}
					new_cell = new PdfPCell(new Phrase(total));
					shiftSum_table.addCell(new_cell);
					datePos++;
					if (datePos == 0) {
						columnItr++;
					}
				} else if (datePos % 5 == 2) {
					for (SummaryInfo si2 : report.getInfo_vec()) {
						if (si.getLocation().equals("Development")) {
							total += si2.getValue();
						}
					}
					new_cell = new PdfPCell(new Phrase(total));
					shiftSum_table.addCell(new_cell);
					datePos++;
					if (datePos == 0) {
						columnItr++;
					}
				} else if (datePos % 5 == 3) {
					for (SummaryInfo si2 : report.getInfo_vec()) {
						if (si.getLocation().equals("Finishing")) {
							total += si2.getValue();
						}
					}
					new_cell = new PdfPCell(new Phrase(total));
					shiftSum_table.addCell(new_cell);
					datePos++;
					if (datePos == 0) {
						columnItr++;
					}
				} else if (datePos % 5 == 4) {
					for (SummaryInfo si2 : report.getInfo_vec()) {
						if (si.getLocation().equals("Packing")) {
							total += si2.getValue();
						}
					}
					new_cell = new PdfPCell(new Phrase(total));
					shiftSum_table.addCell(new_cell);
					datePos++;
					if (datePos == 0) {
						columnItr++;
					}
				}
				datePos++;
			}
			doc.add(shiftSum_table);

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ptr_inv(Invoice report, String file_name) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			doc.open();

			String title = "Invoice " + report.getReportID() + " / " + report.getDate();
			String custname = "Customer Name: " + report.getCompany();
			String accNo = "Account Number: " + report.getAccountNo();
			String contact = "Contact Name: " + report.getName();
			String address = "Address: " + report.getAddress();
			String phone = "Phone: " + report.getPhone();
			String JNCD = "Job Number: " + report.getJobNo() + "                    " + "Completed: " + report.getCompletedDate();

			Paragraph p_title = new Paragraph(new Phrase(title, FontFactory.getFont(FontFactory.defaultEncoding, 20.0f))), p_custname = new Paragraph(custname), p_accNo = new Paragraph(accNo),
					p_contact = new Paragraph(contact), p_address = new Paragraph(address), p_phone = new Paragraph(phone), p_JNCD = new Paragraph(JNCD);

			p_title.setIndentationLeft(130);
			doc.add(p_title);
			doc.add(new Paragraph(new Phrase(" ")));
			doc.add(new Paragraph(new Phrase("Account: " + report.getAccountNo())));
			doc.add(new Paragraph(new Phrase(" ")));
			doc.add(new Paragraph(new Phrase(" ")));
			doc.add(p_custname);
			doc.add(p_accNo);
			doc.add(p_contact);
			doc.add(p_address);
			doc.add(p_phone);
			doc.add(p_JNCD);
			doc.add(new Paragraph(new Phrase(" ")));
			doc.add(new Paragraph(new Phrase("Description of work:")));
			PdfPTable table = new PdfPTable(2);

			PdfPCell new_cell = new PdfPCell(new Phrase("Task IDs"));
			table.addCell(new_cell);
			new_cell = new PdfPCell(new Phrase("Price (£)"));
			table.addCell(new_cell);
			table.setHeaderRows(1);

			float total = 0;
			for (int i = 0; i < report.getJob().getVecTaskJ().getNoOfTasks(); i++) {
				new_cell = new PdfPCell(new Phrase(i + 1));
				table.addCell(new_cell);
				float temp = report.getJob().getVecTaskJ().retrieveTask(i).getPrice();
				new_cell = new PdfPCell(new Phrase(Float.toString(temp)));
				total += temp;
				table.addCell(new_cell);
			}

			table.addCell(new Phrase("Sub-Total"));
			table.addCell(new Phrase(Float.toString(total)));
			table.addCell(new Phrase("Discount agreed:"));
			table.addCell(new Phrase(Float.toString(report.getDiscount())));
			table.addCell(new Phrase(" "));
			table.addCell(new Phrase(Float.toString(total * (1 - report.getDiscount()))));
			table.addCell(new Phrase("Total payable (VAT at 20%)"));
			table.addCell(new Phrase(Float.toString((total * (1 - report.getDiscount())) * 0.8f)));

			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void print(Report report) {
		//set file name
		/////////////////////////////
		//replace with JFileChooser//
		/////////////////////////////
		String file_name = "reports\\";
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
			//call printing function
			prt_psr((PerformanceSummary) report, file_name);
		}
		if (report instanceof Invoice) {
			file_name += "Invoice " + ((Invoice) report).getDate() + " " + ((Invoice) report).getAccountNo() + " " + ((Invoice) report).getJobNo() + ".pdf";
			ptr_inv((Invoice) report, file_name);
		}
	}

	public void cancelPrint() {
		throw new UnsupportedOperationException();
	}

	public PrinterGateway() {}
}