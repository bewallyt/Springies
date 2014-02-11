package simulation;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Muscle extends PhysicalObject {

	/* Mass objects connected by the Spring */
	private Mass myMass1, myMass2;
	private double myRestLength;
	private double mySpringyness;
	private double myAmplitude;
	private double radian = 0;
	private int clear = 0;
	private double dx;
	private double dy;
	private double dist;
	private double magnitude;
	private double xComp;
	private double yComp;
	JGColor color;
	JGColor colorExpand;
	JGColor colorContract;
	
	public Muscle(Mass m1, Mass m2, double restL, double K, double amp) {
		//super(m1, m2, restL, K);
		super("muscle", 0, JGColor.orange);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = K;
		myAmplitude = amp;
		color = JGColor.orange;
		colorExpand = JGColor.yellow;
		colorContract = JGColor.red;
	}

	@Override
	protected void paintShape() {
		if (clear != 1) {
			if (dist == myRestLength) {
				connectingLine(color);
			}
			else if(dist > myRestLength){
				connectingLine(colorExpand);
			}
			else if(dist < myRestLength){
				connectingLine(colorContract);
			}
		}
	}
	
	protected void connectingLine(JGColor c)	{
		myEngine.setColor(c);
		myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(),
				myMass2.getMassX(), myMass2.getMassY());
		muscleForce();
	}

	public void muscleForce() {
		radian += .1;
		double tempRestLength = myRestLength + myAmplitude*Math.sin(radian)*5;
		dx = myMass2.getMassX() - myMass1.getMassX();
		dy = myMass2.getMassY() - myMass1.getMassY();
		dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
//		double sinForce = Math.sin(radian) * 250 * myAmplitude;
//		double magnitude = (mySpringyness * (dist - myRestLength) * 70) +
//				sinForce;
//		double magnitude = (mySpringyness * (dist - myRestLength) * 4)
//				+ sinForce;
		magnitude = mySpringyness * (dist - tempRestLength);
		xComp = dx / dist * magnitude;
		yComp = dy / dist * magnitude;

		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
	}

	public void terminate() {
		clear = 1;
	}

	public void decreaseAmp() {
		myAmplitude -= 2;
	}

	public void increaseAmp() {
		myAmplitude += 2;
	}

	public double getAmp() {
		return myAmplitude;
	}
}
