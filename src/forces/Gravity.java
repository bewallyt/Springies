package forces;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class Gravity {
	
	private Double dir;
	private Double mag;
	private Vec2 gravVec;
	
	public Gravity ( Double dir, Double mag){
		this.dir = dir/(2*3.14);
		this.mag = mag;
	}
	
	public Vec2 applyGravity(Mass m) {
		System.out.println(m.getMass());
		gravVec = new Vec2((float) (Math.cos(dir) * m.getMass() * mag),(float) (Math.sin(dir) * m.getMass() * mag));
		return gravVec;
	
	}

}
