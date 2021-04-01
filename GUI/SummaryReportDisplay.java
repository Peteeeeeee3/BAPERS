package GUI;

import Report.PerformanceSummary;
import Report.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SummaryReportDisplay {
    private JPanel sumdisPanel;
    private JTable shift1;
    private JTable shift2;
    private JTable shift3;
    private JTable shiftSum;
    private JButton Generate;
    private JButton Cancel;
    private GUIControl guiControl;
    private Report report;

    public SummaryReportDisplay(GUIControl guiControl, JFrame frame, Report report) {
        this.guiControl = guiControl;
        this.report = report;
        frame.setContentPane(new SummaryReportDisplay(guiControl, (PerformanceSummary) report).sumdisPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public SummaryReportDisplay(GUIControl guiControl, PerformanceSummary report) {
        this.guiControl = guiControl;
        this.report = report;

        DefaultTableModel shift1Table_M = new DefaultTableModel();
        DefaultTableModel shift2Table_M = new DefaultTableModel();
        DefaultTableModel shift3Table_M = new DefaultTableModel();
        DefaultTableModel shiftSumTable_M = new DefaultTableModel();

        //add columns
        shift1Table_M.addColumn("Date");
        shift1Table_M.addColumn("Copy Room");
        shift1Table_M.addColumn("Development");
        shift1Table_M.addColumn("Finishing");
        shift1Table_M.addColumn("Packing");
        shift2Table_M.addColumn("Date");
        shift2Table_M.addColumn("Copy Room");
        shift2Table_M.addColumn("Development");
        shift2Table_M.addColumn("Finishing");
        shift2Table_M.addColumn("Packing");
        shift3Table_M.addColumn("Date");
        shift3Table_M.addColumn("Copy Room");
        shift3Table_M.addColumn("Development");
        shift3Table_M.addColumn("Finishing");
        shift3Table_M.addColumn("Packing");
        shiftSumTable_M.addColumn("Date");
        shiftSumTable_M.addColumn("Copy Room");
        shiftSumTable_M.addColumn("Development");
        shiftSumTable_M.addColumn("Finishing");
        shiftSumTable_M.addColumn("Packing");

        //shift 1
        shift1.setModel(shift1Table_M);
        shift1Table_M.addRow(new Object[] {"Date", "Copy Room", "Development", "Finishing", "Packing"});
        for (SummaryInfo si : report.getInfo_vec()) {
            int date = si.getDate();
            String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
                    Integer.toString(date).substring(6, 8);
            int copy_room = 0, development = 0, finishing = 0, packing = 0;
            for (SummaryInfo si_inner : report.getInfo_vec()) {
                if (si_inner.getShift() == 1) {
                    if (si_inner.getDate() == date) {
                        if (si_inner.getLocation().equals("Copy Room")) {
                            copy_room = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Development")) {
                            development = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Finishing")) {
                            finishing = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Packing")) {
                            packing = si_inner.getValue();
                        }
                    }
                }
                shift1Table_M.addRow(new Object[]{dateStr, copy_room, development, finishing, packing});
            }
        }

        shift2.setModel(shift2Table_M);
        shift2Table_M.addRow(new Object[] {"Date", "Copy Room", "Development", "Finishing", "Packing"});
        //shift 2
        for (SummaryInfo si : report.getInfo_vec()) {
            int date = si.getDate();
            String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
                    Integer.toString(date).substring(6, 8);
            int copy_room = 0, development = 0, finishing = 0, packing = 0;
            for (SummaryInfo si_inner : report.getInfo_vec()) {
                if (si_inner.getShift() == 2) {
                    if (si_inner.getDate() == date) {
                        if (si_inner.getLocation().equals("Copy Room")) {
                            copy_room = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Development")) {
                            development = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Finishing")) {
                            finishing = si_inner.getValue();
                        } else if (si_inner.getLocation().equals("Packing")) {
                            packing = si_inner.getValue();
                        }
                    }
                }
                shift2Table_M.addRow(new Object[]{dateStr, copy_room, development, finishing, packing});
            }
        }

        //shift 3
        shift3.setModel(shift3Table_M);
        shift3Table_M.addRow(new Object[] {"Date", "Copy Room", "Development", "Finishing", "Packing"});
        for (SummaryInfo si : report.getInfo_vec()) {
            int date = si.getDate();
            String dateStr = Integer.toString(date).substring(0, 4) + "/" + Integer.toString(date).substring(4, 6) + "/" +
                    Integer.toString(date).substring(6, 8);
            int copy_room = 0, development = 0, finishing = 0, packing = 0;
            for (SummaryInfo si_inner : report.getInfo_vec()) {
                if (si_inner.getShift() == 3) {
                    if (si_inner.getDate() == date) {
                        switch (si_inner.getLocation()) {
                            case "Copy Room":
                                copy_room = si_inner.getValue();
                                break;
                            case "Development":
                                development = si_inner.getValue();
                                break;
                            case "Finishing":
                                finishing = si_inner.getValue();
                                break;
                            case "Packing":
                                packing = si_inner.getValue();
                                break;
                        }
                    }
                }
                shift3Table_M.addRow(new Object[]{dateStr, copy_room, development, finishing, packing});
            }
        }

        shiftSum.setModel(shiftSumTable_M);
        shiftSumTable_M.addRow(new Object[] {" ", "Copy Room", "Development", "Finishing", "Packing"});
        for (int r = 1; r< 5; r++) {
            String shift = "";
            int copy_room = 0, development = 0, finishing = 0, packing = 9;

            if (r == 1) {
                shift = "Day Shift 1";
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Copy Room")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Development")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Finishing")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Packing")) {
                        copy_room += si_inner.getValue();
                    }
                }
            } else if (r == 2) {
                shift = "Day Shift 2";
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Copy Room")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Development")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Finishing")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Packing")) {
                        copy_room += si_inner.getValue();
                    }
                }
            } else if (r == 3) {
                shift = "Night Shift";
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Copy Room")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Development")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Finishing")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Packing")) {
                        copy_room += si_inner.getValue();
                    }
                }
            } else {
                shift = "Total";
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Copy Room")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Development")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Finishing")) {
                        copy_room += si_inner.getValue();
                    }
                }
                for (SummaryInfo si_inner : report.getInfo_vec()) {
                    if (si_inner.getLocation().equals("Packing")) {
                        copy_room += si_inner.getValue();
                    }
                }
            }

        }

        //shift summary

        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.getController().getPrinterGateway().print(report);
            }
        });
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiControl.closeCurrentFrame();
            }
        });
    }
}
