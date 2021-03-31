package GUI;

import Report.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportDisplayScreen {
    private GUIControl guiControl;
    private JPanel panel1;
    private JButton cancelButton;
    private JButton printButton;
    private JTable table;
    private Report report;

    public ReportDisplayScreen(GUIControl guiControl, JFrame frame, Report report) {
        this.guiControl = guiControl;
        this.report = report;
        frame.setContentPane(new ReportDisplayScreen(guiControl, report).panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    String formatTime(int time) {
        String s_time = "";
        if (time == 0) {
            return "Not yet started";
        }
        if (time < 1000) {
            s_time += "0" + time;
        } else {
            s_time += Integer.toString(time);
        }
        s_time = s_time.substring(0, 2) + ":" + s_time.substring(2, 4);
        return s_time;
    }

    String formatDate(int date) {
        String s_date = Integer.toString(date);
        s_date = s_date.substring(0, 4) + "/" + s_date.substring(4, 6) + "/" + s_date.substring(6, 8);
        return s_date;
    }

    public ReportDisplayScreen(GUIControl guiControl, Report report) {
        this.guiControl = guiControl;
        String text = "";

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        if (report instanceof IndividualPerformanceReport) {

            //set up table
            table.setModel(defaultTableModel);
            defaultTableModel.addColumn("Name");
            defaultTableModel.addColumn("Task IDs");
            defaultTableModel.addColumn("Department");
            defaultTableModel.addColumn("Date");
            defaultTableModel.addColumn("Start Time");
            defaultTableModel.addColumn("Time Taken");
            defaultTableModel.addColumn("Total");

            String previousName = "";
            int total = 0;
            String empty = " ";

            for (int namesItr = 0; namesItr < ((IndividualPerformanceReport) report).getNames().size(); namesItr++) {
                String name = ((IndividualPerformanceReport) report).getNames().get(namesItr);
                total += ((IndividualPerformanceReport) report).getDurations().get(namesItr);

                if (previousName.equals(name)) {
                    name = " ";
                }

                if (namesItr >= ((IndividualPerformanceReport) report).getNames().size() - 1) {
                    defaultTableModel.addRow(new Object[]{name,
                            ((IndividualPerformanceReport) report).getTaskIDs().get(namesItr),
                            ((IndividualPerformanceReport) report).getLocations().get(namesItr),
                            formatDate(((IndividualPerformanceReport) report).getDates().get(namesItr)),
                            formatTime(((IndividualPerformanceReport) report).getStartTimes().get(namesItr)),
                            ((IndividualPerformanceReport) report).getDurations().get(namesItr),
                            total});

                } else if (!((IndividualPerformanceReport) report).getNames().get(namesItr + 1).equals(((IndividualPerformanceReport) report).getNames().get(namesItr))) {
                    defaultTableModel.addRow(new Object[]{name,
                            ((IndividualPerformanceReport) report).getTaskIDs().get(namesItr),
                            ((IndividualPerformanceReport) report).getLocations().get(namesItr),
                            formatDate(((IndividualPerformanceReport) report).getDates().get(namesItr)),
                            formatTime(((IndividualPerformanceReport) report).getStartTimes().get(namesItr)),
                            ((IndividualPerformanceReport) report).getDurations().get(namesItr),
                            total});
                    total = 0;
                } else {
                    defaultTableModel.addRow(new Object[]{name,
                            ((IndividualPerformanceReport) report).getTaskIDs().get(namesItr),
                            ((IndividualPerformanceReport) report).getLocations().get(namesItr),
                            formatDate(((IndividualPerformanceReport) report).getDates().get(namesItr)),
                            formatTime(((IndividualPerformanceReport) report).getStartTimes().get(namesItr)),
                            ((IndividualPerformanceReport) report).getDurations().get(namesItr),
                            empty});
                }

                name = ((IndividualPerformanceReport) report).getNames().get(namesItr);
                previousName = name;
            }
        } else if
        (report instanceof CustomerJobReport) {
            table.setModel(defaultTableModel);
            defaultTableModel.addColumn("Job");
            defaultTableModel.addColumn("Price, GBP");
            defaultTableModel.addColumn("Task");
            defaultTableModel.addColumn("Department");
            defaultTableModel.addColumn("Start Time");
            defaultTableModel.addColumn("Time Taken");
            defaultTableModel.addColumn("Completed by");

            for (ReportTask rt : ((CustomerJobReport) report).getInfoVec()) {
                defaultTableModel.addRow(new Object[]{rt.getJob(), rt.getPrice(), rt.getTask(), rt.getDepartment(), rt.getStartTime(), rt.getTimeTaken(), rt.getCompletedBy()});
            }
        } else if
        (report instanceof PerformanceSummary) {
            table.setModel(defaultTableModel);
        }

        //print button
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(report);
                System.out.println(guiControl);
                guiControl.getController().getPrinterGateway().print(report);
            }
        });

    }
}
