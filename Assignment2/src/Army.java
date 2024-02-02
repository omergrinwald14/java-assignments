import Turtle.*;
import java.util.Scanner;
public class Army {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		Turtle[] army = new Turtle[5];
		int choose;
		for(int i=0;i<5;i++) {
			System.out.println("Choose the type of a turtle:\r\n"
					+ "1.	Simple\r\n"
					+ "2.	Intelligent\r\n"
					+ "3.	Drunk\r\n"
					+ "4.	Jumpy\r\n"
					+ "5.	Hangover\r\n"
					+ "6.	Burmese\r\n"
					+ "");
			choose = sc.nextInt();
			switch(choose) {
			case 1:
				army[i] = new Turtle();
				break;
			case 2:
				army[i] = new IntelligentTurtle();
				break;
			case 3:
				army[i] = new DrunkTurtle();
				break;
			case 4:
				army[i] = new JumpyTurtle();
				break;
			case 5:
				army[i] = new HangoverTurtle();
				break;
			case 6:	
				army[i] = new BurmeseTurtle();
				break;
			}
		}
		armyTailUp(army);
		lineUp(army);
		armyTailDown(army);
		armyMove(army, 65);
		armyTurnLeft(army, 40);
		armyMove(army, 75);
		armyDrawPolygon(army,6,40);
		armyHide(army);

	}
	public static void lineUp(Turtle[] army) {
		for(int i=0;i<army.length;i++) {
			if(army[i] instanceof DrunkTurtle) {
				((DrunkTurtle)army[i]).turnRightNormal(90);
				((DrunkTurtle)army[i]).move_NotDrunk(120*i);
			}
			if(army[i] instanceof HangoverTurtle) {
				((HangoverTurtle)army[i]).turnRightNormal(90);
				((HangoverTurtle)army[i]).move_NotDrunk(120*i);

			}
			army[i].turnRight(90);
			army[i].moveForward(120*i);
		}
	}
	public static void armyTailUp(Turtle[] army) {
		for(int i=0;i<army.length;i++) {
			army[i].tailUp();
		}
	}
	public static void armyTailDown(Turtle[] army) {
		for(int i=0;i<army.length;i++) {
			army[i].tailDown();
		}
	}
	public static void armyMove(Turtle[] army, double distance) {
		for(int i=0;i<army.length;i++) {
			army[i].moveForward(distance);
		}
	}
	public static void armyTurnLeft(Turtle[] army, int degree) {
		for(int i=0;i<army.length;i++) {
			army[i].turnLeft(degree);
		}
	}
	public static void armyDrawPolygon(Turtle[] army, int sides, double size) {
		for(int i=0;i<army.length;i++) {
			if(army[i] instanceof IntelligentTurtle) {
				((IntelligentTurtle)army[i]).draw(sides,size);
			}
		}
	}
	public static void armyHide(Turtle[] army) {
		for(int i=0;i<army.length;i++) {
			army[i].hide();
		}
	}
}
