package simulation;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
import jgame.JGColor;

public class Mass extends PhysicalObjectCircle {

	private String id;
	private double mass;
	private static double MASS_RADIUS = 5;

	/* Constructor */
	public Mass(String id, double xCoord, double yCoord,
			double mass, double xv, double yv) {
		super(id, 0, JGColor.blue, MASS_RADIUS, mass);

		this.id = id;
		this.x = xCoord;
		this.y = yCoord;
		this.xspeed = xv;
		this.yspeed = yv;
		this.mass = mass;

	}

	
	
	@Override
	protected void init(double radius, double mass) {
		CircleDef shape = new CircleDef();
		shape.filter.groupIndex = -1;
		super.init(radius, mass);
	}



	public double getMassX() {
		return this.x;
	}

	public double getMassY() {
		return this.y;
	}

	public String getID() {
		return this.id;
	}

	public double getMass() {
		return this.mass;
	}

	public void move() {
		// if the JGame object was deleted, remove the physical object too
		if (myBody.m_world != WorldManager.getWorld()) {
			remove();
			return;
		}

		// copy the position and rotation from the JBox world to the JGame world
		Vec2 position = myBody.getPosition();
		this.x = position.x;
		this.y = position.y;
		myRotation = -myBody.getAngle();

	}

	public Vec2 getVelocity() {
		Vec2 velocity = myBody.getLinearVelocity();
		return velocity;
	}
	
	public String toString(){
		return id + " x:" + x + " y:" + y;
	}

}
