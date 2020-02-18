/**
 * 
 */
package songlib.model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Abdulrahman Abdulrahman and Muhmmad Choudhary
 *
 */
public abstract class AppData {
	public static List<Song> songs = new LinkedList<Song>();
	public static File xmlSaveFile = 
			new File(System.getProperty("user.dir") + File.separator
					+ "songlib_data.xml");
	
	/**
	 * Set this variable to keep track of the selected song when switching
	 * scenes
	 */
	public static Song selectedSong = null;
	
	private AppData() {};
	
	public static void readFromFile() {
		if(!xmlSaveFile.exists())
			return;
		try {
			DocumentBuilderFactory factory = 
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlSaveFile);
			
			Element root = doc.getDocumentElement();
			NodeList nodes = root.getChildNodes();
			
			for(int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Song s = new Song(
						e.getAttribute("name"),
						e.getAttribute("artist"),
						e.getAttribute("album"),
						e.getAttribute("year"));
				songs.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	};
	public static void writeToFile() {
		try {
			DocumentBuilderFactory factory = 
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element root = doc.createElement("data");
			doc.appendChild(root);
			
			for(Song s : songs) {
				Element songNode = doc.createElement("song");
				songNode.setAttribute("name", s.name);
				songNode.setAttribute("artist", s.artist);
				songNode.setAttribute("album", s.album);
				songNode.setAttribute("year", s.year);
				root.appendChild(songNode);
			}
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer trans = tFactory.newTransformer();
			if(!xmlSaveFile.exists())
				xmlSaveFile.createNewFile();
			trans.transform(new DOMSource(doc), new StreamResult(xmlSaveFile));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
