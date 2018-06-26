package emailapp;

import java.util.Scanner;

public class Email {
	// Declare the variables
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private int mailboxCapacity = 500;
	private int defaultPasswordLength = 10;
	private String alternateEmail;
	private String companySuffix = "RileyCompanies.com";
	
	// Constructor to receive the first name and the last name
	public Email(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		System.out.println("Email created for: " + this.firstName + " " + this.lastName);
		
		// Call a method asking for the department and then return the department
		this.department = setDept();
		System.out.println("Department: " + this.department);
		
		// Call a method that returns a random password
		this.password = randomPassword(defaultPasswordLength);
		System.out.println("Your password is: " + this.password);
		
		// Combine the elements to generate an email address
		email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
		System.out.println("Your email is: " + email);
	}
	
	// Ask for the employee's department
	private String setDept(){
		System.out.print("New employee: " + firstName + " " + lastName + " " + "Enter the department code:\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None\nEnter department code: ");
		Scanner in = new Scanner(System.in);
		int deptChoice = in.nextInt();
		if(deptChoice == 1){
			return "Sales";
		} else if (deptChoice == 2){
			return "Dev";
		} else if (deptChoice == 3){
			return "Accounting";
		} else{
			return "";
		}
		
	}
	
	// Generate a random password
	private String randomPassword(int length){
		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?$#@";
		char[] password = new char[length];
		for(int i = 0; i < length; i++){
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
		}
		return new String(password);
	}
	
	// Set the mailbox capacity
	public void setMailboxCapacity(int capacity){
		this.mailboxCapacity = capacity;
	}
	
	// Set the alternate email
	public void setAlternateEmail(String altEmail){
		this.alternateEmail = altEmail;
	}
	
	// Change the password
	public void changePassword(String password){
		this.password = password;
	}
	
	// Show mailbox capacity
	public int getMailboxCapacity(){
		return mailboxCapacity;
	}
	
	// Show alternate email
	public String getAlternateEmail(){
		return alternateEmail;
	}
	
	// Show the password
	public String getPassword(){
		return password;
	}
	
	// Display the important info
	public String showInfo(){
		return "Display Name: " + firstName + " " + lastName +
			   "\nCompany Email: " + email + 
			   "\nMailbox Capacity: " + mailboxCapacity + "mb"; 
	}
}
