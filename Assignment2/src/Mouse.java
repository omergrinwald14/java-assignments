
public class Mouse extends Mamals {
	protected int PostionBeforeLastEat; // helps to prevent endless loop of double eating
	public Mouse(String name,int type) {
		super(name,type);
	}
	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		if (i_origin == i_dest+1)
			super.forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals);

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

		return false;
	}
	public boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin == i_dest - 2)  //move up
			if (j_origin == j_dest - 2)//move up and right
				if(check_mamals[i_origin - 1][j_origin + 1].type==2)
					if(check_mamals[i_origin - 2][j_origin + 2].equals("*")) {//check that is empty
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
						check_mamals[i_origin][j_origin]= new Mamals("*",0);
						PostionBeforeLastEat=i_origin*10+j_origin;
						return true;
					}
		if (j_origin == j_dest + 2)//up and left
			if(check_mamals[i_origin - 1][j_origin - 1].type==2)
				if(check_mamals[i_origin - 2][j_origin - 2].equals("*")) {//check that is empty
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return true;
				}
		return false;
	}
	public String double_food_player(int i_origin,int j_origin,Mamals [][] check_mamals) {
		int i_cancel=PostionBeforeLastEat/10;
		int j_cancel=PostionBeforeLastEat%10;
		if(check_mamals[i_origin - 1][j_origin + 1].type==2)//up right
			if(check_mamals[i_origin - 2][j_origin + 2].equals("*")) {
				if(i_cancel!=i_origin-2||j_cancel!=j_origin+2) {
					check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]= new Mamals("*",0);
					check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return "U-R";
				}
			}
		//up and left
		if(check_mamals[i_origin - 1][j_origin - 1].type==2)
			if(check_mamals[i_origin - 2][j_origin - 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin-2||j_cancel!=j_origin-2) {
					check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return "U-L";
				}
			}

		if(check_mamals[i_origin +1][j_origin + 1].type==2)//down right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin-2||j_cancel!=j_origin-2) {
					check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return "D-R";
				}
			}
		if(check_mamals[i_origin +1][j_origin - 1].type==2)//down left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin+2||j_cancel!=j_origin-2) {
					check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					PostionBeforeLastEat=i_origin*10+j_origin;
					return "D-L";
				}
			}
		return "";
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
	public boolean first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {

		if(is_valid_coordinate(i_origin+2, j_origin+2) && check_mamals[i_origin + 1][j_origin + 1].type==1)//move up right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				PostionBeforeLastEat=i_origin*10+j_origin;
				return true;
			}
		if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin + 1][j_origin - 1].type==1)//move up left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*"))
			{//check that is empty
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				PostionBeforeLastEat=i_origin*10+j_origin;
				return true;
			}

		return false;
	}
	public String double_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		int i_cancel=PostionBeforeLastEat/10;
		int j_cancel=PostionBeforeLastEat%10;
		if(is_valid_coordinate(i_origin-2, j_origin+2) && check_mamals[i_origin - 1][j_origin + 1].type==1)//up right
			if(check_mamals[i_origin - 2][j_origin + 2].equals("*")) {
				if(i_cancel!=i_origin-2||j_cancel!=j_origin+2) {
				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				return "D-R";
				}
			}
		if(is_valid_coordinate(i_origin-2, j_origin-2) && check_mamals[i_origin - 1][j_origin - 1].type==1)//up left
			if(check_mamals[i_origin - 2][j_origin - 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin-2||j_cancel!=j_origin-2) {
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return "D-L";
				}
			}

		if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin +1][j_origin + 1].type==1)//down right
			if(check_mamals[i_origin + 2][j_origin + 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin+2||j_cancel!=j_origin+2) {
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return "U-R";
				}
			}
		if(is_valid_coordinate(i_origin+2, j_origin-2) && check_mamals[i_origin +1][j_origin - 1].type==1)//down left
			if(check_mamals[i_origin + 2][j_origin - 2].equals("*")){//check that is empty
				if(i_cancel!=i_origin+2||j_cancel!=j_origin-2) {
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				return "U-L";
				}
			}
		return "";
	}

}
