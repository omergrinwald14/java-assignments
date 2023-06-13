
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
		
		

	}

}
