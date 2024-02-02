import Turtle.*;

public class JumpyTurtle extends IntelligentTurtle {
	boolean isTailDown;
	public void tailDown() {
		super.tailDown();
		isTailDown = true;
	}
	public void tailUp() {
		super.tailDown();
		isTailDown = false;
	}
	public void moveForward(double distance) {
		if(isTailDown) {
			for(int i=0;i<distance/4;i++) {
				if(isTailDown) {
					super.moveForward(4);
					this.tailUp();
				}
				else {
					super.moveForward(4);
					this.tailDown();
				}	
			}
			this.tailDown();
			super.moveForward(distance%4);
		}
		else
			super.moveForward(distance);
	}
	public void draw (int sides, double size) {
		int angle = 360/sides;
		for(int i=0;i<sides;i++) {
			this.moveForward(size);
			this.turnRight(angle);
		}
	}
}

