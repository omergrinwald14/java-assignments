
public class Mamals {
String type;
public Mamals (String type)
{
	this.type=type;
}
	public static boolean forward_player(int i_dest,int j_dest,int i_origin,int j_origin,Mamals [][] check_mamals)
	{
		 if (i_origin == i_dest + 1)
	            if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
	            	if(check_mamals[i_dest][j_dest].equals("*"))
	            	{
	      
	            	check_mamals[i_origin][j_origin]=new squre("*");
		 check_mamals[i_dest][j_dest]=check_mamals[i_origin][j_origin];
	            	}
	                return true;
	        return false;
	}
	  public static boolean is_first_food(int i_dest,int j_dest,int i_origin,int j_origin,string "direction",Mamals [][] check_mamals) {
	        
	        if (i_origin == i_dest + 2) { //move up
	            if (j_origin == j_dest - 2)//move up and right
	            	String ans=check_mamals[i_origin - 1][j_origin + 1].charAT(1);
	                if (ans.equals("2"))
	                {
	                	String ans2=check_mamals[i_origin][j_origin];
	            	check_mamals[i_origin][j_origin]="*";
		 check_mamals[i_dest+1][j_dest+1]="*";
		 check_mamals[i_dest+1][j_dest+2]=ans2;
	                    return true;
	                }
	            if (j_origin == j_dest + 2)
	            	String ans=check_mamals[i_origin - 1][j_origin - 1].charAT(1);
                if (ans.equals("2"))
                {//move up and left
                	String ans2=check_mamals[i_origin][j_origin];
            	check_mamals[i_origin][j_origin]="*";
	 check_mamals[i_dest+1][j_dest-1]="*";
	 check_mamals[i_dest+1][j_dest-2]=ans2;
	                
	                    return true;
	        }
	                
                return false;
	        }
}
