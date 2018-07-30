package emailapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.sql.*;

public class EmailApp extends Application{
	
	public static void main(String[] args){
		// Launch the GUI
		launch(args);
	}

	// Variables
	Button submitButton, closeButton, viewUsersButton, goBackButton, refreshButton;
	Stage window;
	Scene mainScene, viewUsers, alterUsers;
	ComboBox<String> deptComboBox;
	TableView<Users> table;
	ObservableList<Users> empList;
	int defaultPasswordLen = 15;
	private String companySuffix = "DevTek.com";
	

	// GUI
	@SuppressWarnings("unchecked")
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
		viewUsersButton = new Button();
		viewUsersButton.setText("View users");
		goBackButton = new Button();
		goBackButton.setText("Go back");
		refreshButton = new Button();
		refreshButton.setText("Refresh");
			
		// Button events
		submitButton.setOnAction(e -> 
			isChar(firstNameInput, lastNameInput, firstNameInput.getText(), lastNameInput.getText(), textArea, randomPassword(defaultPasswordLen), deptComboBox.getValue()));
		viewUsersButton.setOnAction(e -> 
			window.setScene(viewUsers));
		goBackButton.setOnAction(e ->
			window.setScene(mainScene));
		refreshButton.setOnAction(e ->
			refreshTable());
		
		// Main Scene/layout
		GridPane mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(10,10,10,10));
		mainGrid.setVgap(8);
		mainGrid.setHgap(10);
		GridPane.setConstraints(firstNameLabel, 0, 0);
		GridPane.setConstraints(firstNameInput, 1, 0);
		GridPane.setConstraints(lastNameLabel, 0, 1);
		GridPane.setConstraints(lastNameInput, 1, 1);
		GridPane.setConstraints(submitButton, 0, 3);
		GridPane.setConstraints(viewUsersButton, 1, 3);
		GridPane.setConstraints(deptComboBox, 1, 2);
		GridPane.setConstraints(textArea, 1, 4);
		
		mainGrid.getChildren().addAll(firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, submitButton, viewUsersButton, deptComboBox, textArea);
		
		mainScene = new Scene(mainGrid, 570, 300);
		window.setScene(mainScene);
		
		// View users scene
		GridPane userViewGrid = new GridPane();
		viewUsers = new Scene(userViewGrid, 1000, 500);
		userViewGrid.setPadding(new Insets(10,10,10,10));
		userViewGrid.setVgap(8);
		userViewGrid.setHgap(10);
		
		table = new TableView<>();
		getUsers();
		table.setItems(empList);
		
		// Columns
		TableColumn<Users, Integer> empIdColumn = new TableColumn<Users, Integer>("Employee ID");
		empIdColumn.setMinWidth(10);
		empIdColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("EmpID"));
								
		TableColumn<Users, String> firstNameColumn = new TableColumn<Users, String>("First Name");
		firstNameColumn.setMinWidth(200);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
								
		TableColumn<Users, String> lastNameColumn = new TableColumn<Users, String>("Last Name");
		lastNameColumn.setMinWidth(200);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
								
		TableColumn<Users, String> emailColumn = new TableColumn<Users, String>("Email");
		emailColumn.setMinWidth(200);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
								
		TableColumn<Users, String> deptColumn = new TableColumn<Users, String>("Dept");
		deptColumn.setMinWidth(200);
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("Dept"));
		
		table.getColumns().addAll(empIdColumn, firstNameColumn, lastNameColumn, emailColumn, deptColumn);
		
		GridPane.setConstraints(goBackButton, 0, 5);
		GridPane.setConstraints(refreshButton, 1, 5);
		GridPane.setConstraints(table, 0, 0);
		userViewGrid.getChildren().addAll(goBackButton, refreshButton, table);
		
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
	
	// Populate the user table with information from the database
	public ObservableList<Users> getUsers(){
		empList = FXCollections.observableArrayList();
		
		try{
			// Establish a connection to the database
			String url = "jdbc:mysql://localhost:3306/javaemailapp?autoReconnect=true&useSSL=true";
			String user = "Admin";
			String dbPassword = "********";
			
			Connection myConn = DriverManager.getConnection(url, user, dbPassword);
			// Execute an SQL query
			String sql = "SELECT `empID`, `firstName`, `lastName`, `email`, `deptComboBox` FROM `employees` WHERE `empID` > 0";	
					
			ResultSet rs = myConn.createStatement().executeQuery(sql);
		
			while(rs.next()){
				empList.add(new Users(rs.getInt("empID"), rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"), rs.getString("deptComboBox")));
				}
		} catch(Exception exc){
			exc.printStackTrace();
		}
		return empList;
	}
	
	// Refresh the table
	public void refreshTable(){
		empList.clear();
		try{
			// Establish a connection to the database
			String url = "jdbc:mysql://localhost:3306/javaemailapp?autoReconnect=true&useSSL=true";
			String user = "Admin";
			String dbPassword = "********";
			
			Connection myConn = DriverManager.getConnection(url, user, dbPassword);
			// Execute an SQL query
			String sql = "SELECT `empID`, `firstName`, `lastName`, `email`, `deptComboBox` FROM `employees` WHERE `empID` > 0";	
					
			ResultSet rs = myConn.createStatement().executeQuery(sql);
		
			while(rs.next()){
				empList.add(new Users(rs.getInt("empID"), rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"), rs.getString("deptComboBox")));
				}
		} catch(Exception exc){
			exc.printStackTrace();
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
	
	// Validate their name and generate them an email and then submit that information to the database
	private boolean isChar(TextField fistNameInput, TextField lastNameInput, String fNMessage, String lNMessage, TextArea textArea, String password, String deptComboBox){
			// Initialize the variables
			String regex = "^[a-zA-Z]+$";
			String firstName = fistNameInput.getText();
			String lastName = lastNameInput.getText();
			String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + companySuffix;
			
			
			// Validate that the strings contain only letters
			if(firstName.matches(regex) && lastName.matches(regex)){
				// Submit the information to the database
				try{
					// Establish a connection to the database
					String url = "jdbc:mysql://localhost:3306/javaemailapp?autoReconnect=true&useSSL=true";
					String user = "Admin";
					String dbPassword = "********";
					
					Connection myConn = DriverManager.getConnection(url, user, dbPassword);
					// Create a statement
					Statement myStmt = myConn.createStatement();
					// Execute an SQL query
					String sql = "INSERT INTO employees "
							+ "(firstName, lastName, email, password, deptComboBox)"
							+ "VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+password+"', '"+deptComboBox+"')";
					myStmt.executeUpdate(sql);
					textArea.setText("Insert complete."
							+ "\nHere's your information: " + "\nFirst Name: " + firstName +"\nLast Name: " + lastName + "\nDepartment: " + deptComboBox +
							"\nEmail: " + email + "\nTemporary Password: " +  password
							);
					
				} catch(Exception exc){
					exc.printStackTrace();
					textArea.setText("Insert failed.");
				}
				return true;
			}
			else{
				textArea.setText("Error, please chech the spelling for invalid characters.");
				return false;
			}
	}
}
