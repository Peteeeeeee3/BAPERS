package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDiscountScreen extends Form {
    private JButton fixedDiscountButton;
    private JPanel selectDiscountPanel;
    private JButton variableDiscountButton;
    private JButton flexibleDiscountButton;
    private JButton cancelButton;

    public SelectDiscountScreen(GUIControl guiControl) {
        super(guiControl);
    }

//    public static void main(String args[]){
//        JFrame frame = new JFrame("Select Discount");
//        frame.setContentPane(new SelectDiscountScreen().selectDiscountPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,300);
//        frame.setVisible(true);
//    }
}
