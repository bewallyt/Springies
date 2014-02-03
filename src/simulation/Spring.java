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
		myRestLength = restL;
		mySpringyness = K;
		paintShape();
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
		double dist = Math.sqrt(Math.pow(
				dx, 2) + Math.pow(dy, 2));
		System.out.println("dist: " + dist);
		double magnitude = mySpringyness * (dist - myRestLength) * 75000;
		//double angle = Math.atan(dy / dx);
		double xComp = dx / dist * magnitude; //Math.cos(angle) * magnitude;
		double yComp = dy / dist * magnitude;//Math.sin(angle) * magnitude;
		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
	}



}
