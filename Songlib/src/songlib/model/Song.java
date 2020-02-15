/**
 * 
 */
package songlib.model;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public class Song implements Comparable<Song> {
	String name;
	String artist;
	String album;
	String year;

	/**
	 * @param name - Required
	 * @param artist - Required
	 * @param album - Optional. If you send in null, it sets album to ""
	 * @param year - Optional. Expected to always be an integer. If you send in
	 * null, it sets year to ""
	 */
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = (album != null) ? album : "";
		this.year = (year != null) ? year : "";
	}
	
	@Override
	public int compareTo(Song s) {
		if(this.name.compareToIgnoreCase(s.name) != 0) {
			return this.name.compareToIgnoreCase(s.name);
		}else {
			return this.artist.compareToIgnoreCase(s.artist);
		}
	}

}
