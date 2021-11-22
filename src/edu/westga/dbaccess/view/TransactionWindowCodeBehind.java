package edu.westga.dbaccess.view;

import java.io.IOException;
import java.util.List;

import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Employee;
import edu.westga.dbaccess.model.RentalItem;
import edu.westga.dbaccess.model.RentalTransaction;
import edu.westga.dbaccess.utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TransactionWindowCodeBehind {

    @FXML
    private TextArea transactionSummaryTextArea;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Button backToCartButton;
    
    private Employee employee;

    @FXML
    void handleBackToCartButtonClick(ActionEvent event) {

    }

    @FXML
    void handleBackToHomeButtonClick(ActionEvent event) {
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

			root = loader.load();


			Stage stage = new Stage();

			stage.setTitle("Registration Window");

			stage.setScene(new Scene(root, 452, 440));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    public void setEmployee(Employee employee) {
    	if (employee == null) {
    		throw new IllegalArgumentException(UI.ErrorMessages.EMPLOYEE_NULL);
    	}
    	this.employee = employee;
    }
    
    public void setTransactionText(RentalTransaction transaction, List<RentalItem> items) {
    	String transactionSummary = transaction.toString() + System.lineSeparator();
    	for (RentalItem item : items) {
    		transactionSummary += item.toString() + System.lineSeparator();
    	}
    	this.transactionSummaryTextArea.setText(transactionSummary);
    }

}

