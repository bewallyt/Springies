package simulation;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject {
	/* Mass objects connected by the Spring */

	private Mass myMass1, myMass2;
	private double myRestLength;
	private double mySpringyness;
	private double dx;
	private double dy;
	private double dist;
	private double magnitude;
	private double xComp;
	private double yComp;

	private int clear = 0;

	/* Spring constructor with springyness */
	public Spring(Mass m1, Mass m2, Double restL, Double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = K;
	}

	@Override
	protected void paintShape() {
		if (clear != 1) {
			if (dist == myRestLength) {
				myEngine.setColor(JGColor.green);
				myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
						myMass2.getMassX(), myMass2.getMassY());
				springForce();
			}
			else if(dist > myRestLength){
				myEngine.setColor(JGColor.orange);
				myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
						myMass2.getMassX(), myMass2.getMassY());
				springForce();
			}
			else if(dist < myRestLength){
				myEngine.setColor(JGColor.yellow);
				myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
						myMass2.getMassX(), myMass2.getMassY());
				springForce();
			}
		}

	}

	public void springForce() {

<<<<<<< HEAD
		dx = myMass2.getMassX() - myMass1.getMassX();
		dy = myMass2.getMassY() - myMass1.getMassY();
		dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		magnitude = mySpringyness * (dist - myRestLength) * 1800;

		xComp = dx / dist * magnitude;
		yComp = dy / dist * magnitude;

=======
		double dx = myMass2.getMassX() - myMass1.getMassX();
		double dy = myMass2.getMassY() - myMass1.getMassY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double magnitude = mySpringyness * (dist - myRestLength); //Daintywalker
		//double magnitude = mySpringyness * (dist - myRestLength) * 1800; //Ball
		double xComp = dx / dist * magnitude;
		double yComp = dy / dist * magnitude;

>>>>>>> 77e876985b0248f153a80be14a2415126c75727f
		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
	}

	public String toString() {
		return myMass1 + " " + myMass2 + " rl:" + myRestLength + " K:"
				+ mySpringyness;
	}

	public void terminate() {
		clear = 1;
	}

}
