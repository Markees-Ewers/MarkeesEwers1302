package edu.westga.cs1302.pantryproject1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class Main.
 * @author me00070
 * @version fall 2024
 */
public class Main extends Application {
	
	/** The Constant WINDOW_TITLE. */
	private static final String WINDOW_TITLE = "Markees Ewers Pantry Project";
	
	/** The Constant GUI_RESOURCE. */
	private static final String GUI_RESOURCE = "view/MainWindow.fxml";

	/**
	 * JavaFX entry point.
	 *
	 * @param primaryStage the primary stage
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @precondition none
	 * @postcondition none
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(Main.GUI_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}

}