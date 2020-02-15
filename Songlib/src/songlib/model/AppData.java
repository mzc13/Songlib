/**
 * 
 */
package songlib.model;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public abstract class AppData {
	private AppData() {};
	public abstract void readFromFile();
	public abstract void writeToFile();
}
