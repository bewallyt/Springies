package simulation;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{

	private Mass myMass1,myMass2;
	private double myRestLength;
	private double mySpringyness;
	
	/*Spring constructor with springyness*/
	protected Spring(Mass m1, Mass m2, double restL,
			double springyness) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = springyness;
	}
	
	/*Spring constructor without springyness*/
	protected Spring(Mass m1, Mass m2, double restL) {
		super("spring", 0, JGColor.blue);
		myMass1 = m1;
		myMass2 = m2;
		myRestLength = restL;
		mySpringyness = 1;
	}
	
	@Override
	protected void paintShape() {
		myEngine.drawLine(myMass1.hookX, myMass1.hookY, 
				myMass2.hookX, myMass2.hookY);
		myEngine.setColor(myColor);
	}
	

}
