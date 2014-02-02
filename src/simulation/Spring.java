package simulation;

import springies.Springies;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject {

	/* Mass objects connected by the Spring */
	String myMass1, myMass2;
	private double x1, y1, x2, y2;

	private double myRestLength;
	private double mySpringyness;

	/* Spring constructor with springyness */
	public Spring(String m1, String m2, Double restL, Double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		x1 = Springies.getMasses().get(myMass1).x;
		y1 = Springies.getMasses().get(myMass1).y;
		x2 = Springies.getMasses().get(myMass2).x;
		y2 = Springies.getMasses().get(myMass2).y;
		myRestLength = restL;
		mySpringyness = K;
		paintShape();

	}

	public void move() {
		double width = Math.abs(x1 - x2);
		double height = Math.abs(y1 - y2);
		double xCorner = Math.min(x1, x2);
		double yCorner = Math.min(y1, y2);
		if (myEngine.getMouseButton(1)
				&& (myEngine.getMouseX() >= xCorner && myEngine.getMouseX() <= xCorner
						+ width)
				&& (myEngine.getMouseY() >= yCorner && myEngine.getMouseY() <= yCorner
						+ height)) {
			x = myEngine.getMouseX();
			y = myEngine.getMouseY();
		}
	}

	@Override
	protected void paintShape() {
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(x1, y1, x2, y2);

	}

}
