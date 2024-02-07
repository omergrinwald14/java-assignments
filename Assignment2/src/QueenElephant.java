
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
		if(i_origin == i_dest+2) 
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);

		//up
		//regular move
		if(i_origin == i_dest-1) {
			if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
				if(check_mamals[i_dest][j_dest].equals("*")){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
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
	public boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals)
	{
		double i_direction=1+(int)Math.random()*10;
		//move down - like regular elephant
		if(i_direction<=5 && (is_valid_coordinate(i_origin+1, j_origin+1)|| is_valid_coordinate(i_origin+1, j_origin-1)))
			super.forward_computer(i_origin, j_origin, check_mamals);
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
	public boolean first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin + 1][j_origin + 1].type==1)//move down right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
				return true;
			}
		if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin + 1][j_origin - 1].type==1)//move down left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
				return true;
			}
		if(is_valid_coordinate(i_origin-2, j_origin+2) && check_mamals[i_origin-1][j_origin + 1].type==1)//move up right
			if(check_mamals[i_origin-2][j_origin + 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
				return true;
			}
		if(is_valid_coordinate(i_origin-2, j_origin-2) && check_mamals[i_origin-1][j_origin - 1].type==1)//move up left
			if(check_mamals[i_origin-2][j_origin - 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin-1]=new Mamals("*",0);
				return true;
			}
		return false;
	}
	public boolean isBlockedPlayer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		//check if move up is possible
		if(is_valid_coordinate(i+1,j+1)&&check_mamals[i+1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i+1,j-1)&&check_mamals[i+1][j-1].name.equals("*"))//left
			flag=false;
		//check if move down is possible
		if(is_valid_coordinate(i-1,j+1)&&check_mamals[i-1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i-1,j+1)&&check_mamals[i-1][j+1].name.equals("*"))//right
			flag=false;
		//check if eat up is possible
		if(is_valid_coordinate(i+2,j+2)&&check_mamals[i+2][j+2].name.equals("*"))//right
			if(check_mamals[i+1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i+2,j-2)&&check_mamals[i+2][j-2].name.equals("*"))//left
			if(check_mamals[i+1][j-1].type==2)
				flag=false;

		return flag;
	}
	public boolean isBlockedComputer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		//check if move up is possible
		if(is_valid_coordinate(i+1,j+1)&&check_mamals[i+1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i+1,j-1)&&check_mamals[i+1][j-1].name.equals("*"))//left
			flag=false;
		//check if move down is possible
		if(is_valid_coordinate(i-1,j+1)&&check_mamals[i-1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i-1,j-1)&&check_mamals[i-1][j-1].name.equals("*"))//left
			flag=false;
		//check if eat down is possible
		if(is_valid_coordinate(i-2,j+2)&&check_mamals[i-2][j+2].name.equals("*"))//right
			if(check_mamals[i-1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i-2,j-2)&&check_mamals[i-2][j-2].name.equals("*"))//left
			if(check_mamals[i-1][j-1].type==2)
				flag=false;

		return flag;

	}

}
