import Turtle.*;

public class BurmeseTurtle extends IntelligentTurtle{
	public void draw (int sides, double size) {
		double action = Math.random()*100;
		if(action>=0 && action<50) {
			super.draw(sides-1, size);
			this.hide();
		}
		else {
			super.draw(sides, 18);
		}
	}
}
