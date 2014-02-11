package forces;

import org.jbox2d.common.Vec2;

public class Viscosity {

	private double mag;

//	public Viscosity(List<Double> viscMag) {
//		this.mag = viscMag.get(0);
//	}
	
	public Viscosity(Double viscMag) {
		this.mag = viscMag;
	}

	public Vec2 setViscosity(Vec2 velocity) {
		velocity.x *= mag;
		velocity.y *= mag;
		return velocity;
	}

}
