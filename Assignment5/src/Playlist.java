
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
	public Playlist copy(Playlist pl1){
		Playlist ans = new Playlist();
		if (pl1.getSongsList() == null){
			return null;
		}
		Node<Song> pos = null; 
		for(Node<Song> p = pl1.getSongsList().getFirst(); p != null; p = p.getNext()){
			pos = ans.getSongsList().insert(pos, p.getData());
		}

		return ans;
	}
	
	public Playlist merge(Playlist pl1){

		if (pl1 == null || this.pl.isEmpty()){
			return copy(pl1);
		}
		if (this.pl == null || pl1.getSongsList().isEmpty()){
			return copy(this);
		}
		Playlist pl3 = copy(pl1);
		Node<Song> p = pl3.getSongsList().getFirst();
		while (p.getNext() != null){
			p = p.getNext();
		}
		p.setNext(copy(pl1).getSongsList().getFirst());
		return pl3;

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
			duration = duration + s.getDuration();
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
		Node<Song> q = pl.getFirst();
		Node<Song> min = pl.getFirst();
		Node<Song> insertIndex = sortedPlaylist.getSongsList().getFirst();
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			q=p;
			min=p;
			while (q.getNext()!=null) {
				if(min.getData().getName().compareTo(q.getData().getName())>0) {
					min = q;
				}
				q = q.getNext();
			}
			insertIndex = sortedPlaylist.getSongsList().insert(insertIndex,min.getData());
		}

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
