
public class Song {
	private String name;
	private double duration;
	private String genre;
	private Cover cover;
	private static int numOfSongs=0;

	// constructors
	public Song() {
		this.duration = 0;
		this.name = "Song" + numOfSongs;
		this.genre = "Unknown";
		numOfSongs++;
	}
	public Song(String name, double duration, String genre, Cover cover) {
		this.duration = duration;
		this.name = name;
		this.genre = genre;
		this.cover = cover;
		numOfSongs++;
	}	
	public Song(String name, double duration, String genre) {
		this.duration = duration;
		this.name = name;
		this.genre = genre;
		numOfSongs++;
	}
	
	//getters	
	public String getName() {
		return name;
	}	
	public double getDuration() {
		return duration;
	}	
	public String getGenre() {
		return genre;
	}	
	public Cover getCover() {
		return cover;
	}

	//setters
	public void setCover(Cover cover) {
		this.cover = cover;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String toString() {
		return this.name+", Duration: "+this.duration+", Genre:"+this.genre;
		}
	
	
}
