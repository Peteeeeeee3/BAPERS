package GUI;

import javax.swing.*;

public class Form {
	private GUIControl guiControl;
	private JButton confirmButton = new JButton("Confirm");
	private JButton backButton = new JButton("Back");
	private JButton cancelButton = new JButton("Cancel");
	private JButton nextButton = new JButton("Next");

	public Form(GUIControl guiControl) {
		this.guiControl = guiControl;

		//confirm button
		confirmButton.setToolTipText("Confirm submission.");

	}


}