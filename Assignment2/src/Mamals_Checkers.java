import java.util.Scanner;

public class Mamals_Checkers {
	static Scanner sc = new Scanner(System.in);
	static Mamals[][] check_mamals = new Mamals[8][8];
	static int status_game = 0;
	static int status_player = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Fatma Checkers. To start the game press 1, to exit press 0:");
		int choose = sc.nextInt();
		if (choose == 0)
			endGame();
		if(choose == 1)
			startGame();
		while (status_game==1 && status_player == 1) {
		}
		int status_game=1;
		int status_player=1;
		while (status_game==1 && status_player==1){
			status_player =	player_turn(check_mamals);
			status_game = check_player_winner();
		}


	}
	private static int check_player_winner() {
		return 0;
	}
	public static void setNewBoard(Mamals[][] check_mamals) {
		for(int i=0;i<check_mamals.length;i++) {
			for(int j=0;j<check_mamals[0].length;j++) {
				if(i%2==0 && j%2 == 0)
					check_mamals[i][j] = new Mamals("-",0);	
				if(i%2==0 && j%2!=0)
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
			check_mamals[5][8] = new Dog("D1",1);
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
		System.out.println("The board:");
		setNewBoard(check_mamals);
		printBoard(check_mamals);
		status_player=1;
		status_game=1;
	}
	public static void printBoard(Mamals[][] check_mamals) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(check_mamals[i][j].toString() + "");
			}
			System.out.println();
		}
	}
	public static boolean is_valid_coordinate(int i, int j) {
		if (i > 7 || i < 0 || j > 7 || j < 0)
			return false;
		return true;
	}
	public static int player_turn(Mamals[][] check_mamals) {
		boolean flag1 = true;
		while(flag1) {
			System.out.println("It's your turn, please enter your move.");
			String move = sc.next();
			if (move.equals("STOP")) {
				computer_winner();
				return 0;
			}		
			while(!is_valid_string(move)){
				System.out.println("invalid input, try again");
				move = sc.next();
			}
			int i_dest = 8 - (move.charAt(0) - '0');
			int j_dest = (move.charAt(1) - '0') - 1;
			int i_origin = 8 - (move.charAt(3) - '0');
			int j_origin = (move.charAt(4) - '0') - 1;
			if(check_mamals[i_origin][j_origin].first_food_player(i_dest,j_dest,i_origin,j_origin,check_mamals)) {
				boolean flag=true;
				while(flag) { // double food while it's possible
					i_origin=i_dest;
					j_origin=i_dest;
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
					else{
						flag =false;
					}	
				}
				flag1 = false;
				return 1;

			}
			if(check_mamals[i_origin][j_origin].forward_player(i_dest, j_dest, i_origin, j_origin, check_mamals))
			{
				flag1=false;
				return 1;
			}
		}
		System.out.println("invalid input");
		return 1;

	}
	public static void player_winner() {

		System.out.println("Congratulations, user has won :)");
	}
	public static void computer_winner() {
		System.out.println("Sorry, computer has won:(");
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
		return true;
	}

}