
public class Assignment5 {

	public static void main(String[] args) {

		//creating songs
		Song s1 = new Song("a", 2.5, "pop");	
		Song s2 = new Song("b", 3, "pop");
		Song s3 = new Song("c", 1.5, "rock");
		Song s4 = new Song("d", 1.7, "pop");	
		Song s5 = new Song("e", 2.2, "pop");	
		Song s6 = new Song("f", 3.4, "rock");	
		Song s7 = new Song("g", 2, "pop");	
		Song s8 = new Song("h", 1.8, "rock");	

		
		//creating a playlist and adding songs
		System.out.println("creating a playlist and adding songs:");
		Playlist pl1 = new Playlist();
		pl1.addSong(s1);
		pl1.addSong(s4);
		pl1.addSong(s2);
		pl1.addSong(s3);
		System.out.println(pl1);	
		
		
		//creating empty playlists
		System.out.println("creating empty playlists:");
		Playlist pl2 = new Playlist();
		System.out.println(pl2);
		
		Playlist pl3 = new Playlist();
		System.out.println(pl3);
		
		//merging a full playlist with an empty playlist
		System.out.println("merging a full playlist with an empty playlist:");
		pl1.merge(pl2);
		System.out.println(pl1);
		System.out.println(pl2);
		
		
		System.out.println("merging an empty playlist with a full playlis:");
		pl3.merge(pl1);
		System.out.println(pl1);
		System.out.println(pl3);
		
		
		//creating a new playlist and adding songs
		System.out.println("creating a new playlist and adding songs:");
		Playlist pl4 = new Playlist();
		pl4.addSong(s8);
		pl4.addSong(s6);
		pl4.addSong(s5);
		pl4.addSong(s7);
		System.out.println(pl4);
		
		
		System.out.println("merging two full playlists:");
		pl4.merge(pl1);
		System.out.println(pl1);
		System.out.println(pl4);

		
		//setting songs at indices
		System.out.println("setting songs at indices:");
		pl1.setSongAt(2, s8);
		System.out.println(pl1);
		
		pl1.setSongAt(5, s2);
		System.out.println(pl1);
		
		
		//sorting the merged playlist by name
		System.out.println("sorting the merged playlist:");
		System.out.println(pl4);
		
		pl4.sortByName();
		System.out.println(pl4);
		
		pl4.sortByDuration();
		System.out.println(pl4);


	}

}
