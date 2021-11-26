package edu.westga.dbaccess.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * The window Generator Class
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class WindowGenerator {
	
	/**
	 * Generates a new Window
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param title the title of the window 
	 * @param fxmlfilePath the fxml file path
	 */
	public void generateWindow(String title, String fxmlFilePath, ActionEvent event) {
		Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFilePath));

			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle(title);

			stage.setScene(new Scene(root, 452, 440));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException exception) {
			exception.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(exception.getMessage());
			alert.show();
        }	
	}

}
