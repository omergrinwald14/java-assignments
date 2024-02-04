
public class Elephant extends Mamals {

	public Elephant(String name,int type) {
		super(name,type);
	}

	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		if (i_origin == i_dest+1)
			if (j_origin == j_dest+1 || j_origin == j_dest-1)
				if(check_mamals[i_dest][j_dest].equals("*")){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		if(i_origin == i_dest+2) {
			if (j_origin == j_dest+2) // left
				if(check_mamals[i_dest-1][j_dest-1].equals("*") && check_mamals[i_dest-2][j_dest-2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			if (j_origin == j_dest-2) // right
				if(check_mamals[i_dest-1][j_dest-1].equals("*") && check_mamals[i_dest-2][j_dest-2].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		}	

		return false;
	}
	public boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		int steps =(int) (1+Math.random());
		if(steps==1) {
			if ((is_valid_coordinate(i_origin+1,j_origin+1)&&check_mamals[i_origin+1][j_origin+1].equals("*"))||(is_valid_coordinate(i_origin+1, j_origin-1)&&check_mamals[i_origin+1][j_origin-1].equals("*")))
			{
				double direction=1+(int)Math.random()*10;
				if(direction <=5 && is_valid_coordinate(i_origin+1, j_origin+1))//move right
				{
					check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				else if(is_valid_coordinate(i_origin+1, j_origin-1))//move left 
				{
					check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			if (is_valid_coordinate(i_origin+1, j_origin+1) && check_mamals[i_origin+1][j_origin+1].equals("*"))//only right
			{
				check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			if (is_valid_coordinate(i_origin+1, j_origin-1) && check_mamals[i_origin+1][j_origin-1].equals("*"))//only left
			{
				check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}

			steps=2;
		}
		if(steps==2) {
			if ((is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+2][j_origin-2].equals("*"))||is_valid_coordinate(i_origin+2, j_origin+2) && (check_mamals[i_origin+2][j_origin+2].equals("*"))) {
				double direction=1+(int)Math.random()*10;
				if(direction <=5 && is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin+1][j_origin+1].equals("*")){ //move right
					check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				else if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+1][j_origin-1].equals("*")){ //move left 		
					check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			if (is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin+2][j_origin+2].equals("*") && check_mamals[i_origin+1][j_origin+1].equals("*")) { //only right
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			if (is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+2][j_origin-2].equals("*") && check_mamals[i_origin+1][j_origin-1].equals("*")) { //only left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
		}
		return false;
	}

}
