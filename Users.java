package emailapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users{
	private final SimpleIntegerProperty employeeID;
	private final SimpleStringProperty fName;
	private final SimpleStringProperty lName;
	private final SimpleStringProperty emailProp;
	private final SimpleStringProperty deptComboBox;
	
	public Users(int empID, String firstName, String lastName, String email, String deptComboBox){
		this.employeeID = new SimpleIntegerProperty(empID);
		this.fName = new SimpleStringProperty(firstName);
		this.lName = new SimpleStringProperty(lastName);
		this.emailProp = new SimpleStringProperty(email);
		this.deptComboBox = new SimpleStringProperty(deptComboBox);
	}
	
	// Setters and getters
	// EmpID
	public int getEmpID(){
		return employeeID.get();
	}
	
	public void setEmpId(int empID){
		employeeID.set(empID);
	}
	
	// First Name
	public String getFirstName(){
		return fName.get();
	}
	
	public void setFirstName(String firstName){
		fName.set(firstName);
	}
	
	// Last Name
	public String getLastName(){
		return lName.get();
	}
	
	public void setLastName(String lastName){
		lName.set(lastName);
	}
	
	// Email
	public String getEmail(){
		return emailProp.get();
	}
	
	public void setEmail(String email){
		emailProp.set(email);
	}
	
	// Department
	public String getDept(){
		return deptComboBox.get();
	}
	
	public void setDept(String dept){
		deptComboBox.set(dept);
	}
}