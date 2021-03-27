package GUI;

import javax.swing.*;

public class FlexibleDiscountScreen extends JPanel {
    public GUIControl guiControl;

    public FlexibleDiscountScreen(GUIControl guiControl, JFrame frame){
        this.guiControl = guiControl;
    }

    public FlexibleDiscountScreen(GUIControl guiControl){this.guiControl = guiControl;}

    public GUIControl getGuiControl() {
        return guiControl;
    }
}
