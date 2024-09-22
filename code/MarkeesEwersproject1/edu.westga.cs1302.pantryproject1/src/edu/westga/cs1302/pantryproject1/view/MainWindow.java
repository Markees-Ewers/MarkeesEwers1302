package edu.westga.cs1302.pantryproject1.view;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class MainWindow {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFoodButton;

    @FXML
    private ListView<?> foodListView;

    @FXML
    private TextField foodNameTextField;

    @FXML
    private ComboBox<?> foodTypeComboBox;

    @FXML
    private AnchorPane pane;

    @FXML
    void addFood(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addFoodButton != null : "fx:id=\"AddFoodButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert foodListView != null : "fx:id=\"foodListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert foodNameTextField != null : "fx:id=\"foodNameTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert foodTypeComboBox != null : "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}
