
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
			duration = duration + p.getData().getDuration();
		}
		return duration;
	}

	public void merge(Playlist pl1) {
		if(pl1.getSongsList().getFirst()==null && this.pl.getFirst()==null)
			return;
		if(pl1.getSongsList().getFirst()==null && this.pl.getFirst()!=null)
			return;
		if(pl1.getSongsList().getFirst()!=null && this.pl.getFirst()==null) {
			this.pl = pl1.getSongsList();
			return; 
		}
		Node<Song> p=pl.getFirst(); 
		while(p.getNext()!=null)
			p=p.getNext(); 
		p.setNext(pl1.getSongsList().getFirst());
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
		return q;
	}

	public boolean removeSong(int index) { 
		int count = 1;
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
		if(index == 1) {
			pl.remove(pl.getFirst() );
			pl.insert(null, s);
			return true;
		}
		int count = 1;
		for(Node<Song> p=pl.getFirst(); p!=null; p=p.getNext()) {
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
		System.out.println("The song cannot be set at this index");
		return false;
	}

	public Playlist sortByName() {
		List<Song> sorted = new List<Song>();	
		Node<Song> insertIndex = null;
		Node<Song> q = pl.getFirst();
		Node<Song> min = pl.getFirst();
		while(pl.getFirst()!=null) {
			q=pl.getFirst();
			min=pl.getFirst();
			while (q!=null) {
				if(min.getData().getName().compareTo(q.getData().getName())>0) 
					min = q;
				q = q.getNext();
			}
			insertIndex = sorted.insert(insertIndex,min.getData());
			pl.remove(min);
		}
		pl = sorted;
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
					min = q;
				}
				q = q.getNext();
			}
			insertIndex = sorted.insert(insertIndex,min.getData());
			pl.remove(min);

		}
		pl = sorted;
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
