package songlib.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import songlib.model.AppData;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public class Songlib extends Application {

	@Override
	public void start(Stage window) throws IOException {
		AppData.readFromFile();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/index.fxml"));
		GridPane root = (GridPane)loader.load();
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setTitle("Songlib");
		window.setResizable(false);  
		window.show();
		window.setOnCloseRequest(e -> AppData.writeToFile());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
