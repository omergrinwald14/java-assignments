
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
		this.duration = 0;
		for(Node<Song> p=pl.getFirst(); p!=null; p=p.getNext()) {
			duration = duration + p.getData().getDuration(); // calculate current duration of the playlist
		}
		return duration;
	}

	public void merge(Playlist pl1) { // merge two playlists
		if(pl1.getSongsList().isEmpty() && this.pl.isEmpty()) // both empty
			return;
		if(pl1.getSongsList().isEmpty() && !(this.pl.isEmpty())) // second empty and first is'nt
			return;
		if(!(pl1.getSongsList().isEmpty()) && this.pl.isEmpty()) { // second is'nt empty and first is
			Node<Song> p = pl1.getSongsList().getFirst();
			Node<Song> q = null;
			while(p!=null) {
				q=pl.insert(q,p.getData()); 
				p=p.getNext();
			}
			return;
		}
		Node<Song> p=pl.getFirst(); 
		while(p.getNext()!=null)
			p=p.getNext(); // moving p forward to last song of pl
		p.setNext(pl1.getSongsList().getFirst()); // connect the last song of pl with the first song of pl1
	}


	public Node<Song> getSongAt(int index) {
		int count = 0;
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) { // going through all list until finding the wanted index
			if(count == index) 
				return p; // return the node in the right index
			count++;
		}
		Song s = new Song("Song Not Found",0,"Unknown");
		Node<Song> ans = new Node<Song>(s);
		return ans; // return a default node indicating that the song was not found
	}

	public Node<Song> addSong(Song s) {
		Node<Song> q = pl.getFirst();
		if(q == null) {
			q =	pl.insert(q,s); // insert the song as the first
			return q;
		}
		while (q.getNext() != null) 
			q=q.getNext(); // move forward until the end of the list
		q =	pl.insert(q,s); // insert the song at the end of the playlist
		return q;
	}

	public boolean removeSong(int index) { 
		int count = 1;
		for(Node<Song> p = pl.getFirst(); p!= null; p = p.getNext()) {
			if(count == index) { // finding the specific index
				p = pl.remove(p); // remove song from this index
				return true;
			}
			count++;
		}
		System.out.println("The song cannot be removed");
		return false;
	}

	public boolean setSongAt(int index, Song s) {
		if(index == 1) {
			pl.remove(pl.getFirst() );
			pl.insert(null, s); // replace the first song with the given song
			return true;
		}
		int count = 1;
		for(Node<Song> p=pl.getFirst(); p!=null; p=p.getNext()) {
			if(count == index) { // finding the specific index
				Node<Song> prev = pl.getFirst();	
				while(prev.getNext()!=p)
					prev = prev.getNext(); // move forward to one before the specific index
				pl.remove(p); 
				pl.insert(prev,s); // replace with the given song
				return true;
			}
			count++;
		}
		System.out.println("The song cannot be set at this index"); // no such index was found
		return false;
	}

	public Playlist sortByName() {
		List<Song> sorted = new List<Song>();	
		Node<Song> insertIndex = null;
		Node<Song> q = pl.getFirst();
		Node<Song> min = pl.getFirst();
		while(pl.getFirst()!=null) {
			q=pl.getFirst();
			min=pl.getFirst(); // assign the pointers back to the start
			while (q!=null) {
				if(min.getData().getName().compareTo(q.getData().getName())>0) 
					min = q; // if found a "smaller" name, update minimum to it
				q = q.getNext();
			}
			insertIndex = sorted.insert(insertIndex,min.getData()); // insert the song into the sorted list
			pl.remove(min); // remove the minimum from original list
		}
		pl = sorted; // switch original with sorted
		return this;	
	}


	public Playlist sortByDuration() {
		List<Song> sorted = new List<Song>();
		Node<Song> insertIndex = null;
		Node<Song> q = pl.getFirst();
		Node<Song> min = pl.getFirst();
		while(pl.getFirst()!=null) {
			q=pl.getFirst();
			min=pl.getFirst();
			while (q!=null) {
				if(min.getData().getDuration()>q.getData().getDuration()) {
					min = q; // find the song with the minimum duration
				}
				q = q.getNext();
			}
			insertIndex = sorted.insert(insertIndex,min.getData()); // insert the song into the sorted list
			pl.remove(min); // remove the minimum from original list

		}
		pl = sorted; // switch original with sorted
		return this;
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
