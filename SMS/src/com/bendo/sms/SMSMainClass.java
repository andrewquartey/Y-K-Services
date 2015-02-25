package com.bendo.sms;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SMSMainClass {
	
	public static void main(String[] args){
		
		// Windows Default look and feel
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SmsUI ui = new SmsUI();
		ui.start(); 
	}
}
