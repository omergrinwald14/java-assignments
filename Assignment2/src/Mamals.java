public class Mamals { 

	protected String name;
	protected int type;

	//constructor
	public Mamals (String name,int type) {
		this.name=name;
		this.type=type;

	}

	public String toString() {
		return name;
	}
	public static boolean is_valid_coordinate(int i, int j, Mamals[][] check_mamals) {
		if (i > 7 || i < 0 || j > 7 || j < 0)
			return false;
		if(!check_mamals[i][j].name.equals("C1")) //if you are not a cat, only allowed to move to "*"
			if(!check_mamals[i][j].name.equals("*"))
				return false;
		if(check_mamals[i][j].name.equals("C1"))  //if you are a cat, only allowed to move to "*" or "-"
			if(!check_mamals[i][j].name.equals("*") && !check_mamals[i][j].name.equals("-"))
				return false;
		return true;
	}
	public boolean equals(Mamals other) {
		if(this.name.equals(other.name))
			return true;
		else
			return false;
	}
	public boolean equals(String other) {
		if(this.name.equals(other))
			return true;
		else
			return false;
	}
	public boolean isBlockedPlayer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		//check if move up is possible
		if(is_valid_coordinate(i+1, j+1, check_mamals)&&check_mamals[i+1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i+1, j-1, check_mamals)&&check_mamals[i+1][j-1].name.equals("*"))//left
			flag=false;
		//check if eat up is possible
		if(is_valid_coordinate(i+2, j+2, check_mamals)&&check_mamals[i+2][j+2].name.equals("*"))//right
			if(check_mamals[i+1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i+2, j-2, check_mamals)&&check_mamals[i+2][j-2].name.equals("*"))//left
			if(check_mamals[i+1][j-1].type==2)
				flag=false;

		return flag;
	}
	public boolean isBlockedComputer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		//check if move down is possible
		if(is_valid_coordinate(i-1, j+1, check_mamals)&&check_mamals[i-1][j+1].name.equals("*"))//right
			flag=false;
		if(is_valid_coordinate(i-1, j-1, check_mamals)&&check_mamals[i-1][j-1].name.equals("*"))//left
			flag=false;
		//check if eat down is possible
		if(is_valid_coordinate(i-2,j+2, check_mamals)&&check_mamals[i-2][j+2].name.equals("*"))//right
			if(check_mamals[i-1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i-2, j-2, check_mamals)&&check_mamals[i-2][j-2].name.equals("*"))//left
			if(check_mamals[i-1][j-1].type==2)
				flag=false;

		return flag;

	}

	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin == i_dest + 1)
			if (j_origin == j_dest + 1 || j_origin == j_dest - 1){
				check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return true;
			}
		return false;
	}
	public boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin-2 == i_dest) { 
			if (j_origin+2 == j_dest)//move up and right
				if(check_mamals[i_origin - 1][j_origin + 1].type==2) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]= new Mamals("*",0);
					check_mamals[i_dest-1][j_dest+1]=new Mamals("*",0);
					return true;
				}

			if (j_origin-2 == j_dest)//move up and left
				if(check_mamals[i_origin - 1][j_origin - 1].type==2) {
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					check_mamals[i_dest-1][j_dest-1]=new Mamals("*",0);
					return true;
				}
		}
		return false;
	}
	public String double_food_player(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(is_valid_coordinate(i_origin-2, j_origin+2, check_mamals))//up right
 			if(check_mamals[i_origin - 1][j_origin + 1].type==2){
	        	//up and left
 				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
				return "U-R";
			}
		if(is_valid_coordinate(i_origin-2, j_origin-2, check_mamals)) //up and left
			if(check_mamals[i_origin - 1][j_origin - 1].type==2)//check that is empty
			{  
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin-1][j_origin-1]=new Mamals("*",0);
				return "U-R";
			}

		if(is_valid_coordinate(i_origin+2, j_origin+2, check_mamals))//down right
			if(check_mamals[i_origin +1][j_origin + 1].type==2)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
				return "D-R";
			}
		if(is_valid_coordinate(i_origin+2, j_origin-2, check_mamals))//down left
			if(check_mamals[i_origin +1][j_origin - 1].type==2)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
				return "D-L";
			}
		return "";
	}
	public boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		if ((is_valid_coordinate(i_origin+1,j_origin+1, check_mamals)&&check_mamals[i_origin+1][j_origin+1].equals(""))||(is_valid_coordinate(i_origin+1, j_origin-1, check_mamals)&&check_mamals[i_origin+1][j_origin-1].equals("")))
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
		if (is_valid_coordinate(i_origin+1, j_origin+1, check_mamals) && check_mamals[i_origin+1][j_origin+1].equals("*"))//only right
		{
			check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]=new Mamals("*",0);
			return true;
		}
		if (is_valid_coordinate(i_origin+1, j_origin-1, check_mamals) && check_mamals[i_origin+1][j_origin-1].equals("*"))//only left
		{
			check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]=new Mamals("*",0);	
			return true;
		}

		return false;
	}
	public String first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(is_valid_coordinate(i_origin+2, j_origin+2, check_mamals) && check_mamals[i_origin + 1][j_origin + 1].type==1){//move down right
			check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
			check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
			return "D-R";
		}
		if(is_valid_coordinate(i_origin+2, j_origin-2, check_mamals) && check_mamals[i_origin + 1][j_origin - 1].type==1){//move down left
			check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
			check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
			return "D-L";
		}
		return "";
	}
	public String double_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(is_valid_coordinate(i_origin-2, j_origin+2, check_mamals) && check_mamals[i_origin - 1][j_origin + 1].type==1)//back right
			if(check_mamals[i_origin - 2][j_origin + 2].equals("*"))
			{
				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
				return "U-R";
			}
		//up and left
		if(is_valid_coordinate(i_origin-2, j_origin-2, check_mamals) && check_mamals[i_origin - 1][j_origin - 1].type==1)//back left
			if(check_mamals[i_origin - 2][j_origin - 2].equals("*"))//check that is empty
			{//move up and left
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin-1][j_origin-1]=new Mamals("*",0);
				return "U-L";
			}

		if(is_valid_coordinate(i_origin+2, j_origin+2, check_mamals) && check_mamals[i_origin +1][j_origin + 1].type==1)//up right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*"))//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
				return "D-R";
			}
		if(is_valid_coordinate(i_origin+2, j_origin-2, check_mamals) && check_mamals[i_origin +1][j_origin - 1].type==1)//up left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*"))//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
				return "D-L";
			}
		return "";
	}


}
