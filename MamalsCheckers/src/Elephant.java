
public class Elephant extends Mamals {

	public Elephant(String name,int type) {
		super(name,type);
	}

	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		// 1 step
		if (i_origin == i_dest+1) {
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		//2 steps		
		if(i_origin-2 == i_dest) {
			if (j_origin-2 == j_dest) // left
				if(check_mamals[i_origin-1][j_origin-1].equals("*")){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			if (j_origin+2 == j_dest) // right
				if(check_mamals[i_origin-1][j_origin+1].equals("*")) {
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
			if ((is_valid_coordinate(i_origin+1,j_origin+1, check_mamals))||(is_valid_coordinate(i_origin+1, j_origin-1, check_mamals)))
			{
				double direction=1+(int)Math.random()*10;
				if(direction <=5 && is_valid_coordinate(i_origin+1, j_origin+1, check_mamals))//move right
				{
					check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				else if(is_valid_coordinate(i_origin+1, j_origin-1, check_mamals))//move left 
				{
					check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			if (is_valid_coordinate(i_origin+1, j_origin+1, check_mamals))//right because left not possible
			{
				check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			if (is_valid_coordinate(i_origin+1, j_origin-1, check_mamals))//left because right not possible
			{
				check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}

			steps=2;
		}
		if(steps==2) {
			if ((is_valid_coordinate(i_origin+2, j_origin-2, check_mamals))||is_valid_coordinate(i_origin+2, j_origin+2, check_mamals)) {
				double direction=1+(int)Math.random()*10;
				//move right
				if(direction <=5 && is_valid_coordinate(i_origin+2, j_origin+2, check_mamals) && check_mamals[i_origin+1][j_origin+1].equals("*")){ //move right
					check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				//move left 
				else if(is_valid_coordinate(i_origin+2, j_origin-2, check_mamals) && check_mamals[i_origin+1][j_origin-1].equals("*")){ //move left 		
					check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			//right because left not possible
			if (is_valid_coordinate(i_origin+2, j_origin+2, check_mamals)&& check_mamals[i_origin+1][j_origin+1].equals("*")) { //only right
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			//left because right not possible
			if (is_valid_coordinate(i_origin+2, j_origin-2, check_mamals)&& check_mamals[i_origin+1][j_origin-1].equals("*")) { //only left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
		}
		return false;
	}

}
