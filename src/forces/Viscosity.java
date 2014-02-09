package forces;

import org.jbox2d.common.Vec2;

public class Viscosity {

	private double mag;

	public Viscosity(double mag) {
		this.mag = mag;
	}

	public Vec2 setViscosity(Vec2 velocity) {
		velocity.x *= mag;
		velocity.y *= mag;
		return velocity;
	}

}
