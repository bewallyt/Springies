package simulation;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{
	
	/*Mass objects connected by the Spring*/
	private Mass myMass1,myMass2;
	private double x1,y1,x2,y2;
	
	private double myRestLength;
	private double mySpringyness;
	
	/*Spring constructor with springyness*/
	protected Spring(Mass m1, Mass m2, double restL,
			double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		x1 = myMass1.hookX;
		y1 = myMass1.hookY;
		x2 = myMass2.hookX;
		y2 = myMass2.hookY;
		myRestLength = restL;
		mySpringyness = K;
		
	}
	
	/*Spring constructor without springyness*/
	protected Spring(Mass m1, Mass m2, double restL) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = 1;
	}
	
	public void move()	{
		double width = Math.abs(x1-x2);
		double height = Math.abs(y1-y2);
		double xCorner = Math.min(x1, x2);
		double yCorner = Math.min(y1, y2);
		if(myEngine.getMouseButton(1) && (myEngine.getMouseX() >= xCorner && 
				myEngine.getMouseX() <= xCorner+width) && 
				(myEngine.getMouseY() >= yCorner &&
				myEngine.getMouseY() <= yCorner+height))	{
			x = myEngine.getMouseX();
			y = myEngine.getMouseY();
		}
	}
	
	@Override
	protected void paintShape() {
		myEngine.drawLine(x1, y1, 
				x2, y2);
		myEngine.setColor(myColor);
	}
	

}
