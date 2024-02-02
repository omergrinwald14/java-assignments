import Turtle.*;

public class HangoverTurtle extends DrunkTurtle
{
	public void moveForward(double distance) {
		double action=Math.random()*100;
		 if (action>=0 && action<30) { //act like drunk
			  super.moveForward(distance);
		  }
		 if (action>=30 && action<50) { //act like normal
			  super.move_NotDrunk(distance);
		  }	
		 //else, 50% of not doing anything
	}

}

