/**
 * 
 */
package songlib.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import songlib.model.AppData;
import songlib.model.Song;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public class AddController {

    @FXML Button cancelButton;
    @FXML Button submitButton;
    @FXML TextField nameField;
    @FXML TextField artistField;
    @FXML TextField albumField;
    @FXML TextField yearField;
    
    @FXML
    public void initialize() {
    	Platform.runLater(() -> nameField.requestFocus());
    }

    public void cancel() {
    	Stage window = (Stage) cancelButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/index.fxml"));
		try {
			window.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void submit() {
    	String name = nameField.getText().strip();
    	String artist = artistField.getText().strip();
    	String album = albumField.getText().strip();
    	String year = yearField.getText().strip();
    	
    	if(name.equals("") || artist.equals("")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(submitButton.getScene().getWindow());
    		alert.setHeaderText("Song name and artist are required!");
    		alert.showAndWait();
    		return;
    	}
    	
    	if(!year.equals("")) {
    		try {
        		int y = Integer.parseInt(year);
        		if(y <= 0) {
        			Alert alert = new Alert(AlertType.WARNING);
            		alert.initOwner(submitButton.getScene().getWindow());
            		alert.setHeaderText("Year must be a valid number!");
            		alert.showAndWait();
            		return;
        		}
        	} catch(NumberFormatException e) {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.initOwner(submitButton.getScene().getWindow());
        		alert.setHeaderText("Year must be a valid number!");
        		alert.showAndWait();
        		return;
        	}
    	}
    	
    	Song s = new Song(name, artist, album, year);
    	if(AppData.songs.contains(s)) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(submitButton.getScene().getWindow());
    		alert.setHeaderText("Cannot add duplicate song!");
    		alert.showAndWait();
    		return;
    	}
    	
    	AppData.songs.add(s);
    	AppData.selectedSong = s;
    	
    	Stage window = (Stage) submitButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/index.fxml"));
		try {
			window.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
