package simulation;

import initialize.ObjectParser;
import java.util.ArrayList;
import java.util.List;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject {
	/* Mass objects connected by the Spring */

	private Mass myMass1, myMass2;
	private static List<Spring> Springs = new ArrayList<Spring>();
	private double myRestLength;
	private double mySpringyness;

	/* Spring constructor with springyness */
	public Spring(String m1, String m2, Double restL, Double K) {
		super("spring", 0, JGColor.blue);
		myMass1 = Mass.getMasses().get(m1);
		myMass2 = Mass.getMasses().get(m2);
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
		double magnitude = mySpringyness * (dist - myRestLength) * 80000;
		double xComp = dx / dist * magnitude;
		double yComp = dy / dist * magnitude;

		myMass1.setForce(xComp, yComp);
		myMass2.setForce(-xComp, -yComp);
	}

	public static void createSprings() {

		ObjectParser importSprings = new ObjectParser();

		List<List<Object>> tempSprings = new ArrayList<List<Object>>(
				importSprings.getSpringList());

		for (int i = 0; i < tempSprings.size(); i++) {

			Spring tempSpring = new Spring((String) tempSprings.get(i).get(0),
					(String) tempSprings.get(i).get(1), (Double) tempSprings
							.get(i).get(2), (Double) tempSprings.get(i).get(3));

			Springs.add(tempSpring);
		}

	}

}
