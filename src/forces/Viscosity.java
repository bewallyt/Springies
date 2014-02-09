package forces;

import org.jbox2d.common.Vec2;
import simulation.Mass;

public class Viscosity{

	public Vec2 setViscosity(Mass m,double magnitude){
	Vec2 linearVelocity = m.getVelocity();
		linearVelocity.x *= magnitude;
		linearVelocity.y *= magnitude;
		return linearVelocity;
	}


}
