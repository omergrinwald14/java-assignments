import java.util.Scanner;

public class checkers {
    static Scanner sc = new Scanner(System.in);
    static String[][] newBoard = {{"-", "W", "-", "W", "-", "W", "-", "W"},
            {"W", "-", "W", "-", "W", "-", "W", "-"},
            {"-", "W", "-", "W", "-", "W", "-", "W"},
            {"*", "-", "*", "-", "*", "-", "*", "-"},
            {"-", "*", "-", "W", "-", "W", "-", "*"},
            {"R", "-", "R", "-", "R", "-", "*", "-"},
            {"-", "R", "-", "R", "-", "R", "-", "R"},
            {"R", "-", "R", "-", "R", "-", "R", "-"}};

    public static void main(String[] args) {
        boolean status_game = true;
        int status_client = 1;
        String[][] check_board = new String[8][8];

        System.out.println("Welcome to Famtma Checkers. To start the game press 1 to exit press 0");
        int choose = sc.nextInt();
        if (choose == 0)
            System.out.println("bye bye");
        if (choose == 1)
        //true if the game is continue,finish the game-win,tie,or give up - turn false
        {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    check_board[i][j] = newBoard[i][j];
            System.out.println("the board:");
            printBoard(newBoard);
        }
        while (status_game && status_client == 1) {
            status_client = client_turn(check_board);
            status_game = calc_client_winner(check_board);
            System.out.println("The board:");
            printBoard(check_board);
        }
        System.out.println("BYE BYE");
    }
    public static void printBoard(String[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "");
            }
            System.out.println();
        }
    }
    public static int client_turn(String[][] check_board) {
        System.out.println("It's your turn, please enter your move.");
        boolean flag = false;
        while (!flag) {
            String move = sc.next();
            if (move.equals("STOP")) {
                count_check(check_board);
                return 0;
            }
            if(is_valid_string(move)) {
                int i_dest = 8 - (move.charAt(0) - '0');
                int j_dest = (move.charAt(1) - '0') - 1;
                int i_origin = 8 - (move.charAt(3) - '0');
                int j_origin = (move.charAt(4) - '0') - 1;

                if (is_empty(move, check_board) && isRed(move, check_board)) {

                    if (is_food(move, check_board)) {
                        check_board[i_origin][j_origin] = "*";
                        check_board[i_dest][j_dest] = "R";
                        if (i_dest > i_origin) { //move down
                            if(j_dest>j_origin) //move down and right
                                check_board[i_origin + 1][j_origin + 1] = "*";
                            if(j_dest<j_origin) //move down and left
                                check_board[i_origin + 1][j_origin - 1] = "*";
                        }
                        if (i_dest < i_origin) { //move up
                            if(j_dest>j_origin) //move up and right
                                check_board[i_origin - 1][j_origin + 1] = "*";
                            if(j_dest<j_origin) //move up and left
                                check_board[i_origin - 1][j_origin - 1] = "*";
                        }

                        if(double_food_possible(i_dest,j_dest,check_board))
                            execute_double_food(check_board);
                        flag = true;
                    }
                    if (isfoward_client(move, check_board)) {
                        check_board[i_origin][j_origin] = "*";
                        check_board[i_dest][j_dest] = "R";
                        flag = true;
                    }
                }
            }
            if (!flag)
                System.out.println("The move is invaild, please enter new move");
        }
        return 1;
    }
    public static void computer_turn(String[][] check_board){
        int[] computer_tools = new int[12];
        int count = 0;
        for(int i=0; i<7; i++)
            for(int j=0; j<7; j++) {
                if (check_board[i][j].equals("W")) {
                    computer_tools[count] = i*10+j;
                    count++;
                }
                else{
                    computer_tools[count] = 0;
                    count++;
                }
            }
    }
    public static void execute_double_food(String[][] check_board) {
        boolean flag = true;
        while(flag) {
            System.out.println("you have another move possible, please enter again");
            System.out.println("the board:");
            printBoard(check_board);
            String move = sc.next();
            int i_dest = 8 - (move.charAt(0) - '0');
            int j_dest = (move.charAt(1) - '0') - 1;
            int i_origin = 8 - (move.charAt(3) - '0');
            int j_origin = (move.charAt(4) - '0') - 1;
            if(is_valid_string(move) && is_food(move, check_board)) { // check for valid move for food
                check_board[i_origin][j_origin] = "*";
                check_board[i_dest][j_dest] = "R";
                if (i_dest > i_origin) { //move down
                    if(j_dest>j_origin) //move down and right
                        check_board[i_origin + 1][j_origin + 1] = "*";
                    if(j_dest<j_origin) //move down and left
                        check_board[i_origin + 1][j_origin - 1] = "*";
                }
                if (i_dest < i_origin) { //move up
                    if(j_dest>j_origin) //move up and right
                        check_board[i_origin - 1][j_origin + 1] = "*";
                    if(j_dest<j_origin) //move up and left
                        check_board[i_origin - 1][j_origin - 1] = "*";
                }
            }
            if(!double_food_possible(i_dest,j_dest,check_board))
                flag = false;
        }
    }
    public static boolean isRed(String move, String[][] check_board) {
        int i = 8 - (move.charAt(3) - '0');
        int j = (move.charAt(4) - '0') - 1;
        if (check_board[i][j].equals("R"))
            return true;
        return false;
    }
    public static boolean isfoward_client(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if (i_origin == 0 || j_dest < 0 || j_dest > 7)
            return false;
        if (i_origin == i_dest + 1)
            if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
                return true;
        return false;
    }
    public static boolean is_empty(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        if (!check_board[i_dest][j_dest].equals("*"))
            return false;
        return true;
    }
    public static boolean is_food(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if (i_origin == i_dest + 2) {
            if (j_origin == j_dest - 2)//move right
                if (check_board[i_origin - 1][j_origin + 1].equals("W"))
                    return true;
            if (j_origin == j_dest + 2)//move left
                if (check_board[i_origin - 1][j_origin - 1].equals("W"))
                    return true;
        }
        return false;
    }
    public static boolean is_valid_string(String move) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;

        if(i_dest>7 || j_dest>7 || i_origin>7 || j_origin>7)
            return false;
        if(i_dest<0 || j_dest<0 || i_origin<0 || j_origin<0)
            return false;
        return true;
    }
    public static boolean is_valid_coordinate(int i,int j) {
        if(i>7||i<0||j>7||j<0)
            return false;
        return true;
    }
    public static void computer_winner() {
        System.out.println("Sorry, computer has won:(");
    }
    public static boolean calc_client_winner(String[][] check_board) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (check_board[i][j].equals("W"))
                    return true; //the game will continue to run
        client_winner();
        return false;
    }
    public static void client_winner() {

        System.out.println("Congratulations, user has won :)");
    }
    public static void count_check(String[][] check_board) { //work when a client gives up
        int count_red = 0;
        int count_white = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (check_board[i][j].equals("R"))
                    count_red++;
                if (check_board[i][j].equals("W"))
                    count_white++;
            }
        if (count_red > count_white)
            client_winner();
        else if (count_white > count_red)
            computer_winner();
        else {
            System.out.println("Sorry, it's a tie");
        }
    }
    public static boolean double_food_possible(int i, int j, String[][] check_board) {
        if(check_board[i][j]=="W") {
            if(is_valid_coordinate(i+2,j+2) && check_board[i+1][j+1].equals("R"))
                if(check_board[i+2][j+2].equals("*"))
                    return true;
            if(is_valid_coordinate(i+2,j-2) && check_board[i+1][j-1].equals("R"))
                if(check_board[i+2][j-2].equals("*"))
                    return true;
            if(is_valid_coordinate(i-2,j-2) && check_board[i-1][j-1].equals("R"))
                if(check_board[i-2][j-2].equals("*"))
                    return true;
            if(is_valid_coordinate(i-2,j+2) && check_board[i-1][j+1].equals("R"))
                if(check_board[i-2][j+2].equals("*"))
                    return true;
        }
        if(check_board[i][j]=="R") {
            if(is_valid_coordinate(i-2,j-2) && check_board[i-1][j-1].equals("W"))
                if(check_board[i-2][j-2].equals("*"))
                    return true;
            if(is_valid_coordinate(i-2,j+2) && check_board[i-1][j+1].equals("W"))
                if(check_board[i-2][j+2].equals("*"))
                    return true;
            if(is_valid_coordinate(i+2,j+2) && check_board[i+1][j+1].equals("W"))
                if(check_board[i+2][j+2].equals("*"))
                    return true;
            if(is_valid_coordinate(i+2,j-2) && check_board[i+1][j-1].equals("W"))
                if(check_board[i+2][j-2].equals("*"))
                    return true;
        }
        return false;
    }
/*
public static void computer_turn(string [][] borad
*/

}