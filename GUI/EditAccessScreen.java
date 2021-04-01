package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditAccessScreen extends JPanel {
    private JTextField textField1;
    private JPanel editAccessPanel;
    private JCheckBox editAccessCheckBox;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField accessLevel;
    public GUIControl guiControl;
    int flag = 0;

    public EditAccessScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new EditAccessScreen(guiControl).editAccessPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public EditAccessScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        editAccessCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If the check box is pressed a variable called flag gets updated to 1
                flag = 1;
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (guiControl.getAccess()) {
                    case 1:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useHomepage(guiControl);
                        break;
                    case 2:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useTechHomePage(guiControl);
                        break;
                    case 3:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useSMHomePage(guiControl);
                        break;
                    case 4:
                        getGuiControl().closeCurrentFrame();
                        guiControl.useOMHomePage(guiControl);
                        break;
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting the values from the text fields
                int id = Integer.parseInt(textField1.getText());
                int nAccessLevel = Integer.parseInt(accessLevel.getText());
                //If the flag is 1
                if (flag == 1){
                    //Then the method gets called
                    boolean check = getGuiControl().getController().getAccountControl().updateAccess(id, nAccessLevel);

                    if (!check) {
                        JOptionPane.showMessageDialog(confirmButton, "Incorrect entries! Please try again.");
                    } else {
                        //And the popup shows this
                        JOptionPane.showMessageDialog(confirmButton, "Access level has been changed.");
                    }
                } else {
                    //If flag is still 0, this message will pop up
                    if (flag == 0) {
                        JOptionPane.showMessageDialog(confirmButton, "Please tick the checkbox.");
                    }
                }
                guiControl.useEditAccessScreen(guiControl);
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
