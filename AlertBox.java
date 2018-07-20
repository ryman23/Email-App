package emailapp;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	
	public static void display(String title, String message){
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
		
		// Button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> window.close());
		
		// Create the layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, exitButton);
		layout.setAlignment(Pos.CENTER);
		
		// Create the scene
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
