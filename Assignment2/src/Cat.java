
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
	public static boolean forward_computer(int i_origin,int j_origin,Mamals[][] check_mamals) {
		int i_dest=0;
		int j_dest=0;
		boolean flag=false;

		for (int direction=1;direction<=8|| !flag ;direction++)
		{
			if(direction ==1)//move up and the most left
			{
				if(is_valid_coordinate(i_origin+1, j_origin-1) && check_mamals[i_origin+1][j_origin-1].equals("*"))
				{
					i_dest=1;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==2)//move up and the most left
			{
				if(is_valid_coordinate(i_origin+1, j_origin) && check_mamals [i_origin+1][j_origin].equals("*"))
				{
					i_dest=1;
					j_dest=0;
					flag=true;
				}
			}
			if(direction ==3)//move up and the most left
			{
				if(is_valid_coordinate(i_origin+1, j_origin+1) && check_mamals [i_origin+1][j_origin+1].equals("*"))
				{
					i_dest=1;
					j_dest=1;
					flag=true;
				}
			}
			if(direction ==4)//move up and the most left
			{
				if(is_valid_coordinate(i_origin, j_origin-1) && check_mamals [i_origin][j_origin-1].equals("*"))
				{
					i_dest=0;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==5)//move up and the most left
			{
				if(is_valid_coordinate(i_origin, j_origin+1) && check_mamals [i_origin][j_origin+1].equals("*"))
				{
					i_dest=0;
					j_dest=1;
					flag=true;
				}
			}
			if(direction ==6)//move up and the most left
			{
				if(is_valid_coordinate(i_origin-1, j_origin-1) && check_mamals[i_origin-1][j_origin-1].equals("*"))
				{
					i_dest=-1;
					j_dest=-1;
					flag=true;
				}
			}
			if(direction ==7)//move up and the most left
			{
				if(is_valid_coordinate(i_origin-1, j_origin) && check_mamals [i_origin-1][j_origin].equals("*"))
				{
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
		}
		if(i_dest!=0||j_dest!=0) {
			check_mamals[i_origin+i_dest][j_origin+j_dest]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]=new Mamals("*",0);	
			return true;
		}
		return flag;

	}
}





