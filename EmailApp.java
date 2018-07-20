package emailapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class EmailApp extends Application{
	
	public static void main(String[] args){
		// Launch the GUI
		launch(args);
	}
	
	// Variables
	Button submitButton;
	Button closeButton;
	Stage window;
	Scene mainScene;
	ComboBox<String> deptComboBox;
	int defaultPasswordLen = 15;
	private String password;
	private String companySuffix = "DevTek.com";
	

	// GUI
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Email Generation Application");
		
		// Labels
		Label firstNameLabel = new Label("First Name");
		Label lastNameLabel = new Label("Last Name");
		
		// Text Fields
		TextField firstNameInput = new TextField();
		firstNameInput.setPromptText("First Name");
		TextField lastNameInput = new TextField();
		lastNameInput.setPromptText("Last Name");
		
		// Text Area
		TextArea textArea = new TextArea();
		
		// ComboBox
		deptComboBox = new ComboBox<>();
		deptComboBox.getItems().addAll(
				"Accounting",
				"Human Resources",
				"Information Technology",
				"Management",
				"Sales"
				);
		deptComboBox.setPromptText("Please select a department");
		
		// Buttons
		submitButton = new Button();
		submitButton.setText("Submit");
			
		// Button events
		submitButton.setOnAction(e -> isChar(firstNameInput, lastNameInput, firstNameInput.getText(), lastNameInput.getText(), textArea, randomPassword(defaultPasswordLen), deptComboBox.getValue()));
		
		// Main Scene/layout
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(firstNameLabel, 0, 0);
		GridPane.setConstraints(firstNameInput, 1, 0);
		GridPane.setConstraints(lastNameLabel, 0, 1);
		GridPane.setConstraints(lastNameInput, 1, 1);
		GridPane.setConstraints(submitButton, 1, 3);
		GridPane.setConstraints(deptComboBox, 1, 2);
		GridPane.setConstraints(textArea, 1, 4);
		
		grid.getChildren().addAll(firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, submitButton, deptComboBox, textArea);
		
		mainScene = new Scene(grid, 570, 300);
		window.setScene(mainScene);
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		window.show();
	}
	
	// Close the program
	private void closeProgram(){
		Boolean answer = ConfirmBox.display("End Program", "Are you sure you want to close the program?");
		if(answer){
			window.close();
		}
	}
	
	// Generate a random password for the new user
		private String randomPassword(int length){
			String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?$#@";
				char[] password = new char[length];
				
				for(int i = 0; i < length; i++){
					int rand = (int) (Math.random() * passwordSet.length());
					password[i] = passwordSet.charAt(rand);
				}
		return new String(password);
	}
	
	// Validate their name and generate them an email
	private boolean isChar(TextField fistNameInput, TextField lastNameInput, String fNMessage, String lNMessage, TextArea textArea, String password, String deptComboBox){
			// Initialize the variables
			String regex = "^[a-zA-Z]+$";
			String firstName = fistNameInput.getText();
			String lastName = lastNameInput.getText();
			String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + companySuffix;
			
			
			// Validate that the strings contain only letters
			if(firstName.matches(regex) && lastName.matches(regex)){
				textArea.setText(firstName + " " + lastName + " Successfully added to the database."
						+ "\nHere's your information: " + "\nFirst Name: " + firstName +"\nLast Name: " + lastName + "\nDepartment: " + deptComboBox +
						"\nEmail: " + email + "\nTemporary Password: " +  password
						);
				return true;
			}
			else{
				textArea.setText("Error, please chech the spelling for invalid characters.");
				return false;
			}
	}
	
}
/*
try{
	// Establish a connection to the database
	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaemailapp", "Admin", "********");
	// Create a statement
	Statement myStmt = myConn.createStatement();
	// Execute SQL query
	ResultSet myRs = myStmt.executeQuery("SELECT * FROM employees");
	// Process the result process
	while(myRs.next()){
		System.out.println("Emp Id: " + myRs.getInt("empID") + "\nFirst Name: " + myRs.getString("firstName") + "\nLast Name: " + 
		myRs.getString("lastName") + "\nEmail: " + myRs.getString("email"));
	}
	
} catch(Exception exc){
	exc.printStackTrace();
}



//Email em1 = new Email("John", "Smith");
//System.out.println(em1.showInfo());

*/