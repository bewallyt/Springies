package simulation;

import springies.Springies;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject {

	/* Mass objects connected by the Spring */
	Mass myMass1, myMass2;
	private double x1, y1, x2, y2;

	private double myRestLength;
	private double mySpringyness;

	/* Spring constructor with springyness */
	public Spring(String m1, String m2, Double restL, Double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = Springies.Masses.get(m1);
		myMass2 = Springies.Masses.get(m2);
//		x1 = Springies.getMasses().get(myMass1).x;
//		y1 = Springies.getMasses().get(myMass1).y;
//		x2 = Springies.getMasses().get(myMass2).x;
//		y2 = Springies.getMasses().get(myMass2).y;
		myRestLength = restL;
		mySpringyness = K;
		paintShape();

	}

	public void move() {

	}

	@Override
	protected void paintShape() {
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(), myMass2.getMassX(), myMass2.getMassY());

	}

}
