package edu.westga.dbaccess.view;

import edu.westga.dbaccess.model.Furniture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ReturnWindowCodeBehind {

    @FXML
    private ListView<Furniture> transactionListView;

    @FXML
    private ListView<Furniture> returnListView;

    @FXML
    private Button addToReturnButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label customerInformationLabel;

    @FXML
    private Label transactionNumberLabel;

    @FXML
    void handleAddToReturnClick(ActionEvent event) {

    }

    @FXML
    void handleRemoveClick(ActionEvent event) {

    }

    @FXML
    void handleSubmitButtonClick(ActionEvent event) {

    }

}
