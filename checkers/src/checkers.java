import java.util.Scanner;
import java.util.Random;

public class checkers {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static String[][] newBoard = {{"-", "", "-", "", "-", "*", "-", "W"},
            {"W", "-", "*", "-", "W", "-", "R", "-"},
            {"-", "W", "-", "R", "-", "W", "-", "W"},
            {"", "-", "R", "-", "", "-", "*", "-"},
            {"-", "Q-R", "-", "W", "-", "W", "-", "*"},
            {"W", "-", "W", "-", "", "-", "", "-"},
            {"-", "R", "-", "*", "-", "R", "-", "R"},
            {"R", "-", "", "-", "", "-", "R", "-"}};
    public static void main(String[] args) {



        boolean status_game = true;
        int status_client = 1;
        String[][] check_board = new String[8][8];

        System.out.println("Welcome to Famtma Checkers. To start the game press 1 to exit press 0");
        int choose = sc.nextInt();
        if (choose == 0)
        {
            System.out.println("bye bye");
            status_client=0;
            status_game=false;
        }
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
            check_Q_computer(check_board);
            status_game = calc_client_winner(check_board);
            System.out.println("The board:");
            printBoard(check_board);
            status_game=computer_turn(check_board);
            check_Q_client(check_board);
            status_game=calc_computer_winner(check_board);
            status_game=calc_tie_computer(check_board);
            status_game=calc_tie_player(check_board);


            System.out.println("The board:");
            printBoard(check_board);
        }
        System.out.println("BYE BYE");

    }

    public static boolean computer_turn(String[][] check_board) {
        int index_i = 0;
        int index_j = 0;
        String index_direction = "";
        boolean flag = false;//its false when there is nothing to eat
        int i_double = 0;
        int j_double = 0;
        //check if there is any white who can eat
        for (int i = 0; i < 6 && !flag; i++)
            for (int j = 2; j < 6 && !flag; j++) {
                if (check_board[i][j].equals("W")) {
                    flag = is_food_first_computer(i+1, j+1, check_board, "right");
                    if (flag) {
                        index_i = i;
                        index_j = j;
                        index_direction = "right";
                    }
                    flag = is_food_first_computer(i+1, j-1, check_board, "left");
                    if (flag) {
                        index_i = i;
                        index_j = j;
                        index_direction = "left";
                    }
                }
            }
        for (int i = 0; i < 6 && !flag; i++)
            for (int j = 0; j < 2 && !flag; j++)//can eat right only- right side of the board
                if (check_board[i][j].equals("W")) {
                    flag = is_food_first_computer(i+1, j+1, check_board, "right");
                    if (flag) {
                        index_i = i;
                        index_j = j;
                        index_direction = "right";
                    }
                }
        for (int i = 0; i < 6 && !flag; i++)
            for (int j = 6; j < 8 && !flag; j++)//can eat left side only- left side of the board
                if (check_board[i][j].equals("W")) {
                    flag = is_food_first_computer(i+1, j-1, check_board, "left");
                    if (flag) {
                        index_i = i;
                        index_j = j;
                        index_direction = "left";
                    }
                }
        for (int i = 0; i < 8 && !flag; i++)
            for (int j = 0; j < 8 && !flag; j++) {
                if(is_food_queen_computer(i, j, check_board, "right-down")) {

                    index_i = i;
                    index_j = j;
                    i_double = i+2;
                    j_double = j+2;
                    index_direction = "right-down";
                    break;
                }
                if(is_food_queen_computer(i, j, check_board, "right-up"))	{

                    index_i = i;
                    index_j = j;
                    i_double = i-2;
                    j_double = j+2;
                    index_direction = "right-up";
                    break;
                }
                if(is_food_queen_computer(i, j, check_board, "left-forward")) {

                    index_i = i;
                    index_j = j;
                    i_double = i+2;
                    j_double = j-2;
                    index_direction = "left-forward";
                    break;
                }
                if(is_food_queen_computer(i, j, check_board, "left-up")) {
                    index_i = i;
                    index_j = j;
                    i_double = i-2;
                    j_double = j-2;
                    index_direction = "left-up";
                    break;
                }
            }

        //finish check if there is "Eat" move
        //if there is eat = do it according to the  i,j,direction when is not quenn
        if (index_direction.equals("right")) { // also indicates that 'flag' is true
            check_board[index_i][index_j] = "*";
            check_board[index_i+1][index_j+1] = "*";
            check_board[index_i+2][index_j+2] = "W";
            i_double = index_i+2;
            j_double = index_j+2;
        }
        if (index_direction.equals("left")) { // also indicates that 'flag' is true
            check_board[index_i][index_j] = "*";
            check_board[index_i+1][index_j-1] = "*";
            check_board[index_i+2][index_j-2] = "W";
            i_double = index_i+2;
            j_double = index_j-2;
        }
        //check more to eat
        while(index_direction != "" && double_food_possible(i_double,j_double,check_board)){
            if (is_valid_coordinate(i_double - 2, j_double - 2) && check_board[i_double - 1][j_double - 1].equals("R"))//right down direction
                if (check_board[i_double - 2][j_double - 2].equals("*")){ //if true - execute double eat
                    check_board[i_double][j_double] = "*";
                    check_board[i_double - 1][j_double - 1] = "*";
                    if(check_board[i_double][j_double].equals("W"))
                        check_board[i_double - 2][j_double - 2] = "W";
                    if(check_board[i_double][j_double].equals("Q-W"))
                        check_board[i_double - 2][j_double - 2] = "Q-W";
                    i_double = i_double - 2;
                    j_double = j_double - 2;
                    continue;
                }
            if (is_valid_coordinate(i_double - 2, j_double + 2) && check_board[i_double - 1][j_double + 1].equals("R"))//left down direction
                if (check_board[i_double - 2][j_double + 2].equals("*")){
                    check_board[i_double][j_double] = "*";
                    check_board[i_double - 1][j_double + 1] = "*";
                    if(check_board[i_double][j_double].equals("W"))
                        check_board[i_double - 2][j_double + 2] = "W";
                    if(check_board[i_double][j_double].equals("Q-W"))
                        check_board[i_double - 2][j_double + 2] = "Q-W";
                    i_double = i_double - 2;
                    j_double = j_double + 2;
                    continue;
                }
            if (is_valid_coordinate(i_double + 2, j_double + 2) && check_board[i_double + 1][j_double + 1].equals("R"))//right and left direction
                if (check_board[i_double + 2][j_double + 2].equals("*")){
                    check_board[i_double][j_double] = "*";
                    check_board[i_double + 1][j_double + 1] = "*";
                    if(check_board[i_double][j_double].equals("W"))
                        check_board[i_double + 2][j_double + 2] = "W";
                    if(check_board[i_double][j_double].equals("Q-W"))
                        check_board[i_double + 2][j_double + 2] = "Q-W";
                    i_double = i_double + 2;
                    j_double = j_double + 2;
                    continue;
                }
            if (is_valid_coordinate(i_double + 2, j_double - 2) && check_board[i_double + 1][j_double - 1].equals("R"))
                if (check_board[i_double + 2][j_double - 2].equals("*")){
                    check_board[i_double][j_double] = "*";
                    check_board[i_double + 1][j_double - 1] = "*";
                    if(check_board[i_double][j_double].equals("W"))
                        check_board[i_double + 2][j_double - 2] = "W";
                    if(check_board[i_double][j_double].equals("Q-W"))
                        check_board[i_double + 2][j_double - 2] = "Q-W";
                    i_double = i_double + 2;
                    j_double = j_double - 2;
                    continue;
                }
        }
        if(!flag) { //nothing to eat do a random move with regular tool
            int[] computer_tools = new int[12]; //calculate how many tools left in the game
            int count = 0;
            for (int i = 0; i < 7; i++)
                for (int j = 0; j < 7; j++) {
                    if (check_board[i][j].equals("W")||check_board[i][j].equals("Q-W")) {
                        computer_tools[count] = i * 10 + j;
                        count++;
                    }
                }
            String next_forward=random_forward(computer_tools,check_board);
            if(!next_forward.isEmpty()) {
                flag=true;
                return flag;
            }
        }

        //if (flag==false)
        //tie_stage();
        flag=calc_tie_computer(check_board);
        return flag;
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
            if (is_valid_string(move)) {
                int i_dest = 8 - (move.charAt(0) - '0');
                int j_dest = (move.charAt(1) - '0') - 1;
                int i_origin = 8 - (move.charAt(3) - '0');
                int j_origin = (move.charAt(4) - '0') - 1;

                if (is_empty(move, check_board) && isQR(move, check_board)) {//if its a queen
                    if (is_food_client_QR(move, check_board)) {
                        check_board[i_origin][j_origin] = "*";//change the board
                        check_board[i_dest][j_dest] = "Q-R";
                        if (i_dest > i_origin) { //move down, eat the white
                            if (j_dest > j_origin) //move down and right
                                check_board[i_origin + 1][j_origin + 1] = "*";
                            if (j_dest < j_origin) //move down and left
                                check_board[i_origin + 1][j_origin - 1] = "*";
                        }
                        if (i_dest < i_origin) { //move up
                            if (j_dest > j_origin) //move up and right
                                check_board[i_origin - 1][j_origin + 1] = "*";
                            if (j_dest < j_origin) //move up and left
                                check_board[i_origin - 1][j_origin - 1] = "*";
                        }

                        flag = true;
                        if (double_food_possible(i_dest, j_dest, check_board) && flag)
                            execute_double_food(check_board);
                    }
                        if (isfoward_client_QR(move, check_board) && !flag) {
                            check_board[i_origin][j_origin] = "*";
                            check_board[i_dest][j_dest] = "Q-R";
                            flag = true;
                        }
                    
                }
                if (is_empty(move, check_board) && isRed(move, check_board) && !flag) {
                    if (is_food_client(move, check_board)) {
                        check_board[i_origin][j_origin] = "*";
                        check_board[i_dest][j_dest] = "R";
                        if (i_dest < i_origin) { //move up
                            if (j_dest > j_origin) //move up and right
                                check_board[i_origin - 1][j_origin + 1] = "*";
                            if (j_dest < j_origin) //move up and left
                                check_board[i_origin - 1][j_origin - 1] = "*";
                        }

                        flag = true;
                        if (double_food_possible(i_dest, j_dest, check_board) && flag)
                            execute_double_food(check_board);
                    }
                    if (isfoward_client(move, check_board) && !flag) {
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


    public static void printBoard(String[][] check_board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(check_board[i][j] + "");
            }
            System.out.println();
        }
    }
    public static void print_tie() {

        System.out.println("Congratulations, user has won :)");
    }

    public static void execute_double_food(String[][] check_board) {
        boolean flag = true;
        while (flag) {
            System.out.println("you have another move possible, please enter again");
            System.out.println("the board:");
            printBoard(check_board);
            String move = sc.next();
            int i_dest = 8 - (move.charAt(0) - '0');
            int j_dest = (move.charAt(1) - '0') - 1;
            int i_origin = 8 - (move.charAt(3) - '0');
            int j_origin = (move.charAt(4) - '0') - 1;
            if (is_valid_string(move) && is_food_client(move, check_board)) { // check for valid move for food
                if(check_board[i_origin][j_origin].equals("R")) {
                    check_board[i_origin][j_origin] = "*";
                    check_board[i_dest][j_dest] = "R";
                }
                if(check_board[i_origin][j_origin].equals("Q-R")) {
                    check_board[i_origin][j_origin] = "*";
                    check_board[i_dest][j_dest] = "Q-R";
                }
                if (i_dest > i_origin) { //move down
                    if (j_dest > j_origin) //move down and right
                        check_board[i_origin + 1][j_origin + 1] = "*";
                    if (j_dest < j_origin) //move down and left
                        check_board[i_origin + 1][j_origin - 1] = "*";
                }
                if (i_dest < i_origin) { //move up
                    if (j_dest > j_origin) //move up and right
                        check_board[i_origin - 1][j_origin + 1] = "*";
                    if (j_dest < j_origin) //move up and left
                        check_board[i_origin - 1][j_origin - 1] = "*";
                }
            }
            if (!double_food_possible(i_dest, j_dest, check_board))
                flag = false;
        }
    }
    public static boolean isQR(String move, String[][] check_board) {
        int i = 8 - (move.charAt(3) - '0');
        int j = (move.charAt(4) - '0') - 1;
        if (check_board[i][j].equals("Q-R"))
            return true;
        return false;
    }
    public static boolean isRed(String move, String[][] check_board) {
        int i = 8 - (move.charAt(3) - '0');
        int j = (move.charAt(4) - '0') - 1;
        if (check_board[i][j].equals("R"))
            return true;
        return false;
    }
    public static boolean isfoward_client_QR(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if(is_valid_coordinate(i_origin, j_origin) && is_valid_coordinate(i_dest, j_dest))
            if (i_origin == i_dest + 1 || i_origin == i_dest - 1)
                if (j_origin == j_dest + 1 || j_origin == j_dest - 1)
                    return true;
        return false;
    }
    public static boolean isfoward_client(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if (i_origin <= 0 || j_dest < 0 || j_dest > 7)
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
    public static boolean is_food_client(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if (i_origin == i_dest + 2) { //move up
            if (j_origin == j_dest - 2)//move up and right
                if (check_board[i_origin - 1][j_origin + 1].equals("W"))
                    return true;
            if (j_origin == j_dest + 2)//move up and left
                if (check_board[i_origin - 1][j_origin - 1].equals("W"))
                    return true;
        }
        return false;
    }
    public static boolean is_food_client_QR(String move, String[][] check_board) {
        int i_dest = 8 - (move.charAt(0) - '0');
        int j_dest = (move.charAt(1) - '0') - 1;
        int i_origin = 8 - (move.charAt(3) - '0');
        int j_origin = (move.charAt(4) - '0') - 1;
        if (i_origin == i_dest + 2) { //move up
            if (j_origin == j_dest - 2)//move up and right
                if (check_board[i_origin - 1][j_origin + 1].equals("W"))
                    return true;
            if (j_origin == j_dest + 2)//move up and left
                if (check_board[i_origin - 1][j_origin - 1].equals("W"))
                    return true;
        }
        if (i_origin == i_dest - 2) { //move down
            if (j_origin == j_dest - 2)//move down and right
                if (check_board[i_origin + 1][j_origin + 1].equals("W"))
                    return true;
            if (j_origin == j_dest + 2)//move down and left
                if (check_board[i_origin + 1][j_origin - 1].equals("W"))
                    return true;
        }
        return false;
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
    public static boolean is_valid_coordinate(int i, int j) {
        if (i > 7 || i < 0 || j > 7 || j < 0)
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
        if (check_board[i][j] == "W") {
            if (is_valid_coordinate(i+2, j+2) && check_board[i+1][j+1].equals("R"))
                if (check_board[i+2][j+2].equals("*"))
                    return true;
            if (is_valid_coordinate(i+2, j-2) && check_board[i+1][j-1].equals("R"))
                if (check_board[i+2][j-2].equals("*"))
                    return true;
            if (is_valid_coordinate(i-2, j-2) && check_board[i-1][j-1].equals("R"))
                if (check_board[i-2][j-2].equals("*"))
                    return true;
            if (is_valid_coordinate(i-2, j+2) && check_board[i-1][j+1].equals("R"))
                if (check_board[i-2][j+2].equals("*"))
                    return true;
        }
        if (check_board[i][j] == "R") {
            if (is_valid_coordinate(i-2, j-2) && check_board[i-1][j-1].equals("W"))
                if (check_board[i-2][j-2].equals("*"))
                    return true;
            if (is_valid_coordinate(i-2, j+2) && check_board[i-1][j+1].equals("W"))
                if (check_board[i-2][j+2].equals("*"))
                    return true;
            if (is_valid_coordinate(i+2, j+2) && check_board[i+1][j+1].equals("W"))
                if (check_board[i+2][j+2].equals("*"))
                    return true;
            if (is_valid_coordinate(i+2, j-2) && check_board[i+1][j-1].equals("W"))
                if (check_board[i+2][j-2].equals("*"))
                    return true;
        }
        return false;
    }
    public static String random_forward (int[] computer_tools, String [][] check_board) {
        int random_tool=0;
        boolean flag = false;
        int loc_i, loc_j;
        String direction="";
        int []count_random=new int[12];
        int count=0;

        while(!flag)//its false until we find a move forward
        {
            random_tool = rand.nextInt(12);
            while (computer_tools[random_tool] == 0)
            {
                random_tool = rand.nextInt(12);
            }
            count_random[random_tool]++;
            loc_i = computer_tools[random_tool] / 10;
            loc_j = computer_tools[random_tool] % 10;
            if(check_board[loc_i][loc_j].equals("W"))
                direction = W_forward(loc_i, loc_j, check_board);
            if(check_board[loc_i][loc_j].equals("Q-W"))
                direction = QW_forward(loc_i, loc_j, check_board);

            if (direction.equals("right")||direction.equals("left")) {
                check_board[loc_i][loc_j] = "*";
                if (direction.equals("right"))
                    check_board[loc_i+1][loc_j+1] = "W";
                if (direction.equals("left"))
                    check_board[loc_i+1][loc_j-1] = "W";
                flag = true;
            }
            if (direction.equals("right-up")||direction.equals("left-up")||direction.equals("left-down")||direction.equals("right-down")) {
                check_board[loc_i][loc_j] = "*";
                if(direction.equals("right-up"))
                    check_board[loc_i-1][loc_j+1] = "Q-W";
                if(direction.equals("right-down"))
                    check_board[loc_i+1][loc_j+1] = "Q-W";
                if(direction.equals("left-up"))
                    check_board[loc_i-1][loc_j-1] = "Q-W";
                if(direction.equals("left-down"))
                    check_board[loc_i+1][loc_j-1] = "Q-W";
                flag = true;
            }

            if(direction.equals(""))
            {
                computer_tools[random_tool]=0;
                for (int i=0;i<12;i++)
                {
                    if(computer_tools[i]!=0)
                        count++;
                }
                if(count!=12)
                    count=0;
                else
                    flag=true; //no forward
            }
        }
        return direction;

    }
    public static String W_forward (int loc_i,int loc_j,String[][]check_board)
    {
        int direction;
        if  (loc_j == 0 )
            if(check_board[loc_i+1][loc_j+1].equals("*"))
                return "right";
            else return "";

        if  (loc_j >= 1|| loc_j <=6)//both direction
        {
            if (check_board[loc_i+1][loc_j+1].equals("") && check_board[loc_i+1][loc_j-1].equals("")) {

                direction = rand.nextInt(1,11);//1-3 right, 4-10 left
                if (direction > 0 && direction < 4)
                    return "right";
                else return "left";
            }


        }
        if ( loc_j == 7) { //left
            if (check_board[loc_i+1][loc_j-1].equals("*"))
                return "left";
            else return "";
        }

        return "";
    }
    public static String QW_forward(int loc_i,int loc_j,String[][]check_board){
        int direction = rand.nextInt(1,5);
        if(direction==1 && is_valid_coordinate(loc_i-1,loc_j+1))
            return "right-up";
        if(direction==2 && is_valid_coordinate(loc_i+1,loc_j+1))
            return "right-down";
        if(direction==3 && is_valid_coordinate(loc_i-1,loc_j-1))
            return "left-up";
        if(direction==4 && is_valid_coordinate(loc_i+1,loc_j-1))
            return "left-down";
        return "";
    }
    public static boolean is_food_first_computer ( int i, int j, String[][] check_board, String direction)
    {

        if (check_board[i][j].equals("R") && direction.equals("right"))//move right
            if (check_board[i+1][j+1].equals("*"))
                return true;

        if (check_board[i][j].equals("R") && direction.equals("left")) //move left
            if (check_board[i+1][j-1].equals("*"))
                return true;

        return false;
    }
    public static boolean calc_computer_winner(String[][] check_board) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (check_board[i][j].equals("R"))
                    return true; //the game will continue to run
        computer_winner();
        return false;

    }
    public static boolean calc_tie_player(String[][] check_board)
    {
        for (int i=0;i<7;i++)
            for(int j=0;j<7;j++) {
                if (j != 0 && j != 6) {
                    if (check_board[i][j].equals("W"))
                        if (check_board[i+1][j-1].equals("") || check_board[i+1][j+1].equals(""))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;

                }
                if (j == 0) {
                    if (check_board[i][j].equals("W"))
                        if (check_board[i+1][j+1].equals("*"))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;
                }
                if (j == 6) {
                    if (check_board[i+1][j-1].equals("W"))
                        if (check_board[i+1][j+1].equals("*"))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;
                }
            }
        print_tie();
        return false;//false says that there in a tie
    }
    public static boolean calc_tie_computer(String[][] check_board)
    {
        for (int i=7;i>0;i--)
            for(int j=0;j<7;j++) {

                if (j != 0 && j != 6 && i!=7) {
                    if (check_board[i][j].equals("W"))
                        if (check_board[i+1][j-1].equals("") || check_board[i+1][j+1].equals(""))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;

                }
                if (j == 0) {
                    if (check_board[i][j].equals("W"))
                        if (check_board[i+1][j+1].equals("*"))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;
                }
                if (j == 6) {
                    if (check_board[i][j-1].equals("W"))
                        if (check_board[i+1][j+1].equals("*"))
                            return true;
                    if (double_food_possible(i, j, check_board))
                        return true;
                }
            }

        print_tie();
        return false; //false says that there IS a tie
    }

    //queen methods
    public static void check_Q_computer(String[][] check_board) {
        for(int i=7;i<8;i++)
            for(int j=0;j<8;j++)
                if(check_board[i][j].equals("W"))
                    check_board[i][j] = "Q-W";
    }
    public static void check_Q_client(String[][] check_board) {
        for(int i=0;i<1;i++)
            for(int j=0;j<8;j++)
                if(check_board[i][j].equals("R"))
                    check_board[i][j] = "Q-R";
    }
    public static boolean is_food_queen_computer(int i, int j, String[][] check_board, String direction) {//original input . SeNt the 2 for loop on rows and col

        if (is_valid_coordinate(i+2,j+2) && check_board[i][j].equals("Q-W") && direction.equals("right-down"))//move right
        {
            if (check_board[i+1][j+1].equals("R"))
                if (check_board[i+2][j+2].equals("*")) {
                    check_board[i][j] = "*";
                    check_board[i+1][j+1] = "*";
                    check_board[i+2][j+2] = "Q-W";
                    return true;
                }
        }

        if (is_valid_coordinate(i-2,j+2) && check_board[i][j].equals("Q-W") && direction.equals("right-up"))//move right
        {
            if (check_board[i-1][j+1].equals("R"))
                if (check_board[i-2][j+2].equals("*")) {
                    check_board[i][j] = "*";
                    check_board[i-1][j+1] = "*";
                    check_board[i-2][j+2] = "Q-W";
                    return true;
                }
        }

        if (is_valid_coordinate(i+2,j-2) && check_board[i][j].equals("Q-W") && direction.equals("left-forward"))//move left
        {
            if (check_board[i+1][j-1].equals("R"))
                if (check_board[i+2][j-2].equals("*")) {
                    check_board[i][j] = "*";
                    check_board[i+1][j-1] = "*";
                    check_board[i+2][j-2] = "Q-W";
                    return true;
                }
        }

        if (is_valid_coordinate(i-2,j-2) && check_board[i][j].equals("Q-W") && direction.equals("left-up")) {//move left
            if (check_board[i-1][j-1].equals("R"))
                if (check_board[i-2][j-2].equals("*")) {
                    check_board[i][j] = "*";
                    check_board[i-1][j-1] = "*";
                    check_board[i-2][j-2] = "Q-W";
                    return true;
                }
        }
        return false; // if there is'nt any option to eat with queen
    }

}
