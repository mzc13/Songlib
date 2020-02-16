/**
 * 
 */
package songlib.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public abstract class AppData {
	public static List<Song> songs = new LinkedList<Song>();
	
	/**
	 * Set this variable to keep track of the selected song when switching
	 * scenes
	 */
	public static Song selectedSong = null;
	private AppData() {};
	public abstract void readFromFile();
	public abstract void writeToFile();
}
