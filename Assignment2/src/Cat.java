
public class Cat extends Mamals {

	public Cat(String name,int type) {
		super(name,type);
	}
public static boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)
	
	{
		if (Math.abs(i_origin-i_dest)==1)//check if its valid move
			if (Math.abs(j_origin-j_dest)==1)
			{
				if(check_mamals[i_dest][j_dest].equals("*")||check_mamals[i_dest][j_dest].equals("-"))
				{
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
			}
		return false;
	}
public static boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {

	if (check_mamals[i_origin+1][j_origin+1].equals("*")||check_mamals[i_origin+1][j_origin+1].equals("*"))
	{
		double direction=1+(int)Math.random()*10;
		if(direction <=5)//move right
		{
			check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]=new Mamals("*",0);
			return true;
		}
			else//move left 
			{
				check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
		}
	if (check_mamals[i_origin+1][j_origin+1].equals("*"))//only right
			{
		check_mamals[i_origin+1][j_origin+1]=check_mamals[i_origin][j_origin];
		check_mamals[i_origin][j_origin]=new Mamals("*",0);
		return true;
			}
	if (check_mamals[i_origin+1][j_origin+1].equals("*"))//only left
	{
		check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
		check_mamals[i_origin][j_origin]=new Mamals("*",0);	
		return true;
	}
	
return false;
}
	}



