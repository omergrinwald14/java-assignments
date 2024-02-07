
public class Cat extends Mamals {

	public Cat(String name,int type) {
		super(name,type);
	}
	public boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (Math.abs(i_origin-i_dest)==1)//check if its valid move
			if (Math.abs(j_origin-j_dest)==1){	
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					if((i_origin+j_origin)%2 == 0)
						check_mamals[i_origin][j_origin]=new Mamals("-",0);
					else
						check_mamals[i_origin][j_origin] = new Mamals("*",0);
					return true;
			}
		return false;
	}
	public boolean forward_computer(int i_origin,int j_origin,Mamals[][] check_mamals) {
		int i_dest=0;
		int j_dest=0;
		boolean flag=false;// becomes true when there is no move
		int [] check_random= new int [8];
		int direction=(int)(1+Math.random()*8);
		int count=0;
		
		while(!flag) {
			if(direction ==1) { //move up and the most left
				if(is_valid_coordinate(i_origin+1, j_origin-1) && check_mamals[i_origin+1][j_origin-1].equals("*"))	{
					i_dest=1;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==2) { //move up and the most left
				if(is_valid_coordinate(i_origin+1, j_origin) && check_mamals [i_origin+1][j_origin].equals("*")){
					i_dest=1;
					j_dest=0;
					flag=true;
				}
			}
			if(direction ==3) { //move up and the most left
				if(is_valid_coordinate(i_origin+1, j_origin+1) && check_mamals [i_origin+1][j_origin+1].equals("*")){
					i_dest=1;
					j_dest=1;
					flag=true;
				}
			}
			if(direction ==4)//move up and the most left
			{
				if(is_valid_coordinate(i_origin, j_origin-1) && check_mamals [i_origin][j_origin-1].equals("*")){
					i_dest=0;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==5)//move up and the most left
			{
				if(is_valid_coordinate(i_origin, j_origin+1) && check_mamals [i_origin][j_origin+1].equals("*")){
					i_dest=0;
					j_dest=1;
					flag=true;
				}
			}
			if(direction ==6)//move up and the most left
			{
				if(is_valid_coordinate(i_origin-1, j_origin-1) && check_mamals[i_origin-1][j_origin-1].equals("*")){
					i_dest=-1;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==7)//move up and the most left
			{
				if(is_valid_coordinate(i_origin-1, j_origin) && check_mamals [i_origin-1][j_origin].equals("*")){
					i_dest=-1;
					j_dest=0;
					flag=true;
				}
			}
			if(direction ==8)//move up and the most left
			{
				if(is_valid_coordinate(i_origin-1, j_origin+1) && check_mamals [i_origin-1][j_origin+1].equals("*")) {
					i_dest=-1;
					j_dest=1;
					flag=true;
				}
			}
			check_random[direction-1]++; // mark that in that direction- not possible to move
		for(int i=0;i<8;i++)
			if(check_random[i]!=0)
				count++;
		if(count==8)
			break;  //the tool is blocked
		else
			count=0; // for the next count
		
			while(check_random[direction-1]!=0)
				direction=(int)(1+Math.random()*8);//random a new square
			
		}
		if(i_dest!=0||j_dest!=0) {
			check_mamals[i_origin+i_dest][j_origin+j_dest]=check_mamals[i_origin][j_origin];
			if((i_origin+j_origin)%2 == 0)
				check_mamals[i_origin][j_origin]=new Mamals("-",0);
			else
				check_mamals[i_origin][j_origin] = new Mamals("*",0);
			return true;
		}
		return false;

	}
	
	public boolean isBlockedPlayer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		//check if move is possible
		if(is_valid_coordinate(i+1, j+1)&&check_mamals[i+1][j+1].name.equals("*"))//right up
			flag=false;
		if(is_valid_coordinate(i+1, j-1)&&check_mamals[i+1][j-1].name.equals("*"))//left up
			flag=false;
		if(is_valid_coordinate(i-1, j+1)&&check_mamals[i-1][j+1].name.equals("*"))//down up
			flag=false;
		if(is_valid_coordinate(i-1, j-1)&&check_mamals[i-1][j-1].name.equals("*"))//down up
			flag=false;
		if(is_valid_coordinate(i+1, j)&&check_mamals[i+1][j].name.equals("*"))//up
			flag=false;
		if(is_valid_coordinate(i-1, j)&&check_mamals[i-1][j].name.equals("*"))//down
			flag=false;
		if(is_valid_coordinate(i, j-1)&&check_mamals[i][j-1].name.equals("*"))//left
			flag=false;
		if(is_valid_coordinate(i, j+1)&&check_mamals[i][j+1].name.equals("*"))//right
			flag=false;
		//check if eat up is possible
		if(is_valid_coordinate(i+2, j+2)&&check_mamals[i+2][j+2].name.equals("*"))//right
			if(check_mamals[i+1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i+2, j-2)&&check_mamals[i+2][j-2].name.equals("*"))//left
			if(check_mamals[i+1][j-1].type==2)
				flag=false;
		
		return flag;
	}
	public boolean isBlockedComputer(Mamals[][] check_mamals,int i,int j) {
		boolean flag = true;
		if(is_valid_coordinate(i+1, j+1)&&check_mamals[i+1][j+1].name.equals("*"))//right up
			flag=false;
		if(is_valid_coordinate(i+1, j-1)&&check_mamals[i+1][j-1].name.equals("*"))//left up
			flag=false;
		if(is_valid_coordinate(i-1, j+1)&&check_mamals[i-1][j+1].name.equals("*"))//down up
			flag=false;
		if(is_valid_coordinate(i-1, j-1)&&check_mamals[i-1][j-1].name.equals("*"))//down up
			flag=false;
		if(is_valid_coordinate(i+1, j)&&check_mamals[i+1][j].name.equals("*"))//up
			flag=false;
		if(is_valid_coordinate(i-1, j)&&check_mamals[i-1][j].name.equals("*"))//down
			flag=false;
		if(is_valid_coordinate(i, j-1)&&check_mamals[i][j-1].name.equals("*"))//left
			flag=false;
		if(is_valid_coordinate(i, j+1)&&check_mamals[i][j+1].name.equals("*"))//right
			flag=false;
		//check if eat down is possible
		if(is_valid_coordinate(i-2,j+2)&&check_mamals[i-2][j+2].name.equals("*"))//right
			if(check_mamals[i-1][j+1].type==2)
				flag=false;
		if(is_valid_coordinate(i-2, j-2)&&check_mamals[i-2][j-2].name.equals("*"))//left
			if(check_mamals[i-1][j-1].type==2)
				flag=false;
		
		return flag;
		
	}


}





