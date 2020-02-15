package songlib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public class Songlib extends Application {

	@Override
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/index.fxml"));
		GridPane root = (GridPane)loader.load();
		Scene scene = new Scene(root);
		mainStage.setScene(scene);
		mainStage.setTitle("Songlib");
		mainStage.setResizable(false);  
		mainStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
