package simulation;

import springies.Springies;
import jgame.JGColor;

public class Muscle extends Spring	{
	
	/* Mass objects connected by the Spring */
	Mass myMass1, myMass2;

	private double myRestLength;
	private double mySpringyness;
	private double myAmplitude;
	
	public Muscle(String m1, String m2, double restL, double K, 
			double amp) {
		super(m1, m2, restL, K);
		myMass1 = Springies.Masses.get(m1);
		myMass2 = Springies.Masses.get(m2);
		myRestLength = restL;
		mySpringyness = K;
		paintShape();
		myAmplitude = amp;
	}
	
	public void move() {

	}

	double radian = 0;
	@Override
	protected void paintShape() {
		radian += .1;
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
				myMass2.getMassX(), myMass2.getMassY());
		muscleForce();
	}

	public void muscleForce() {
		double dx = myMass2.getMassX() - myMass1.getMassX();
		double dy = myMass2.getMassY() - myMass1.getMassY();
		double dist = Math.sqrt(Math.pow(
				dx, 2) + Math.pow(dy, 2));
		//System.out.println("dist: " + dist);
		double sinForce = Math.sin(radian) * 250 * myAmplitude;
		double magnitude = (mySpringyness * (dist - myRestLength) * 70)+sinForce;
		double xComp = dx / dist * magnitude;
		double yComp = dy / dist * magnitude;
		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
	}
	
}
