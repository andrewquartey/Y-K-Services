package com.bendo.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JOptionPane;

public class SmsBackend {

	String sender_id = "YK LTD";
	final String KEY = "fe2eb8db51156dfe2c20";  // I am using Benjamin's API key
	String message;
	String number;
	String company;
	String amount;
	String errCode;
	
	public SmsBackend(String company, String number, String amount){
		this.company = company;		
		this.number = number;
		this.amount = amount;		
	}
	
	public  void issueInvoice() throws Exception {
		
		message = "Hello " + company + " an invoice valued at " + amount + " Ghana cedis has been issued to you." +
					" Thank you. For further enquires call 0245778778.";// + sender_id +".";		
//		System.out.println(number);	
		
		int response = JOptionPane.showConfirmDialog(null,
			    "Your message will be sent to " + number + " as follows:\n" + message ,
			    "Confirm",					    
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION){
			sender_id = URLEncoder.encode(sender_id, "utf-8");		// sender id is encoded so that " " and "&" will appear
			// Mnotify api
			URL msg = new URL("http://bulk.mnotify.net/smsapi?key=" + KEY + "&to="
					+ number + "&msg=" + message + "&sender_id=" + sender_id);
			
			URLConnection info = msg.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                					info.getInputStream()));
			
			errCode = in.readLine().replaceAll("\\s+","");		//the is an \s before the first character so the need to strip it off
			System.out.println("a"+errCode+"a");
			errCode.replaceAll("\\s+","");	
			switch(errCode){
			case "1000":
				System.out.println("Successful");
				JOptionPane.showMessageDialog(null,
						"Message Sent!");
				break;
			case "1002":
				System.out.println("SMS sending failed");
				JOptionPane.showMessageDialog(null,
						"SMS sending failed" ,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			case "1003":
				System.out.println("Insufficient balance");
				JOptionPane.showMessageDialog(null,
						"Insufficient balance" ,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			case "1004":
				System.out.println("Invalid API key");
				break;
			case "1005":
				System.out.println("Invalid destination");		
				JOptionPane.showMessageDialog(null,
						"Invalid destination" ,
						"Wrong number",
						JOptionPane.ERROR_MESSAGE);
				break;
			case "1006":
				JOptionPane.showMessageDialog(null,
					"Invalid Sender ID",
					"Error",
					JOptionPane.ERROR_MESSAGE);
				break;
			case "1007":
				JOptionPane.showMessageDialog(null,
					"Message scheduled for later delivery");
				break;
			case "1008":
				JOptionPane.showMessageDialog(null,
					"Empty Message",
					"Error",
					JOptionPane.ERROR_MESSAGE);
				break;
//			default:
//				System.out.println("switch default error");
			}			
			in.close();
		}
	
	}
	
	public void moneyReceived()throws Exception {
		
		message = "Hello " + company + " we have recived " + amount + " Ghana cedis from you for goods purchased from us." +
					" Thank you. For further enquires call 0245778778.";
		
//		System.out.println(number);	
		
		int response = JOptionPane.showConfirmDialog(null,
			    "Your message will be sent to " + number + " as follows:\n" + message ,
			    "Confirm",					    
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION){	
			
//			sender_id = URLEncoder.encode(sender_id, "utf-8");		// sender id is encoded so that " " and "&" will appear
			// Mnotify api
			URL msg = new URL("http://bulk.mnotify.net/smsapi?key=" + KEY + "&to="
					+ number + "&msg=" + message + "&sender_id=" + sender_id);
			
			URLConnection info = msg.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                					info.getInputStream()));
			
			errCode = in.readLine().replaceAll("\\s+","");   //strip off beginning space  			
			System.out.println("a"+errCode+"a");
			switch(errCode){			
			case "1000":
				System.out.println("Successful");
				JOptionPane.showMessageDialog(null,
						"Message Sent!");
				break;
			case "1002":
			System.out.println("SMS sending failed");
			JOptionPane.showMessageDialog(null,
					"SMS sending failed" ,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			break;
			case "1003":
				System.out.println("Insufficient balance");
				JOptionPane.showMessageDialog(null,
						"Insufficient balance" ,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			case "1004":
				System.out.println("Invalid API key");
				break;
			case "1005":
				System.out.println("Invalid destination");		
				JOptionPane.showMessageDialog(null,
						"Invalid destination" ,
						"Wrong number",
						JOptionPane.ERROR_MESSAGE);
				break;
			case "1006":
				JOptionPane.showMessageDialog(null,
					"Invalid Sender ID",
					"Error",
					JOptionPane.ERROR_MESSAGE);
				break;
			case "1007":
				JOptionPane.showMessageDialog(null,
					"Message scheduled for later delivery");
				break;
			case "1008":
				JOptionPane.showMessageDialog(null,
					"Empty Message",
					"Error",
					JOptionPane.ERROR_MESSAGE);
				break;
//			default:
//				System.out.println("switch default error");
			}			
			in.close();
		}
	}
}
