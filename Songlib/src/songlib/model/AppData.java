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
	private AppData() {};
	public abstract void readFromFile();
	public abstract void writeToFile();
}
