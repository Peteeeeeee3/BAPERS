package GUI;

import javax.swing.*;

public class Popup extends JFrame {
    private JPanel panel1;
    private JLabel text;

    public Popup(String text) {
        this.text.setText(text);
        setTitle("Error");
        add(panel1);
    }
}
