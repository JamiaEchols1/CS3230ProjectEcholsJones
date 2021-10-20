package edu.westga.dbaccess;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {

		launch(args);

	}

	private static void printEmployees(Customer customer) {
		System.out.println(customer.getName() + "\t\t" + customer.getMemberID() + "\t\t" + customer.getBirthday());
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
