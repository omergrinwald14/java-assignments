
public class Playlist {

	private String name;
	private List<Song> pl;
	private double duration;
	private static int numOfPlaylists;

	//constructors	
	public Playlist() {
		name = "Playlist" + (numOfPlaylists + 1);
		numOfPlaylists++;
		pl = new List<Song>();
	}
	public Playlist(String name) {
		this.name = name;
		numOfPlaylists++;
		pl = new List<Song>();
	}

	//getters	
	public String getName() {
		return name;
	}

	public List<Song> getSongsList() {
		return pl;
	}

	public double getDuration() {
		if(numOfPlaylists == 0)
			return 0;
		return duration;
	}

	public Node<Song> getSongAt(int index) {
		int count = 0;
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			if(count == index) 
				return p;		
			count++;
		}
		Song s = new Song("Song Not Found",0,"Unknown");
		Node<Song> ans = new Node<Song>(s);
		return ans;
	}

	public Node<Song> addSong(Song s) {
		Node<Song> q = pl.getFirst();
		if(q == null) {
			q =	pl.insert(q,s);
			return q;
		}
		while (q.getNext() != null) 
			q=q.getNext();
		q =	pl.insert(q,s);
		duration = duration + s.getDuration();
		return q;
	}

	public boolean removeSong(int index) { 
		int count = 0;
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			if(count == index) {
				p = pl.remove(p);
				return true;
			}
			count++;
		}
		System.out.println("The song cannot be removed");
		return false;
	}

	public boolean setSongAt(int index, Song s) {
		int count = 0;
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			if(count == index) {
				Node<Song> prev = pl.getFirst();
				while(prev.getNext()!=p)
					prev = prev.getNext();
				pl.remove(p);
				pl.insert(prev,s);
				return true;
			}
			count++;
		}
		System.out.println("The song cannot be removed");
		return false;
	}

	public Playlist sortByName() {
		Playlist sortedPlaylist = new Playlist();
		return sortedPlaylist;
	}

	public Playlist sortByDuration() {
		Playlist sortedPlaylist = new Playlist();
		Node<Song> q = pl.getFirst();
		Node<Song> min = pl.getFirst();
		Node<Song> insertIndex = sortedPlaylist.getSongsList().getFirst();
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			q=p;
			min=p;
			while (q.getNext()!=null) {
				if(min.getData().getDuration()>q.getData().getDuration()) {
					min = q;
				}
				q = q.getNext();
			}
			insertIndex = sortedPlaylist.getSongsList().insert(insertIndex,min.getData());
		}

		return sortedPlaylist;
	}

	public String toString() {
		String s = this.name+" songs:\n";
		for (Node <Song> p = pl.getFirst(); p != null; p=p.getNext()) {
			s += p.toString()+"\n";
		}
		s += this.name+" duration: "+String.format("%.2f",
				this.getDuration())+"\n";
		return s;
	}


}
