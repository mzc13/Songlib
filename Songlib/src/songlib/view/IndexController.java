/**
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import songlib.model.AppData;
import songlib.model.Song;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public class IndexController {

	@FXML ListView<Song> songView;
	@FXML Label details;
	@FXML Button addButton;
	@FXML Button editButton;
	@FXML Button deleteButton;
	
	ObservableList<Song> songList = 
			FXCollections.observableList(AppData.songs);
	
	@FXML
	public void initialize() {
		Collections.sort(songList);
		songView.setItems(songList);
		songView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if(songList.isEmpty()) {
			editButton.setDisable(true);
			deleteButton.setDisable(true);
			setSongDetails();
		}else if(AppData.selectedSong == null) {
			select(0);
		}else {
			select(AppData.selectedSong);
		}
	}
	
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
	
	public void editSong() {
		AppData.songs.remove(AppData.selectedSong);
		Stage window = (Stage) addButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/edit.fxml"));
		try {
			window.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSong() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(deleteButton.getScene().getWindow());
		alert.setHeaderText("Are you sure you want to delete this song?");
		alert.showAndWait();
		if(alert.getResult() == ButtonType.OK) {
			int index = songList.indexOf(AppData.selectedSong);
			Song newSelection = null;
			try {
				newSelection = songList.get(index + 1);
			}catch(IndexOutOfBoundsException e) {
				try {
					newSelection = songList.get(index - 1);
				}catch(IndexOutOfBoundsException e2) {}
			}
			songList.remove(index);
			AppData.selectedSong = newSelection;
			initialize();
		}
		return;
	}
	
	public void select(Song s) {
		select(songList.indexOf(s));
	}
	public void select(int index) {
		noScrollSelect(index);
		songView.scrollTo(index);
	}
	public void noScrollSelect(int index) {
		songView.getSelectionModel().select(index);
		AppData.selectedSong = songView.getSelectionModel().getSelectedItem();
		setSongDetails();
	}
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
	public void listMouseListener() {
		int index = songView.getSelectionModel().getSelectedIndex();
		if(index >= 0) {
			noScrollSelect(songView.getSelectionModel().getSelectedIndex());
		}else {
			return;
		}
	}
}
