package forces;

import org.jbox2d.common.Vec2;

public class Force {

	public Vec2 deriveForce(double mag, double exp, double ang, double dis) {
		
		float yForce;
		float xForce;
		
		if (exp > 0) {
			yForce = (float) (mag * (Math.pow(dis, exp * -2)) * Math.sin(ang));
			xForce = (float) (mag * (Math.pow(dis, exp * -2)) * Math.cos(ang));
			
		} else if (exp == 0) {
			yForce = (float) (mag * Math.sin(ang));
			xForce = (float) (mag * Math.cos(ang));
			
		} else{
			yForce = (float) (mag * (Math.pow(dis, exp * -2)) * Math.sin(ang));
			xForce = (float) (mag * (Math.pow(dis, exp * -2)) * Math.cos(ang));		
		}
		
		return(new Vec2(xForce, yForce));	
	}
	
	
}
