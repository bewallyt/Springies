package forces;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class CenterOfMass {

	public static double[] centerForce(ArrayList<Mass> masses) {
		
		double[] vector;
		ArrayList<Double> allX = new ArrayList<Double>();
		ArrayList<Double> allY = new ArrayList<Double>();
		double sumX = 0;
		double sumY = 0;
		double avgX = 0;
		double avgY = 0;
		double dist = 0;
		int max = masses.size();

		for (int i = 0; i < max; i++) {

			allX.add(masses.get(i).getMassX());
			allY.add(masses.get(i).getMassY());

		}

		for (int i = 0; i < max; i++) {

			sumX = sumX + allX.get(i);
			sumY = sumY + allY.get(i);

		}

		avgX = sumX / max;
		avgY = sumY / max;

		dist = Math.sqrt(Math.pow(avgX - 426, 2) + Math.pow(avgX - 240, 2));

		double angle = Math.atan((avgX - 426) / (avgX - 240));
		double xComp = Math.cos(angle) * dist * 20;
		double yComp = Math.sin(angle) * dist * 20;
	

		if (((avgX - 426) > 0) && ((avgY - 240) > 0)) {
			vector = new double[] {-xComp,  -yComp};
		}
		else if (((avgX - 426) < 0) && ((avgY - 240) > 0)) {
			vector = new double[] {xComp, -yComp};
		}
		else if (((avgX - 426) > 0) && ((avgY - 240) < 0)) {
			vector = new double[] {-xComp, yComp};
		}
		else{
			vector = new double[] {xComp, yComp};
		}

		return vector;

	}

}
