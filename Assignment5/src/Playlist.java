
public class Playlist {

	private String name;
	//private List<Song> pl;
	private double duration;
	private static int numOfPlaylists;
	
	//constructors	
	public Playlist() {
		name = "Playlist" + numOfPlaylists;
		numOfPlaylists++;
	}
	
	//getters	
	public String getName() {
		return name;
	}
	
	
}
