package forces;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class Gravity {
	
	private Double dir;
	private Double mag;
	private Vec2 gravVec;
	
	public Gravity ( Double dir, Double mag){
		this.dir = dir;
		this.mag = mag;
	}
	
	public Vec2 applyGravity(Mass m) {
		System.out.println(m.getMass());
		gravVec.x = (float) (Math.cos(dir) * m.getMass() * mag);
		gravVec.y =  (float) (Math.sin(dir) * m.getMass() * mag);
		System.out.println(gravVec.x);
		System.out.println(gravVec.y);
		return gravVec;
	
	}

}
