package edu.westga.dbaccess.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FurnitureSearchCodeBehind {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> styleFilterComboBox;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private ListView<?> furnitureListView;

    @FXML
    void handleSearchButtonClick(ActionEvent event) {

    }

}
