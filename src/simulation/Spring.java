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
		springForce();
	}

	@Override
	protected void paintShape() {
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(myMass1.getMassX(), myMass1.getMassY(), myMass2.getMassX(), myMass2.getMassY());
		springForce();
	}
	
	public void springForce(){
		double dist = Math.sqrt(Math.pow(myMass1.getMassX()-myMass2.getMassX(),2) + 
				Math.pow(myMass1.getMassY()-myMass2.getMassY(),2));
		double length = myMass1.getMassX()-myMass2.getMassX();
		double height = myMass1.getMassY()-myMass2.getMassY();
		double magnitude = mySpringyness*(dist - myRestLength);
		double angle = Math.atan(height/length);
		double xComp = Math.cos(angle)*magnitude;
		double yComp = Math.sin(angle)*magnitude;
		myMass1.setForce(xComp,yComp);
		myMass2.setForce(-xComp,-yComp);
		
		System.out.println("Mass " + myMass1 +  " x: " + xComp);
		System.out.println("Mass " + myMass1 +  " y: " + yComp);
		
	}

}
