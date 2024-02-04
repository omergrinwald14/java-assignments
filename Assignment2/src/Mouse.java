
public class Mouse extends Elephant {

	public Mouse(String name,int type) {
		super(name,type);
	}
	
	public static boolean first_food(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin == i_dest - 2) { //move up
			if (j_origin == j_dest - 2)//move up and right
				if(check_mamals[i_origin - 1][j_origin + 1].type==2)
					if(check_mamals[i_origin - 2][j_origin + 2].type==0)//check that is empty
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
	
			return true;
		}
		if (j_origin == j_dest + 2)//up and left
			if(check_mamals[i_origin - 1][j_origin - 1].type==2)
				if(check_mamals[i_origin - 2][j_origin - 2].type==0)//check that is empty
				{//move up and left
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);


					return true;
				}

		return false;
	}

	public static boolean double_food(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(check_mamals[i_origin - 1][j_origin + 1].type==2)//up right
			if(check_mamals[i_origin - 2][j_origin + 2].type==0)
			{
				check_mamals[i_origin-2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]= new Mamals("*",0);
				check_mamals[i_origin-1][j_origin+1]=new Mamals("*",0);
				return true;
			}
		//up and left
		if(check_mamals[i_origin - 1][j_origin - 1].type==2)
			if(check_mamals[i_origin - 2][j_origin - 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
			
				return true;
			}
		if(check_mamals[i_origin - 1][j_origin - 1].type==2)
			if(check_mamals[i_origin - 2][j_origin - 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin-2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
			
				return true;
			}
		if(check_mamals[i_origin +1][j_origin + 1].type==2)//back right
			if(check_mamals[i_origin + 2][j_origin + 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				
				return true;
			}
		if(check_mamals[i_origin +1][j_origin - 1].type==2)//back left
			if(check_mamals[i_origin + 2][j_origin - 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				
				return true;
			}
		return false;
	}
	
}
