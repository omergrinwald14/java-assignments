
public class QueenMouse extends Mouse {

	public QueenMouse(String name, int type) {
		super(name, type);
	}
	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		// move up
		if (i_origin-1 == i_dest) {
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		if(i_origin-2 == i_dest) { 			
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		//move down
		//1 step
		if (i_origin+1 == i_dest) 
			if (j_origin-1 == j_dest || j_origin+1 == j_dest){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true; 
				}
		//2 steps
		if (i_origin+2 == i_dest) {
			if (j_origin-2 == j_dest) // left
				if(check_mamals[i_origin+1][j_origin-1].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			if (j_origin+2 == j_dest) // right
				if(check_mamals[i_origin+1][j_origin+1].equals("*")) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		}

		return false;
	}
	public boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals)
	{
		double i_direction=1+(int)Math.random()*10;
		//move down - like regular mouse
		if(i_direction<=5 && (is_valid_coordinate(i_origin+1, j_origin+1)|| is_valid_coordinate(i_origin+1, j_origin-1))) {
			super.forward_computer(i_origin, j_origin, check_mamals);
			return true;
		}
		int steps =(int) (1+Math.random());
		if(steps==1) {
			//move up
			if ((is_valid_coordinate(i_origin-1,j_origin+1)&&check_mamals[i_origin-1][j_origin+1].equals("*"))||(is_valid_coordinate(i_origin-1, j_origin-1)&&check_mamals[i_origin-1][j_origin-1].equals("*")))
			{
				double j_direction=1+(int)Math.random()*10;
				if(j_direction <=5 && is_valid_coordinate(i_origin-1, j_origin+1))//move right
				{
					check_mamals[i_origin-1][j_origin+1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				else if(is_valid_coordinate(i_origin-1, j_origin-1))//move left 
				{
					check_mamals[i_origin-1][j_origin-1]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			if (is_valid_coordinate(i_origin-1, j_origin+1) && check_mamals[i_origin-1][j_origin+1].equals("*"))//right because left not possible
			{
				check_mamals[i_origin-1][j_origin+1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			if (is_valid_coordinate(i_origin-1, j_origin-1) && check_mamals[i_origin-1][j_origin-1].equals("*"))//left because right not possible
			{
				check_mamals[i_origin-1][j_origin-1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
			steps=2;
		}
		if(steps==2) {
			//up
			if ((is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+2][j_origin-2].equals("*"))||is_valid_coordinate(i_origin+2, j_origin+2) && (check_mamals[i_origin+2][j_origin+2].equals("*"))) {
				double j_direction=1+(int)Math.random()*10;
				//move right
				if(j_direction <=5 && is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin+1][j_origin+1].equals("*")){ //move right
					check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
				//move left 
				else if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+1][j_origin-1].equals("*")){ //move left 		
					check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);	
					return true;
				}
			}
			//right because left not possible
			if (is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin+2][j_origin+2].equals("*") && check_mamals[i_origin+1][j_origin+1].equals("*")) { //only right
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
			//left because right not possible
			if (is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin+2][j_origin-2].equals("*") && check_mamals[i_origin+1][j_origin-1].equals("*")) { //only left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
		}
		return false;
	}
	public boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin-2 == i_dest) {
			super.first_food_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		if (i_origin+2 == i_dest)  //move down
			if (j_origin+2 == j_dest) //down and right
				if(check_mamals[i_origin + 1][j_origin + 1].type==2) {
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
						check_mamals[i_origin][j_origin]= new Mamals("*",0);
						PostionBeforeLastEat=i_origin*10+j_origin;
						return true;
					}
		if (j_origin+2 == j_dest) //down and left
			if(check_mamals[i_origin + 1][j_origin - 1].type==2) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return true;
				}
		return false;
	}
	public boolean first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		double i_direction=1+(int)Math.random()*10;
		if(i_direction<=5) //move down regular
			if(is_valid_coordinate(i_origin+2, j_origin+2) && (check_mamals[i_origin - 1][j_origin + 1].type==1||check_mamals[i_origin - 1][j_origin - 1].type==1)) {
				super.first_food_computer(i_origin, j_origin, check_mamals);
				return true;
			}
		//move down
		if(is_valid_coordinate(i_origin-2, j_origin+2) && check_mamals[i_origin - 1][j_origin + 1].type==1)//move up right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				PostionBeforeLastEat=i_origin*10+j_origin;
				return true;
			}
		if(is_valid_coordinate(i_origin-2, j_origin-2) && check_mamals[i_origin - 1][j_origin - 1].type==1)//move up left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				PostionBeforeLastEat=i_origin*10+j_origin;
				return true;
			}

		return false;
	}

}
