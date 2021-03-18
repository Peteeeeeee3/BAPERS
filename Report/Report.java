package Report;

import java.io.*;
import java.util.Scanner;

public class Report {
	private int time;
	private int reportID;
	private int timeTaken;
	public ReportFacadeControl RFC;

	public boolean isOnDemand(int reportID) {
		throw new UnsupportedOperationException();
	}

	public boolean isAutomated(int reportID) {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getReportID() {
		return this.reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public void setRFC(ReportFacadeControl RFC) {
		this.RFC = RFC;
	}

	public Report() {
		int posCheck = 0;
		try {
			//select file top_ids.txt. This will contain 3 values, each being the highest ID of the appropriate Report.
			File top_ids = new File("lib/top_ids.txt");
			//setup scanner
			Scanner reader = new Scanner(top_ids);
			//create string value
			String[] string_id = new String[3];
			//there are only 3 lines containing values, loop through them
			while(reader.hasNextLine() && posCheck < 3) {
				//get the value
				string_id[posCheck] = reader.nextLine();
				posCheck++;
			}

			//check whether all sots in array have values
			for (int arrayItr = 0; arrayItr < string_id.length; arrayItr++) {
				//if string is empty, the is a formatting error in the file
				if (string_id[arrayItr].equals("")) {
					throw new Exception("Missing correct values in top_ids.txt.");
				}
			}

			//temporarily store id value
			int temp;
			//check for correct Report type
			if (this instanceof CustomerJobReport) {
				//increment the value of the ID
				temp = Integer.parseInt(string_id[0]);
				temp++;
				string_id[0] = String.valueOf(temp);
				//assign ID
				reportID = temp;
			} else if (this instanceof IndividualPerformanceReport) {
				//increment the value of the ID
				temp = Integer.parseInt(string_id[1]);
				temp++;
				string_id[1] = String.valueOf(temp);
				//assign ID
				reportID = temp;
			} else {
				//increment the value of the ID
				temp = Integer.parseInt(string_id[2]);
				temp++;
				string_id[2] = String.valueOf(temp);
				//assign ID
				reportID = temp;
			}

			//update new top_ids values
			FileOutputStream fos = new FileOutputStream(top_ids);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

			//overwrite file with updated values
			for (int itr = 0; itr < string_id.length; itr++) {
				writer.write(string_id[itr]);
				writer.newLine();
			}
			//close writer
			writer.close();

			//handle errors
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}