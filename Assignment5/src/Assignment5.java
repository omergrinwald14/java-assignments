
public class Assignment5 {

	public static void main(String[] args) {
		
		
		//creating covers
		int[][] image1 = {{1,2,3},{7,8,9},{0,2,4}};
		int[][] image2 = {{1,2,3,4,5,6},{7,8,9,0,1,2},{0,2,4,6,8,0}};
		Cover c1 = new Cover(image1);
		Cover c2 = new Cover(image2);
		Cover c3 = new Cover(c2);

		//creating songs
		Song s1 = new Song("a", 2.5, "pop", c1);	
		Song s2 = new Song("b", 0.1, "pop", c2);
		Song s3 = new Song("b", 0.1, "pop", c3);

		//print covers		
		System.out.println("print covers:");
		System.out.println(s1.getCover());
		System.out.println(s2.getCover());
		System.out.println(s3.getCover());

		
		//flip covers
		System.out.println("flip covers:");
		s1.getCover().flip();
		System.out.println(s1.getCover());
		
		s2.getCover().flip();
		System.out.println(s2.getCover());
		
		
		//crop covers
		System.out.println("crop covers:");
		s1.getCover().crop(2,2,2,2);
		System.out.println(s1.getCover());
		
		s2.getCover().crop(2,4,2,4);
		System.out.println(s2.getCover());
		
		s2.getCover().crop(2,4,-5,-5);
		System.out.println(s2.getCover());
		
		s2.getCover().crop(2,4,1,3);
		System.out.println(s2.getCover());
		
		
		//apply filter:
		System.out.println("apply filter:");
		s1.getCover().applyFilter();
		System.out.println(s1.getCover());
		
		s2.getCover().applyFilter();
		System.out.println(s2.getCover());
		
		System.out.println(s3.getCover());

		

	}

}
