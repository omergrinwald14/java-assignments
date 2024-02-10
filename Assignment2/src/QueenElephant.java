
public class QueenElephant extends Elephant {

	public QueenElephant(String name, int type) {
		super(name, type);
	}
	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		//up
		//1 step
		if (i_origin-1 == i_dest) {
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		//2 steps
		if(i_origin-2 == i_dest) {
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		//down
		//1 step
		if(i_origin+1 == i_dest) {
			if (j_origin-1 == j_dest || j_origin+1 == j_dest){
				check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
		}
		//2 steps
		if(i_origin+2 == i_dest) {
			if (j_origin-2 == j_dest) // left
				if(check_mamals[i_origin+1][j_origin-1].equals("*")){ 
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
	public boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin-2 == i_dest) {//move up and right
			super.first_food_player(i_dest, j_dest, i_origin, j_origin, check_mamals);
			return true;
		}
		if (i_origin+2 == i_dest) {
			if (j_origin-2 == j_dest)//move down and left
				if(check_mamals[i_origin + 1][j_origin - 1].type==2) {
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
						check_mamals[i_origin][j_origin]=new Mamals("*",0);
						check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
						return true;
					}
			if (j_origin+2 == j_dest)//move down and right
				if(check_mamals[i_origin + 1][j_origin + 1].type==2) {
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
		if(i_direction<=5) 
			if(is_valid_coordinate(i_origin+1, j_origin+1)|| is_valid_coordinate(i_origin+1, j_origin-1)) {
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
	public String first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		double i_direction=1+(int)Math.random()*10;
		if(i_direction<=5) //move down regular
			if(is_valid_coordinate(i_origin+2, j_origin+2) && (check_mamals[i_origin - 1][j_origin + 1].type==1||check_mamals[i_origin - 1][j_origin - 1].type==1)) {
				String direction = super.first_food_computer(i_origin, j_origin, check_mamals);
				return direction;
			}
		if(is_valid_coordinate(i_origin-2, j_origin+2) && check_mamals[i_origin-1][j_origin + 1].type==1){//move up right
				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
				return "U-R";
			}
		if(is_valid_coordinate(i_origin-2, j_origin-2) && check_mamals[i_origin-1][j_origin - 1].type==1){//move up left
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin-1]=new Mamals("*",0);
				return "U-L";
			}
		return "";
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
