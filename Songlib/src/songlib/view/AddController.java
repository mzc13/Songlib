/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
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


public class AddController {

    @FXML Button cancelButton;
    @FXML Button submitButton;
    @FXML TextField nameField;
    @FXML TextField artistField;
    @FXML TextField albumField;
    @FXML TextField yearField;
    
    /** Method that runs automatically when the scene is shown. */
    @FXML
    public void initialize() {
    	// When the scene is done loading, focus on the first text field
    	Platform.runLater(() -> nameField.requestFocus());
    }

    /** If user clicks cancel, go back to the start scene. */
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

    /** If user clicks submit, add the song after making sure it is valid. */
    public void submit() {
    	String name = nameField.getText().strip();
    	String artist = artistField.getText().strip();
    	String album = albumField.getText().strip();
    	String year = yearField.getText().strip();
    	
    	// Show error if name or artist field is empty
    	if(name.equals("") || artist.equals("")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(submitButton.getScene().getWindow());
    		alert.setHeaderText("Song name and artist are required!");
    		alert.showAndWait();
    		return;
    	}
    	
    	// Show error if year is not a proper number
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
    	
    	// Show error if song list already contains the current song
    	Song s = new Song(name, artist, album, year);
    	if(AppData.songs.contains(s)) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(submitButton.getScene().getWindow());
    		alert.setHeaderText("Cannot add duplicate song!");
    		alert.showAndWait();
    		return;
    	}
    	
    	// Add the song to the song list and set it as selected
    	AppData.songs.add(s);
    	AppData.selectedSong = s;
    	
    	// Go back to the start scene
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
