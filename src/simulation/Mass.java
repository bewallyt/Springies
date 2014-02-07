package simulation;

import initialize.ObjectParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import forces.Viscosity;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
import jgame.JGColor;

public class Mass extends PhysicalObjectCircle {

	private static ArrayList<Mass> massesCenter = new ArrayList<Mass>();
	private static HashMap<String, Mass> Masses = new HashMap<String, Mass>();

	public static ArrayList<Mass> getMassesCenter() {
		return massesCenter;
	}

	public static HashMap<String, Mass> getMasses() {
		return Masses;
	}

	/* Constructor */
	public Mass(String id, double radius, double xCoord, double yCoord,
			double mass, double xv, double yv) {
		super(id, 0, JGColor.blue, radius, mass);

		this.x = xCoord;
		this.y = yCoord;
		this.xspeed = xv;
		this.yspeed = yv;

	}
	
	public double getMassX(){
		return this.x;
	}
	public double getMassY(){
		return this.y;
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

		initViscosity();
	}

	public Vec2 getVelocity() {
		Vec2 velocity = myBody.getLinearVelocity();
		return velocity;
	}

	private void initViscosity() {
		myBody.setLinearVelocity(Viscosity.setViscosity(this, 0.95));
	}

	public static void createMasses() {
		ObjectParser importMass = new ObjectParser();

		Map<String, List<Double>> massMap = new HashMap<String, List<Double>>(
				importMass.getMassMap());

		for (Entry<String, List<Double>> entry : massMap.entrySet()) {
			String key = entry.getKey();
			List<Double> value = entry.getValue();
			Mass tempMass = new Mass(key, 5, value.get(0), value.get(1), 50,
					value.get(2), value.get(3));

			tempMass.setPos(value.get(0), value.get(1));

			Masses.put(key, tempMass);
			massesCenter.add(tempMass);

		}
	}

}
