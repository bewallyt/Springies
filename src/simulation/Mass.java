package simulation;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
import jgame.JGColor;

public class Mass extends PhysicalObjectCircle {

	private String id;
	private double mass;

	/* Constructor */
	public Mass(String id, double radius, double xCoord, double yCoord,
			double mass, double xv, double yv) {
		super(id, 0, JGColor.blue, radius, mass);

		this.id = id;
		this.x = xCoord;
		this.y = yCoord;
		this.xspeed = xv;
		this.yspeed = yv;
		this.mass = mass;

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

	// int prevmousex = 0, prevmousey = 0;

	// public void move() {
	// if (myEngine.getMouseButton(1)
	// && (myEngine.getMouseX() >= x - massWidth * 2 && myEngine
	// .getMouseX() <= x + massWidth * 2)
	// && (myEngine.getMouseY() >= y - massHeight * 2 && myEngine
	// .getMouseY() <= y + massHeight * 2)) {
	// x = myEngine.getMouseX() - massWidth / 2;
	// y = myEngine.getMouseY() - massHeight / 2;
	// xspeed = (x - prevmousex) / 10;
	// yspeed = (y - prevmousey) / 10;
	// prevmousex = myEngine.getMouseX();
	// prevmousey = myEngine.getMouseY();
	// }
	// }

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
