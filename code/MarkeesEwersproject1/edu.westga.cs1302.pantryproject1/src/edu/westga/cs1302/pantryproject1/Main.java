package edu.westga.cs1302.pantryproject1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private static final String WINDOW_TITLE = "Markees Ewers Pantry Projext";
	private static final String GUI_RESOURCE = "view/MainWindow.fxml";

	/**
	 * JavaFX entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(Main.GUI_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	public static void main(String[] args) {
		Main.launch(args);
	}

}