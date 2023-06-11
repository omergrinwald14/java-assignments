
public class Assignment5 {
	public static void main(String[] args) {
		
		
		//creating covers
		int[][] image1 = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] image2 = {{4,5,6},{1,2,3}};
		int[][] image3 = {{0,1,0},{1,0,1},{0,1,0}};
		Cover c1 = new Cover(image1);
		Cover c2 = new Cover(image2);
		Cover c3 = new Cover(image3);
		
		
		//creating songs
		Song s1 = new Song("a", 2.5, "pop", c1);	
		Song s2 = new Song("b", 3, "pop", c2);
		Song s3 = new Song("c", 1.5, "rock", c3);
		Song s4 = new Song("d", 1.7, "pop", c1);	
		Song s5 = new Song("e", 2.2, "pop", c2);	
		Song s6 = new Song("f", 3.4, "rock", c3);	
		Song s7 = new Song("g", 2, "pop", c1);	
		Song s8 = new Song("h", 1.8, "rock", c2);	
		
		
		//covers methods
		System.out.println("s1 cover:");
		System.out.println(s1.getCover());
		
		s1.getCover().flip();
		System.out.println(s1.getCover());
		
		s1.getCover().applyFilter();
		System.out.println(s1.getCover());
		
		s1.getCover().crop(1,2,3,2);
		System.out.println(s1.getCover());
		
		System.out.println("s2 cover:");
		System.out.println(s2.getCover());
		
		s2.getCover().flip();
		System.out.println(s2.getCover());
		
		s2.getCover().applyFilter();
		System.out.println(s2.getCover());
		
		s2.getCover().crop(1,2,1,3);
		System.out.println(s2.getCover());
		
		
	}
}
