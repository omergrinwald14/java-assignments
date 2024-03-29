import java.util.Scanner;

public class Mamals_Checkers {
	static Scanner sc = new Scanner(System.in);
	static Mamals[][] check_mamals = new Mamals[8][8];
	static int status_game = 0;
	static int status_player = 0;
	public static void main(String[] args) {
		System.out.println("Welcome to Fatma Checkers. To start the game press 1, to exit press 0:");
		String choose = sc.next();
		if (choose.equals("0"))
			endGame();
		if(choose.equals("1"))
			startGame();
		int status_game=1;
		int status_player=1;
		while (status_game==1 && status_player==1){
			status_player =	player_turn(check_mamals);
			check_Q_player(check_mamals);
			status_game = calc_player_winner(check_mamals);
			printBoard(check_mamals);
			status_game=computer_turn(check_mamals);
			check_Q_computer(check_mamals);
			status_game=calc_computer_winner(check_mamals);
			status_game=calc_tie_player(check_mamals);
			printBoard(check_mamals);
		}
	}
	public static void setNewBoard(Mamals[][] check_mamals) {
		for(int i=0;i<check_mamals.length;i++) {
			for(int j=0;j<check_mamals[0].length;j++) {
				if((i+j)%2 == 0)
					check_mamals[i][j] = new Mamals("-",0);	
				else
					check_mamals[i][j] = new Mamals("*",0);	
			}
			check_mamals[0][1] = new Cat("C2",2); //1 line
			check_mamals[0][3] = new Dog("D2",2);
			check_mamals[0][5] = new Elephant("E2",2);
			check_mamals[0][7] = new Mouse("M2",2);
			check_mamals[1][0] = new Dog("D2",2); //2 line
			check_mamals[1][2] = new Elephant("E2",2);
			check_mamals[1][4] = new Mouse("M2",2);
			check_mamals[1][6] = new Cat("C2",2);
			check_mamals[2][1] = new Elephant("E2",2); //3 line
			check_mamals[2][3] = new Mouse("M2",2);
			check_mamals[2][5] = new Cat("C2",2);
			check_mamals[2][7] = new Dog("D2",2);
			check_mamals[5][0] = new Elephant("E1",1); //6 line
			check_mamals[5][2] = new Mouse("M1",1);
			check_mamals[5][4] = new Cat("C1",1);
			check_mamals[5][6] = new Dog("D1",1);
			check_mamals[6][1] = new Dog("D1",1); //7 line
			check_mamals[6][3] = new Elephant("E1",1);
			check_mamals[6][5] = new Mouse("M1",1);
			check_mamals[6][7] = new Cat("C1",1);
			check_mamals[7][0] = new Cat("C1",1); //8 line
			check_mamals[7][2] = new Dog("D1",1);
			check_mamals[7][4] = new Elephant("E1",1);
			check_mamals[7][6] = new Mouse("M1",1);		
		}
	}
	public static void endGame() {
		System.out.println("bye bye");
		status_player=0;
		status_game=0;
	}
	public static void startGame() {
		setNewBoard(check_mamals);
		printBoard(check_mamals);
		status_player=1;
		status_game=1;
	}
	public static void printBoard(Mamals[][] check_mamals) {
		System.out.println("The board:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(check_mamals[i][j].toString() + "");
			}
			System.out.println();
		}
	}
	public static int player_turn(Mamals[][] check_mamals) {	
		boolean flag = true;
		while(flag) {
		System.out.println("It's your turn, please enter your move.");
		String move = sc.next();
		if (move.equals("STOP")) {
			computer_winner();
			return 0;
		}		
		while(!is_valid_string(move)){
			System.out.println("This move is invalid. Please enter a new move.");
			move = sc.next();
		}
		int i_dest = 8 - (move.charAt(0) - '0');
		int j_dest = (move.charAt(1) - '0') - 1;
		int i_origin = 8 - (move.charAt(3) - '0');
		int j_origin = (move.charAt(4) - '0') - 1;
		if(check_mamals[i_origin][j_origin].first_food_player(i_dest,j_dest,i_origin,j_origin,check_mamals)) {
			double_food(check_mamals,i_origin,j_origin,i_dest,j_dest);
				return 1;
		}//finish eat move
		if(check_mamals[i_origin][j_origin].forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals))
			return 1;
		if(calc_tie_player(check_mamals)==0)
			return 0;
		}	
			return 0;	
	}
	
	public static int computer_turn(Mamals[][] check_mamals) {
		boolean moveExecuted=false;
		int i_dest=0;
		int j_dest=0;
		int RandomTool;
		int iRandom;
		int jRandom;
		if(calc_tie_computer(check_mamals)==1) {
			while(!moveExecuted) {
				 RandomTool = RandomComputerTool();
				 iRandom= RandomTool/10;
				 jRandom=RandomTool%10;
				String direction = check_mamals[iRandom][jRandom].first_food_computer(iRandom, jRandom, check_mamals); //check if eat possible and save direction
			     	//understand which direction was the first eat
				if(!direction.equals("")){
					if (direction.equals("D-R")){ 
						i_dest=iRandom+2;
						j_dest=jRandom+2;
					}
					if (direction.equals("D-L")){
						i_dest=iRandom+2;
						j_dest=jRandom-2;
					}
					if (direction.equals("U-R")){
						i_dest=iRandom-2;
						j_dest=jRandom+2;
					}
					if (direction.equals("U-L")){
						i_dest=iRandom-2;
						j_dest=jRandom-2;
					}
					double_food(check_mamals,iRandom,jRandom,i_dest,j_dest);
					moveExecuted=true;
					return 1;
				}
				
				if(check_mamals[iRandom][jRandom].forward_computer(iRandom, jRandom, check_mamals)) {
					moveExecuted=true;
					return 1;
				}
			}
		}
		return 0;
	}
	public static void player_winner() {
		System.out.println("Congratulations, user has won :)");
	}
	public static void computer_winner() {
		System.out.println("Sorry, computer has won:(");
	}
	public static int calc_player_winner(Mamals [][] check_mamals)
	{
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (check_mamals[i][j].type==2)//if there is at least one of "2" not win for now
					return 1; //the game will continue to run
		player_winner();
		return 0;
	}
	public static int calc_computer_winner(Mamals [][] check_mamals)
	{
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (check_mamals[i][j].type==1)//if there is at least one of "2" not win for now
					return 1; //the game will continue to run
		computer_winner();
		return 0;
	}
	public static int calc_tie_player(Mamals [][] check_mamals)
	{
		boolean flag=true;
		for (int i = 0; i < 8&&flag; i++)
			for (int j = 0; j < 8&& flag; j++)
			{	
				if(check_mamals[i][j].type==1)
					flag= check_mamals[i][j].isBlockedPlayer(check_mamals, i, j)	;
			}
		if(flag==false)//false = when there is forward, true = when not found 
			return 1;
		print_tie();
		return 0;
	}
	public static void print_tie() {

		System.out.println("Congratulations, user has won :)");
	}
	public static int calc_tie_computer(Mamals [][] check_mamals)
	{
		boolean flag=true;
		for (int i = 0; i < 8&&flag; i++)
			for (int j = 0; j < 8&& flag; j++)
			{	
				if(check_mamals[i][j].type==2)
					flag= check_mamals[i][j].isBlockedComputer(check_mamals, i, j);
			}
		if(flag==false)//false = when there is forward, true = when not found 
			return 1;//the game continues there is a legal move
		print_tie();
		return 0;
	}
	public static void check_Q_computer(Mamals [][] check_mamals) {
		for(int i=7;i<8;i++)
			for(int j=0;j<8;j++)
			{
				if(check_mamals[i][j].equals("E2"))
					check_mamals[i][j] = new QueenElephant ("E2Q",2);
				if(check_mamals[i][j].equals("M2"))
					check_mamals[i][j] = new QueenMouse ("M2Q",2);
			}
	}
	public static void check_Q_player(Mamals [][] check_mamals) {
		for(int i=0;i<1;i++)
			for(int j=0;j<8;j++)
			{
				if(check_mamals[i][j].equals("E1"))
					check_mamals[i][j] = new QueenElephant ("E1Q",1);
				if(check_mamals[i][j].equals("M1"))
					check_mamals[i][j] = new QueenMouse ("M1Q",1);
			}
	}
	public static boolean is_valid_string(String move) {
		int i_dest = 8 - (move.charAt(0) - '0');
		int j_dest = (move.charAt(1) - '0') - 1;
		int i_origin = 8 - (move.charAt(3) - '0');
		int j_origin = (move.charAt(4) - '0') - 1;
		
		if (i_dest > 7 || j_dest > 7 || i_origin > 7 || j_origin > 7)
			return false;
		if (i_dest < 0 || j_dest < 0 || i_origin < 0 || j_origin < 0)
			return false;
		if(!(check_mamals[i_origin][j_origin].type==1)) // only allowed to move player tools
			return false;
		if(!check_mamals[i_origin][j_origin].name.equals("C1")) //if you are not a cat, only allowed to move to "*"
			if(!check_mamals[i_dest][j_dest].name.equals("*"))
				return false;
		if(check_mamals[i_origin][j_origin].name.equals("C1"))  //if you are a cat, only allowed to move to "*" or "-"
			if(!check_mamals[i_dest][j_dest].name.equals("*") && !check_mamals[i_dest][j_dest].name.equals("-"))
				return false;		
		
		return true;
	}
	public static boolean is_valid_coordinate(int i, int j) {
		if (i > 7 || i < 0 || j > 7 || j < 0)
			return false;
		if(!(check_mamals[i][j].type==1)) // only allowed to move player tools
			return false;
		if(!check_mamals[i][j].name.equals("C1")) //if you are not a cat, only allowed to move to "*"
			if(!check_mamals[i][j].name.equals("*"))
				return false;
		if(check_mamals[i][j].name.equals("C1"))  //if you are a cat, only allowed to move to "*" or "-"
			if(!check_mamals[i][j].name.equals("*") && !check_mamals[i][j].name.equals("-"))
				return false;
		return true;
	}
	public static void double_food(Mamals [][] check_mamals,int i_origin,int j_origin,int i_dest,int j_dest)
	{
		boolean flag=true;
		while(flag) { // double food while it's possible
			
			String direction = check_mamals[i_origin][j_origin].double_food_player(i_origin, j_origin, check_mamals);
			if(direction.equals("U-R")) {
				i_dest-=2;
				j_dest+=2;
			}
			if(direction.equals("U-L")) {
				i_dest-=2;
				j_dest-=2;
			}
			if(direction.equals("D-L"))	{
				i_dest+=2;
				j_dest-=2;
			}
			if(direction.equals("D-R")) {
				i_dest+=2;
				j_dest+=2;
			}
			else
				flag =false;	
			i_origin=i_dest;//change the place after every eat move
			j_origin=j_dest;
		}
	}
	public static int RandomComputerTool() {
		int iRandom=0;
		int jRandom=0;
		boolean flag=false;
		while(!flag) {
			iRandom=(int)(Math.random()*8);
			jRandom=(int)(Math.random()*8);
			if(check_mamals[iRandom][jRandom].type==2)
				flag=true;
		}
		return iRandom*10+jRandom;
	}
}
