package GUI;

import Account.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveUserScreen extends JPanel{
    private JPanel removeUserPanel;
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JButton backButton;
    private JButton confirmButton;
    public GUIControl guiControl;
    public UserAccount userAccount;
    int flag = 0;

    public RemoveUserScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
        frame.setContentPane(new RemoveUserScreen(guiControl).removeUserPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public RemoveUserScreen(GUIControl guiControl){
        this.guiControl = guiControl;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting value from textfield
                int id = Integer.parseInt(textField1.getText());
                try {
                    //This if statement calls a function which checks if the ID is present in the database or not
                    if (!getGuiControl().getController().getAccountControl().vecUser.checkId(id)){
                        //If it isnt, this error message is shown
                        JOptionPane.showMessageDialog(confirmButton,"This user does not exist");
                    } else {
                        //Else if the flag is set to 1 after clicking the check box
                        if (flag == 1){
                            //Call remove user method
                            getGuiControl().getController().getAccountControl().vecUser.removeUser(id);
                            JOptionPane.showMessageDialog(confirmButton, "User has been removed");
                        } else {
                            //If checkbox hasnt been clicked, this error message will show
                            JOptionPane.showMessageDialog(confirmButton, "Please tick the remove check box");
                        }
                    }
                    //Catch expressions here for the vecUser.checkID method.
                } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
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
        checkBox1.addActionListener(new ActionListener() {
            @Override
            //Sets flag to 1 when checkbox clicked
            public void actionPerformed(ActionEvent e) {
                flag = 1;
            }
        });
    }

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
