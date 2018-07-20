package emailapp;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message){
		// Create the stage
		Stage window = new Stage();
		
		// Create the window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(200);
		window.setMinHeight(200);
		
		// Label
		Label label = new Label();
		label.setText(message);
		
		// Buttons
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		Button noButton = new Button("No");
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		// Create the layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		
		// Create the scene
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		// Return the result
		return answer;
	}

}