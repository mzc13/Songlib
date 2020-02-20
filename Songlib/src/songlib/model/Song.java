/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
package songlib.model;


public class Song implements Comparable<Song> {
	public String name;
	public String artist;
	public String album;
	public String year;

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
	
	/**
	 * Using a custom compareTo method so song sorting will automatically be
	 * handled by the data structure holding the songs.
	 */
	@Override
	public int compareTo(Song s) {
		if(this.name.compareToIgnoreCase(s.name) != 0) {
			return this.name.compareToIgnoreCase(s.name);
		}else {
			return this.artist.compareToIgnoreCase(s.artist);
		}
	}
	
	/**
	 * Using a custom equals method so we can easily check if we're about to
	 * insert a duplicate into our song list.
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Song) {
			Song s = (Song) o;
			return (this.name.equalsIgnoreCase(s.name)) 
					&& (this.artist.equalsIgnoreCase(s.artist));
		}else {
			return false;
		}
	}
	
	/**
	 * Using a custom toString method so the ListView only shows song name and
	 * artist.
	 */
	@Override
	public String toString() {
		return name + ", " + artist;
	}

}
