
public class QueenElephant extends Elephant {

	public QueenElephant(String name, int type) {
		super(name, type);
	}
	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		//down
		//regular move
		if (i_origin == i_dest+1)
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
		//move 2 blocks
		if(i_origin == i_dest+2) {
			if (j_origin == j_dest+2) // left
				if(check_mamals[i_dest-1][j_dest-1].equals("*") && check_mamals[i_dest-2][j_dest-2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			if (j_origin == j_dest-2) // right
				if(check_mamals[i_dest-1][j_dest+1].equals("*") && check_mamals[i_dest-2][j_dest+2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		}
		//up
		//regular move
		if(i_origin == i_dest-1) {
			if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
				if(check_mamals[i_dest][j_dest].equals("*")){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			return false;
		}
		//move 2 blocks
		if(i_origin == i_dest-2) {
			if (j_origin == j_dest+2) // left
				if(check_mamals[i_dest+1][j_dest-1].equals("*") && check_mamals[i_dest+2][j_dest-2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			if (j_origin == j_dest-2) // right
				if(check_mamals[i_dest+1][j_dest+1].equals("*") && check_mamals[i_dest+2][j_dest+2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		}
		return false;
	}
	public boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin == i_dest + 2) { //move up and right
			if (j_origin == j_dest - 2)
				if(check_mamals[i_origin - 1][j_origin + 1].type==2)
					if(check_mamals[i_origin - 2][j_origin + 2].equals("*")){ //check that is empty
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
						check_mamals[i_origin][j_origin]= new Mamals("*",0);
						check_mamals[i_dest-1][j_dest+1]=new Mamals("*",0);
					}
			return true;
		}
		if (j_origin == j_dest + 2)//move up and left
			if(check_mamals[i_origin - 1][j_origin - 1].type==2)
				if(check_mamals[i_origin - 2][j_origin - 2].equals("*")){//check that is empty
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					check_mamals[i_dest-1][j_dest-1]=new Mamals("*",0);

					return true;
				}
		if (j_origin == j_dest - 2)//move down and left
			if(check_mamals[i_origin + 1][j_origin - 1].type==2)
				if(check_mamals[i_origin + 2][j_origin - 2].equals("*")){//check that is empty
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					check_mamals[i_dest+1][j_dest+1]=new Mamals("*",0);

					return true;
				}
		if (i_origin == i_dest - 2) { //move down and right
			if (j_origin == j_dest - 2)
				if(check_mamals[i_origin + 1][j_origin + 1].type==2)
					if(check_mamals[i_origin + 2][j_origin + 2].equals("*")){ //check that is empty
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
						check_mamals[i_origin][j_origin]= new Mamals("*",0);
						check_mamals[i_dest+1][j_dest+1]=new Mamals("*",0);
					}
			return true;
		}

		return false;
	}
	//forward computer
	//first food computer

}
