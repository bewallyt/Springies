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
		/**
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
		*/
	}
	
	@Override
	protected void paintShape() {
		myEngine.setColor(JGColor.green);
		myEngine.drawLine(x1, y1, x2, y2);
		
	}
	
	public void springForce(){
		double distance = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2, 2));
		double[] vec12 = findVec(x1,y1,x2,y2);
		myMass1.setForce(vec12[0],vec12[1]);
		double[] vec21 = findVec(x2,y2,x1,y1);
		myMass2.setForce(vec21[0],vec21[1]);
		
	}
	
	public double[] findVec(double xp1, double yp1, double xp2, double yp2)	{
		double dist = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2, 2));
		double length = xp1-xp2;
		double height = yp1-yp2;
		double magnitude = mySpringyness*(dist - myRestLength);
		double angle = Math.atan(height/length);
		double xComp = Math.cos(angle)*magnitude;
		double yComp = Math.sin(angle)*magnitude;
		double[] output = new double[2];
		output[0] = xComp;
		output[1] = yComp;
		return output;
	}

}
