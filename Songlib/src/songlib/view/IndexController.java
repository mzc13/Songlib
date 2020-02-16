/**
 * 
 */
package songlib.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
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
			FXCollections.observableList(AppData.songs).sorted();
	
	@FXML
	public void initialize() {
		songView.setItems(songList);
		songView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if(songList.isEmpty()) {
			editButton.setDisable(true);
			deleteButton.setDisable(true);
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
	
	public void select(Song s) {
		songView.getSelectionModel().select(s);
		AppData.selectedSong = s;
	}
	public void select(int index) {
		songView.getSelectionModel().select(index);
		AppData.selectedSong = songView.getSelectionModel().getSelectedItem();
	}
}
