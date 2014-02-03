package forces;

import jboxGlue.PhysicalObject;

import org.jbox2d.common.Vec2;

import simulation.Mass;

public class Viscosity{

	public static void setViscosity(Mass m,double magnitude){
	Vec2 linearVelocity = m.getVelocity();
		linearVelocity.x *= magnitude;
		linearVelocity.y *= magnitude;
		myBody.setLinearVelocity(linearVelocity);
	}

	@Override
	protected void paintShape() {
		// TODO Auto-generated method stub
		
	}

}
