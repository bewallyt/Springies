package forces;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class CenterOfMass {

	private double xCenter;
	private double yCenter;
	private static ArrayList<Mass> myMasses = new ArrayList<Mass>();

	public CenterOfMass(ArrayList<Mass> masses) {
		xCenter = 426;
		yCenter = 240;
		myMasses = masses;

	}

	public static Vec2 centerForce() {

		ArrayList<Double> allX = new ArrayList<Double>();
		ArrayList<Double> allY = new ArrayList<Double>();
		double sumX = 0;
		double sumY = 0;
		double avgX = 0;
		double avgY = 0;
		double dist = 0;
		int max = myMasses.size();

		for (int i = 0; i < max; i++) {

			allX.add(myMasses.get(i).getMassX());
			allY.add(myMasses.get(i).getMassY());

		}

		for (int i = 0; i < max; i++) {

			sumX = sumX + allX.get(i);
			sumY = sumY + allY.get(i);

		}

		avgX = sumX / max;
		avgY = sumY / max;

		dist = Math.sqrt(Math.pow(avgX - 426, 2) + Math.pow(avgX - 240, 2));

		double angle = Math.atan((avgX - 426) / (avgX - 240));
		double xComp = Math.cos(angle) * dist * 100;
		double yComp = Math.sin(angle) * dist * 100;
		
		Vec2 vec;

		if (((avgX - 426) > 0) && ((avgY - 240) > 0)) {
			vec = new Vec2((float) -xComp, (float) -yComp);
		}
		else if (((avgX - 426) < 0) && ((avgY - 240) > 0)) {
			vec = new Vec2((float) xComp, (float) -yComp);
		}
		else if (((avgX - 426) > 0) && ((avgY - 240) < 0)) {
			vec = new Vec2((float) -xComp, (float) yComp);
		}
		else{
			vec = new Vec2((float) xComp, (float) yComp);
		}

		return vec;

	}

}
