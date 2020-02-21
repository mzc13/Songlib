/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
package songlib.view;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import songlib.model.AppData;
import songlib.model.Song;


public class IndexController {

	@FXML ListView<Song> songView;
	@FXML Label details;
	@FXML Button addButton;
	@FXML Button editButton;
	@FXML Button deleteButton;
	@FXML Tooltip saveLocation =
			new Tooltip("Songs saved at "+ AppData.xmlSaveFile.getAbsolutePath());
	
	// Creating a JavaFX wrapper around the song data for use with ListView
	ObservableList<Song> songList = 
			FXCollections.observableList(AppData.songs);
	
	/** Method that runs automatically when the scene is shown. */
	@FXML
	public void initialize() {
		// Sort the list whenever the scene is started
		Collections.sort(songList);
		
		// Initialize the songs ListView
		songView.setItems(songList);
		songView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		songView.setTooltip(saveLocation);
		details.setTooltip(saveLocation);
		
		if(songList.isEmpty()) {
			// If the list is empty, disable appropriate buttons
			editButton.setDisable(true);
			deleteButton.setDisable(true);
			setSongDetails();
		}else {
			// If list is not empty, select the appropriate song
			select(AppData.selectedSong);
		}
	}
	
	/** If user clicks 'Add Song', go to the add scene. */
	public void addSong() {
		Stage window = (Stage) addButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/add.fxml"));
		try {
			window.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** If user clicks 'Edit Song', go to the edit scene. */
	public void editSong() {
		// Remove the selected song from the list temporarily to prevent false,
		// positives when checking the list for duplicates.
		AppData.songs.remove(AppData.selectedSong);
		
		// Go to the edit scene
		Stage window = (Stage) addButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/edit.fxml"));
		try {
			window.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** If user clicks 'Delete Song', show a confirmation dialog. */
	public void deleteSong() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(deleteButton.getScene().getWindow());
		alert.setHeaderText("Are you sure you want to delete this song?");
		alert.showAndWait();
		
		// If user click 'Ok', then delete the song and set the next selected
		if(alert.getResult() == ButtonType.OK) {
			int index = songList.indexOf(AppData.selectedSong);
			
			// Using try-catch blocks to get the appropriate next selected song
			Song newSelection = null;
			try {
				newSelection = songList.get(index + 1);
			}catch(IndexOutOfBoundsException e) {
				try {
					newSelection = songList.get(index - 1);
				}catch(IndexOutOfBoundsException e2) {}
			}
			
			// Remove the requested song
			songList.remove(index);
			
			// Set the next song as selected
			AppData.selectedSong = newSelection;
			
			// Re-call the initialize method to deal with the modified song list
			initialize();
		}
		return;
	}
	
	/**
	 * Select the requested song in the ListView.
	 * @param s - The song to select. If the song is null, as it would be on
	 * app startup, then try to select the first song in the list.
	 */
	public void select(Song s) {
		if(s == null)
			select(0);
		else
			select(songList.indexOf(s));
	}
	
	/**
	 * Select the song at the requested index in the ListView, and scroll to it.
	 * @param index
	 */
	public void select(int index) {
		noScrollSelect(index);
		songView.scrollTo(index);
	}
	
	/**
	 * Select the song at the requested index in the ListView without scrolling.
	 * @param index
	 */
	public void noScrollSelect(int index) {
		songView.getSelectionModel().select(index);
		AppData.selectedSong = songView.getSelectionModel().getSelectedItem();
		setSongDetails();
	}
	
	/** 
	 * Sets the selected song's details on the screen.
	 * If the list is empty, then clear the song details.
	 */
	public void setSongDetails() {
		if(AppData.selectedSong != null) {
			details.setText("Song Details:\n"
					+ "Name: " + AppData.selectedSong.name + "\n"
					+ "Artist: " + AppData.selectedSong.artist + "\n"
					+ "Album: " + AppData.selectedSong.album + "\n"
					+ "Year: " + AppData.selectedSong.year + "\n");
		}else {
			details.setText("Song Details:");
		}
	}
	
	/** Listens to mouse clicks in the list and selects the appropriate song. */
	public void listMouseListener() {
		int index = songView.getSelectionModel().getSelectedIndex();
		if(index >= 0) {
			noScrollSelect(index);
		}else {
			return;
		}
	}
}
