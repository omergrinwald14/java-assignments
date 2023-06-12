
public class Playlist {

	private String name;
	private List<Song> pl;
	private double duration;
	private static int numOfPlaylists;
	
	//constructors	
	public Playlist() {
		name = "Playlist" + numOfPlaylists;
		numOfPlaylists++;
		pl = new List<Song>();
	}
	public Playlist(String name) {
		this.name = name;
		pl = new List<Song>();

	}
	
	//getters	
	public String getName() {
		return name;
	}
	
	
}
