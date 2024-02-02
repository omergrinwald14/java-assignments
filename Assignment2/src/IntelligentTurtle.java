import Turtle.*;

public class IntelligentTurtle extends Turtle {
	public void draw (int sides, double size) {    // draw a polygon in the given sides and size	
		int angle = 360/sides;
		for(int i=0;i<sides;i++) {
			this.moveForward(size);
			this.turnRight(angle);
		}
	}
}
