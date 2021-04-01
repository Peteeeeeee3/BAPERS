package GUI;

import Job.Job;
import com.itextpdf.text.Paragraph;
import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class TableMouseListener extends MouseAdapter {
    private PaymentScreen s;

    public TableMouseListener(PaymentScreen s) {
        this.s = s;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JTable table = (JTable) e.getSource();
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);
        int col = table.columnAtPoint(p);

        if (SwingUtilities.isLeftMouseButton(e)) {
            s.setTotal(s.getTotal() + Float.parseFloat(table.getValueAt(row, 1).toString()));
            //System.out.println(s.getTotal());
            s.getPriceField().setText("Price: " + s.getTotal() + "GBP");
        }
    }
}
