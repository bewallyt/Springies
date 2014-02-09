package forces;

import java.awt.Dimension;
import org.jbox2d.common.Vec2;
import simulation.Mass;

public class WallRepulsion extends Force{

	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;
	public static final int LEFT = 4;

	private int id;
	private double mag;
	private double exp;

	public WallRepulsion (int id, double mag, double exp){
		this.id = id;
		this.mag = mag;
		this.exp = exp;
	}

	 private double calcDistance (Mass m, Dimension bounds) {
	        double dis = 0;
	        switch (id) {
	            case TOP:
	                dis = m.getMassY();
	                break;
	            case RIGHT:
	                dis = bounds.getWidth() - m.getMassX();
	                break;
	            case LEFT:
	                dis = m.getMassX();
	                break;
	            case BOTTOM:
	                dis = bounds.getHeight() - m.getMassY();
	                break;
	            default:
	                break;
	        }
	        return dis;
	    }
	 

		private double calcAngle() {
			
			double angle = 0;

			switch(id) {
			case TOP:
					angle = 90;
					break;
			case RIGHT:
					angle = 180;
					break;
			case LEFT:
					angle = 0;
					break;
			case BOTTOM:
					angle = 270;
					break;
			default:
					break;
			}
			return angle;
		}


	 public Vec2 deriveForce (Mass m, Dimension bounds) {
		 double dis = calcDistance(m, bounds);
		 double angle = calcAngle();
		 return super.deriveForce(mag, exp, angle, dis);
	 }

}