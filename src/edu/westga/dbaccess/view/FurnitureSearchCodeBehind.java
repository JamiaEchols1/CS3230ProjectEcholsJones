package edu.westga.dbaccess.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.model.Furniture;

import java.util.List;
import java.util.HashSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private ListView<Furniture> furnitureListView;

    private FurnitureDAL furnitureDal;

    private List<Integer> styleIds;

    private List<Integer> categoryIds;

    public FurnitureSearchCodeBehind () {
        this.furnitureDal = new FurnitureDAL();
        this.styleIds = new ArrayList<Integer>();
        this.categoryIds = new ArrayList<Integer>();
    }

    @FXML
    void initialize() {

    }

    @FXML
    void handleSearchButtonClick(ActionEvent event) throws SQLException {
        this.styleIds = (List<Integer>) this.styleFilterComboBox.getSelectionModel().getSelectedItem();
        this.categoryIds = (List<Integer>) this.categoryComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Furniture> furniture = (ObservableList<Furniture>) new ArrayList<Furniture> (this.furnitureDal.getFurnitureByCriteria(this.styleIds, this.categoryIds));
        this.furnitureListView.setItems(furniture);
    }

}
