
public class Mamals_Checkers {

	static Mamals[][] check_mamals = new Mamals[8][8];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void setNewBoard(Mamals[][] check_mamals) {
		for(int i=0;i<check_mamals.length;i++) {
			for(int j=0;j<check_mamals[0].length;j++) {
				if(i%2==0 && j%2 == 0)
					check_mamals[i][j] = new Mamals("*");
					
					
			}
		}
	}
}
