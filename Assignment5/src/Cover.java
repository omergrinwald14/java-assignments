
public class Cover {

	private int[][] coverImage;
	private int height;
	private int width;

	// constructors
	public Cover (int[][] image) {
		this.height = image.length;
		this.width = image[0].length;
		this.coverImage = new int[height][width];

		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				coverImage[i][j] = image[i][j]; // deep copy
	}
	public Cover(Cover c) {
		coverImage = new int[c.height][c.width];
		for(int i=0; i<c.height; i++)
			for(int j=0; j<c.width; j++)
				coverImage[i][j] = c.coverImage[i][j]; // deep copy
		height = c.height; 
		width = c.width;
	}

	//getters
	public int[][] getCoverImage() {
		return coverImage;
	}

	// voids
	public void applyFilter() { // switch even to '0' and odd to '1'
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				if(coverImage[i][j]%2 == 0) // even
					coverImage[i][j] = 0;
				else  // odd
					coverImage[i][j] = 1;
	}	
	public void flip() { // transpose image
		if(height == width) { // check if the image is square
			int[][] transpose = new int[height][width]; 
			for(int i=0; i<height; i++)
				for(int j=0; j<width; j++)
					transpose[i][j] = coverImage[j][i]; //transpose values
			coverImage = transpose; 
		}

		else
			System.out.println("This cover cannot be flipped");
	}
	public void crop(int xStart, int yStart, int height, int width) { // crop image
		if (height>0 && width>0 && xStart+height-1 <= this.height &&  yStart+width-1 <= this.width) { // validating sizes before cropping
			int[][] croppedImg = new int[height][width]; 
			for(int i=xStart-1, a=0; a<height; i++, a++)
				for(int j=yStart-1, b=0; b<width; j++, b++)
					croppedImg[a][b] = coverImage[i][j]; // copy from original image to the right place in the cropped image
			coverImage = croppedImg;
			this.height = height; // updating height and width
			this.width = width;
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
