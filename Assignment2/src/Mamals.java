public class Mamals { 

	protected String name;
	protected int type;

	public static boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)

	{
		if (i_origin == i_dest + 1)
			if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
				if(check_mamals[i_dest][j_dest].equals("*")){
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					return true;
				}
		return false;
	}
	public static boolean first_food_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
		if (i_origin == i_dest - 2) { //move up
			if (j_origin == j_dest - 2)//move up and right
				if(check_mamals[i_origin - 1][j_origin + 1].type==2)
					if(check_mamals[i_origin - 2][j_origin + 2].equals("*"))//check that is empty
						check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
			check_mamals[i_dest-1][j_dest+1]=new Mamals("*",0);
			return true;
		}
		if (j_origin == j_dest + 2)//up and left
			if(check_mamals[i_origin - 1][j_origin - 1].type==2)
				if(check_mamals[i_origin - 2][j_origin - 2].equals("*"))//check that is empty
				{//move up and left
					check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
					check_mamals[i_origin][j_origin]=new Mamals("*",0);
					check_mamals[i_dest-1][j_dest-1]=new Mamals("*",0);

					return true;
				}

		return false;
	}

	public static boolean double_food_player(int i_origin,int j_origin,Mamals [][] check_mamals) {
		if(check_mamals[i_origin - 1][j_origin + 1].type==2)//up right
			if(check_mamals[i_origin - 2][j_origin + 2].equals("*"))
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
				check_mamals[i_origin-1][j_origin-1]=new Mamals("*",0);
				return true;
			}
		
		if(check_mamals[i_origin +1][j_origin + 1].type==2)//back right
			if(check_mamals[i_origin + 2][j_origin + 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
				return true;
			}
		if(check_mamals[i_origin +1][j_origin - 1].type==2)//back left
			if(check_mamals[i_origin + 2][j_origin - 2].type==0)//check that is empty
			{//move up and left
				check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);
				check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
				return true;
			}
		return false;
	}
	
	public static boolean forward_computer(int i_origin,int j_origin,Mamals [][] check_mamals)

	{
			if (check_mamals[i_origin+1][j_origin+1].equals("*")||check_mamals[i_origin+1][j_origin-1].equals("*"))
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
			if (check_mamals[i_origin+1][j_origin-1].equals("*"))//only left
			{
				check_mamals[i_origin+1][j_origin-1]=check_mamals[i_origin][j_origin];
				check_mamals[i_origin][j_origin]=new Mamals("*",0);	
				return true;
			}
			
		return false;
	}
	public static boolean first_food_computer(int i_origin,int j_origin,Mamals [][] check_mamals) {
		
				if(check_mamals[i_origin + 1][j_origin + 1].type==1)//move up right
					if(check_mamals[i_origin + 2][j_origin + 2].equals("*"))
					{//check that is empty
						check_mamals[i_origin+2][j_origin+2]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
			check_mamals[i_origin+1][j_origin+1]=new Mamals("*",0);
			return true;
		}
				if(check_mamals[i_origin + 1][j_origin - 1].type==1)//move up left
					if(check_mamals[i_origin + 2][j_origin - 2].equals("*"))
					{//check that is empty
						check_mamals[i_origin+2][j_origin-2]=check_mamals[i_origin][j_origin];
			check_mamals[i_origin][j_origin]= new Mamals("*",0);
			check_mamals[i_origin+1][j_origin-1]=new Mamals("*",0);
			return true;
				}

		return false;
	}
	
	
	
	
	
	
	
	

	//constructors
	public Mamals (String name,int type) {
		this.name=name;
		this.type=type;

	}

	//getters
	public String Getname() {
		return name;
	}
	public String toString() {
		return name;
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

}