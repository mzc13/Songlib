/**
 * 
 */
package songlib.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
			new SortedList<Song>(FXCollections.observableList(AppData.songs));
	
	@FXML
	public void initialize() {
		songView.setItems(songList);
		if(songList.isEmpty()) {
			editButton.setDisable(true);
			deleteButton.setDisable(true);
		}
	}
	
	public void addSong() {
		
	}
}
