package edu.westga.dbaccess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	/**
	 * Entry for the program
	 */
	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("/edu/westga/dbaccess/view/LoginWindow.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
