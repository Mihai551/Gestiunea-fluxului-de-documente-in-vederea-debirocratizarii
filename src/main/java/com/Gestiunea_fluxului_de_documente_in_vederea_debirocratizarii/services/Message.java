package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import javax.swing.JOptionPane;

public class Message {

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}


