import Turtle.*;

public class DrunkTurtle extends Turtle{
	public void moveForward(double distance) {
		double d1 = Math.random()*distance;
		super.moveForward(d1);
		int p = (int)Math.random()*100;
		
		if(p>=0 && p<45) 
			turnRight(90);
		if(p>=0 && p<35) {
			double d2 = Math.random()*distance;
			super.moveBackward(d2);
		}
		else {
			double d2 = Math.random()*distance;
			super.moveForward(d2);
		}	
		
	}
	public void turnRight(int degrees) {
		int d = (int)(Math.random()*1.5*degrees);
		super.turnRight(d);
	}
	public void turnLeft(int degrees) {
		int d = (int)(Math.random()*1.5*degrees);
		super.turnLeft(d);
	}
	 public void move_notdrunk(int x)
	 {
		 super.moveforward(x);
	 }
}
