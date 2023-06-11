
public class Cover {
	
	private int[][] coverImage;
	private int height;
	private int width;
	
	public Cover (int[][] image) {
		this.height = image.length;
		this.width = image[0].length;
		this.coverImage = new int[height][width];

		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				coverImage[i][j] = image[i][j];
		
	}
	public Cover(Cover c) {
		this.coverImage = new int[c.height][c.width];
		for(int i=0; i<c.height; i++)
			for(int j=0; j<c.width; j++)
				this.coverImage[i][j] = c.coverImage[i][j];
	}
	
	//getters
	public int[][] getCoverImage() {
		return coverImage;
	}
	
	public void applyFilter() {
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				if(coverImage[i][j]%2 == 0)
					coverImage[i][j] = 0;
				else
					coverImage[i][j] = 1;
	}
	
	public void flip() {
		if(height == width) {
			int[][] transpose = new int[height][width];
			for(int i=0; i<height; i++)
				for(int j=0; j<width; j++)
					transpose[i][j] = coverImage[j][i];
			coverImage = transpose;
		}
		
		else
			System.out.println("This cover cannot be flipped");
	}
	
	public void crop(int xStart, int yStart, int height, int width) {
		if (height>0 && width>0 && xStart+height-1 <= this.height+1 &&  yStart+width-1 <= this.width) {
			int[][] croppedImg = new int[height][width];
			for(int i=xStart; i<height; i++)
				for(int j=yStart; j<width; j++)
					croppedImg[i][j] = coverImage[i][j];
			coverImage = croppedImg;
		}
		else
			System.out.println("This cropping is not possible");
	}
	
	public String toString() {
		String str = "Cover:\n";
		for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
		str += this.coverImage[i][j]+" ";
		}
		str += "\n";
		}
		return str;
		}
	
}
