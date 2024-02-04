
public class Mamals {
protected String type;
public static boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)
	{
		 if (i_origin == i_dest + 1)
	            if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
	            	if(check_mamals[i_dest][j_dest].equals("*"))
	            	{
	      
	            	check_mamals[i_origin][j_origin]=new Mamals("*");
		 check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
	            	}
	                return true;
	        return false;
	}
public static boolean is_first_food(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
	        
	        if (i_origin == i_dest + 2) { //move up
	            if (j_origin == j_dest - 2)//move up and right
	            	if(check_mamals[i_origin + 1][j_origin + 1].charAT(1).equals("2"))
	            		check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
	            	check_mamals[i_origin][j_origin]="*";
		 check_mamals[i_dest+1][j_dest+1]="*";
	                    return true;
	                }
	            if (j_origin == j_dest + 2)
	            if(check_mamals[i_origin + 1][j_origin - 1].charAT(1).equals("2"))
                {//move up and left
	            	check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
            	check_mamals[i_origin][j_origin]="*";
	 check_mamals[i_dest+1][j_dest-1]="*";
	                
	                    return true;
	        }
	                
                return false;
	        }

public static boolean is_double_food(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals) {
	
}

//constructors
public Mamals (String type) {
		this.type=type;
}

//getters
public String GetType() {
	return type;
}
public String toString() {
	return type;
}

public boolean equals(Mamals other) {
	if(this.type.equals(other.type))
		return true;
	else
		return false;
}
public boolean equals(String other) {
	if(this.type.equals(other))
		return true;
	else
		return false;
}

}