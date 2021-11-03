package edu.westga.dbaccess.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import edu.westga.dbaccess.dal.CategoryDAL;
import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.StyleDAL;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.Style;
import edu.westga.dbaccess.model.Category;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class FurnitureSearchCodeBehind {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> styleFilterComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ListView<Furniture> furnitureListView;

    private FurnitureDAL furnitureDal;
    
    private CategoryDAL categoryDal;
    
    private StyleDAL styleDal;

    private List<Style> styles;
    
    private List<Category> categories;

    public FurnitureSearchCodeBehind () {
        this.furnitureDal = new FurnitureDAL();
        this.categoryDal = new CategoryDAL();
        this.styleDal = new StyleDAL();
        this.styles = new ArrayList<Style>();
        this.categories = new ArrayList<Category>();
    }

    @FXML
    void initialize() throws SQLException {
    	this.furnitureListView.getItems().setAll(this.furnitureDal.getAllFurniture());
    	this.styleFilterComboBox.getItems().setAll(this.styleDal.getStyles().values());
    	this.categoryComboBox.getItems().setAll(this.categoryDal.getCategory().values());
    }

    @FXML
    void handleSearchButtonClick(ActionEvent event) throws SQLException {
    	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByCriteria(this.getStyleId(this.styleFilterComboBox.getSelectionModel().getSelectedItem()), this.getCategoryId(this.categoryComboBox.getSelectionModel().getSelectedItem())));
    }
    
    private int getStyleId(String styleLabel) {
    	for (Style style : this.styles) {
    		if (style.getLabel() == styleLabel) {
    			return style.getStyleId();
    		}
    	}
    	return 0;
    }
    
    private int getCategoryId(String categoryLabel) {
    	for (Category category : this.categories) {
    		if (category.getLabel() == categoryLabel) {
    			return category.getCategoryId();
    		}
    	}
    	return 0;
    }
        

}
