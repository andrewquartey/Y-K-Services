package com.bendo.sms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class SmsUI {	
	
//	JTextField textField1;
//	JTextField textField2;
	String phoneNum;
	String amount;
	String companyName;	
	
	public void start(){
		invoiceFrame();		 
	}
	
	public void invoiceFrame(){
		final JFrame frame = new JFrame("SMS Tally Sender");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600,130));
		
		// This sets the icon in the frame
		InputStream input = SMSMainClass.class.getClassLoader().getResourceAsStream("sms.gif");
		Image logo = null;
		try {
			logo = ImageIO.read(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame.setIconImage(logo);
		
		
		//Create Components
		//TextField
		final JTextField textField1 = new JTextField(20); //company name
		final JTextField textField2 = new JTextField(20); //phone number
		final JTextField textField3 = new JTextField(20); // amount
		//Buttons
		JButton button1 = new JButton("Send Invoice");
		JButton button2 = new JButton("Send Money Received");
		JButton button3 = new JButton("Cancel");
		
		//add event listener to buttons
		//send button
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				companyName = textField1.getText();
				phoneNum = textField2.getText();
				amount = textField3.getText();			
				
				if(!(companyName.equals("") || phoneNum.equals("") || amount.equals(""))){				
					System.out.println(companyName + " " + phoneNum + " " + amount);
					SmsBackend sms = new SmsBackend(companyName, phoneNum, amount);
					try {
						sms.issueInvoice();
					} catch (Exception e) {								// Catches "no internet connection error" 
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Please check your internet connection",
								"Network Error",
								JOptionPane.ERROR_MESSAGE);
						System.out.println("You are not connected to the internet.");
					}
				}
										
				else {
					JOptionPane.showMessageDialog(null,
							"Please fill in the empty field(s)",
							"Input Error",
							JOptionPane.ERROR_MESSAGE);					
				}
			}			
		});
		
		//Money Recieved button
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				companyName = textField1.getText();
				phoneNum = textField2.getText();
				amount = textField3.getText();
				
				if(!(companyName.equals("") || phoneNum.equals("") || amount.equals(""))){				
					System.out.println(companyName + " " + phoneNum + " " + amount);
					SmsBackend sms = new SmsBackend(companyName, phoneNum, amount);
					try {
						sms.moneyReceived();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Please check your internet connection",
								"Network Error",
								JOptionPane.ERROR_MESSAGE);
						System.out.println("You are not connected to the internet.");
					}
				}				
				else {
					JOptionPane.showMessageDialog(null,
							"Please fill in the empty field(s)",
							"Input Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}			
		});
		
		//cancel button
		button3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int response = JOptionPane.showConfirmDialog(null,
					    "Do you want to exit?",
					    "Confirm",					    
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});
		
		//Labels
		JLabel label1 = new JLabel("Company Name: ");		
		JLabel label2 = new JLabel("Phone number:   ");
		JLabel label3 = new JLabel("Amount:                ");
		
		//set layout for panel
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		//specify automatic gap insertion
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Establish layout group
		//horizontal				
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(label1)
						.addComponent(label2)
						.addComponent(label3))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(textField1)
							.addComponent(textField2)
							.addComponent(textField3))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(button1)							
							.addComponent(button2)
							.addComponent(button3)
							)
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, button1, button2, button3);
		
		//vertical
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label1)
							.addComponent(textField1)
							.addComponent(button1))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label2)
							.addComponent(textField2)
							.addComponent(button2))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label3)
							.addComponent(textField3)
							.addComponent(button3))		
		);
	
		frame.setResizable(false);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setLocation(500,200);
		frame.pack();
		frame.setVisible(true);	
		
//		System.out.println(companyName + " " + phoneNum + " " + amount);		
	}		
}
