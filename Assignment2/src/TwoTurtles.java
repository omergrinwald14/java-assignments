import Turtle.*;

public class TwoTurtles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Turtle Sami = new Turtle();
		Turtle Sumo = new Turtle();
		//move to left Side
		Sami.tailUp();
		Sami.moveForward(100);
		Sami.turnLeft(90);
		Sami.moveForward(200);
		createLeftChair(Sami); //create left chair
		//move to right side
		Sami.turnRight(180);
		Sami.tailUp();
		Sami.moveForward(200);
		Sami.turnRight(90);
		Sami.moveForward(320);
		createRightChair(Sami); //create right chair
		Sami.hide();
		//move up to table drawing position
		Sumo.tailUp();
		Sumo.moveForward(90);
		createTable(Sumo); //create table
		Sumo.hide();
		
	}

	public static void createLeftChair(Turtle T) {		
		T.tailDown();
		T.turnLeft(90);
		T.moveForward(200);
		T.turnLeft(180);
		T.tailUp();
		T.moveForward(80);
		T.turnRight(90);
		T.tailDown();
		T.moveForward(80);
		T.turnRight(90);
		T.moveForward(80);
	}
	public static void createRightChair(Turtle T) {		
		T.tailDown();
		T.turnRight(90);
		T.moveForward(200);
		T.turnLeft(180);
		T.tailUp();
		T.moveForward(80);
		T.turnLeft(90);
		T.tailDown();
		T.moveForward(80);
		T.turnLeft(90);
		T.moveForward(80);
	}
	public static void createTable(Turtle T) {
		//left side
		T.turnLeft(90);
		T.tailDown();
		T.moveForward(120);
		T.turnRight(180);
		T.tailUp();
		T.moveForward(40);
		T.turnRight(90);
		T.tailDown();
		T.moveForward(190);
		//back to middle
		T.tailUp();
		T.turnRight(180);
		T.moveForward(190);
		T.turnRight(90);
		T.moveForward(80);
		//right side
		T.tailDown();
		T.moveForward(120);
		T.turnRight(180);
		T.tailUp();
		T.moveForward(40);
		T.turnLeft(90);
		T.tailDown();
		T.moveForward(190);
		
	}
}