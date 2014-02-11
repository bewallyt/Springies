package simulation;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject {
	/* Mass objects connected by the Spring */

	private Mass myMass1, myMass2;
	private double myRestLength;
	private double mySpringyness;

	/* Spring constructor with springyness */
	public Spring(Mass m1, Mass m2, Double restL, Double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = K;

	}

	public void move() {

	}

	@Override
	protected void paintShape() {
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
				myMass2.getMassX(), myMass2.getMassY());
		springForce();
	}

	public void springForce() {

		double dx = myMass2.getMassX() - myMass1.getMassX();
		double dy = myMass2.getMassY() - myMass1.getMassY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double magnitude = mySpringyness * (dist - myRestLength) * 1500;
		//double magnitude = mySpringyness * (dist - myRestLength) * 50;
		double xComp = dx / dist * magnitude;
		double yComp = dy / dist * magnitude;

		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
		
	}

}
