package edu.westga.dbaccess.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LandingWindowCodeBehind {

    @FXML
    private Label employeeNameLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label idLbl;

    @FXML
    private Button registerBtn;


    @FXML
    void registerBtnClick(ActionEvent event) {
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\RegisterWindow.fxml"));

			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle("Registration Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    @FXML
    void handleSearchClick(ActionEvent event) {
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\FurnitureSearchWindow.fxml"));

			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle("Search Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }

	 public void setTitle(String name, String username, int id) {
    	this.employeeNameLbl.setText(name);
    	this.usernameLbl.setText(username);   	
    	this.idLbl.setText(String.valueOf(id));
    }

}
