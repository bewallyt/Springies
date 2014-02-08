package forces;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class CenterOfMass extends Force {

	private double mag, exp;
	private Point2D center;

	public CenterOfMass(double mag, double exp) {
		this.mag = mag;
		this.exp = exp;
		this.center = new Point2D.Double();
	}

	public void calcCenter(List<Mass> masses) {

		ArrayList<Double> allX = new ArrayList<Double>();
		ArrayList<Double> allY = new ArrayList<Double>();
		double sumX = 0;
		double sumY = 0;
		double avgX = 0;
		double avgY = 0;
		
		int max = masses.size();

		for (int i = 0; i < max; i++) {
			allX.add(masses.get(i).getMassX() * masses.get(i).getMass());
			allY.add(masses.get(i).getMassY() * masses.get(i).getMass());
		}

		for (int i = 0; i < max; i++) {
			sumX = sumX + allX.get(i);
			sumY = sumY + allY.get(i);
		}

		avgX = sumX / max;
		avgY = sumY / max;
		center = new Point2D.Double(avgX, avgY);
	}

	private double calcDistance(Mass m) {
		return center.distance(m.getMassX(), m.getMassY());
	}

	private double calcAngle(Mass m) {
		double ang = 0;
		double x = center.getX() - m.getMassX();
		double y = center.getY() - m.getMassY();
		ang = Math.atan2(x, y);
		return ang;
	}

	public Vec2 obtainForce(Mass m) {
		
		double ang = calcAngle(m);
		double dis = calcDistance(m);
		Vec2 force = super.deriveForce(mag, exp, ang, dis);
		return force;
	}

}
