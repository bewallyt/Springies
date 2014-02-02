package springies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import org.jbox2d.common.Vec2;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Spring;
import simulation.XMLParser;

@SuppressWarnings("serial")
public class Springies extends JGEngine {
	
	private static HashMap<String, Mass> Masses = new HashMap<String, Mass>();
	
	public static HashMap<String, Mass> getMasses() {
		return Masses;
	}

	public void setMasses(HashMap<String, Mass> masses) {
		Masses = masses;
	}

	public Springies() {
		// set the window size
		int height = 480;
		double aspect = 16.0 / 9.0;
		initEngineComponent((int) (height * aspect), height);
	}

	@Override
	public void initCanvas() {
		// I have no idea what tiles do...
		setCanvasSettings(1, // width of the canvas in tiles
				1, // height of the canvas in tiles
				displayWidth(), // width of one tile
				displayHeight(), // height of one tile
				null,// foreground colour -> use default colour white
				null,// background colour -> use default colour black
				null); // standard font -> use default font
	}

	@Override
	public void initGame() {
		setFrameRate(60, 2);
		// NOTE:
		// world coordinates have y pointing down
		// game coordinates have y pointing up
		// so gravity is up in world coords and down in game coords
		// so set all directions (e.g., forces, velocities) in world coords
		WorldManager.initWorld(this);
		WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.1f));
		addBall();
		createMasses();
		createSprings();
		addWalls();
	}

	public void addBall() {
		// add a bouncy ball
		// NOTE: you could make this into a separate class, but I'm lazy
		PhysicalObject ball = new PhysicalObjectCircle("ball", 1, JGColor.blue,
				10, 5); 
		
		{
//			@Override
//			public void hit(JGObject other) {
//				// we hit something! bounce off it!
//				Vec2 velocity = myBody.getLinearVelocity();
//				// is it a tall wall?
//				final double DAMPING_FACTOR = 0.8;
//				boolean isSide = other.getBBox().height > other.getBBox().width;
//				if (isSide) {
//					velocity.x *= -DAMPING_FACTOR;
//				} else {
//					velocity.y *= -DAMPING_FACTOR;
//				}
//				// apply the change
//				myBody.setLinearVelocity(velocity);
//			}
		};
		ball.setPos(displayWidth() / 2, displayHeight() / 2);
		//ball.setForce(8000, -10000);

		PhysicalObject obj1 = new Mass("m1", 10, 10, 100, 100, 1, 0.8951623,
				0.45419145);
		obj1.setForce(8000, -10000);
	}

	/**
	 * Instantiate objects from XML files below.
	 */

	public void createMasses() {
		XMLParser importObject = new XMLParser();
		importObject.readXMLObject("ball.xml");
		HashMap<String, ArrayList<Double>> massMap = new HashMap<String, ArrayList<Double>>(
				importObject.getMassMap());

		for (Entry<String, ArrayList<Double>> entry : massMap.entrySet()) {
			String key = entry.getKey();
			ArrayList<Double> value = entry.getValue();

			Mass tempMass = new Mass(key, 15, 15, value.get(0), value.get(1), 50,
					value.get(2), value.get(3));
			
			Masses.put(key, tempMass);
			
		}
		
		
	}

	public void createFixedMasses() {
		XMLParser importObject = new XMLParser();
		importObject.readXMLObject("ball.xml");
		HashMap<String, ArrayList<Double>> fixedMassMap = new HashMap<String, ArrayList<Double>>(
				importObject.getFixedMap());

		HashMap<String, FixedMass> fixedMasses = new HashMap<String, FixedMass>();

		for (Entry<String, ArrayList<Double>> entry : fixedMassMap.entrySet()) {
			String key = entry.getKey();
			ArrayList<Double> value = entry.getValue();
			// System.out.println(key);
			fixedMasses.put(key, new FixedMass(key, value.get(0), value.get(1)));
			
			for (int i = 0; i < value.size(); i++) {
				double attr = value.get(i);
				System.out.print(attr + " ");
			}
		}
	}

	public void createSprings() {
		XMLParser importObject = new XMLParser();
		importObject.readXMLObject("ball.xml");
		ArrayList<ArrayList<Object>> tempSprings = new ArrayList<ArrayList<Object>>(
				importObject.getSpringList());
		// Mass mass1 = new Mass();

		ArrayList<Spring> Springs = new ArrayList<Spring>();

		for (int i = 0; i < tempSprings.size(); i++) {
			Springs.add(new Spring((String) tempSprings.get(i).get(0),
					(String) tempSprings.get(i).get(1), (Double) tempSprings
							.get(i).get(2), (Double) tempSprings.get(i).get(3)));
			
		}
		

	}

	private void addWalls() {
		// add walls to bounce off of
		// NOTE: immovable objects must have no mass
		final double WALL_MARGIN = 10;
		final double WALL_THICKNESS = 10;
		final double WALL_WIDTH = displayWidth() - WALL_MARGIN * 2
				+ WALL_THICKNESS;
		final double WALL_HEIGHT = displayHeight() - WALL_MARGIN * 2
				+ WALL_THICKNESS;
		PhysicalObject wall = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_WIDTH, WALL_THICKNESS);
		wall.setPos(displayWidth() / 2, WALL_MARGIN);
		wall = new PhysicalObjectRect("wall", 2, JGColor.green, WALL_WIDTH,
				WALL_THICKNESS);
		wall.setPos(displayWidth() / 2, displayHeight() - WALL_MARGIN);
		wall = new PhysicalObjectRect("wall", 2, JGColor.green, WALL_THICKNESS,
				WALL_HEIGHT);
		wall.setPos(WALL_MARGIN, displayHeight() / 2);
		wall = new PhysicalObjectRect("wall", 2, JGColor.green, WALL_THICKNESS,
				WALL_HEIGHT);
		wall.setPos(displayWidth() - WALL_MARGIN, displayHeight() / 2);
	}

	@Override
	public void doFrame() {
		// update game objects
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(1 + 2, 1);
	}

	@Override
	public void paintFrame() {
		// nothing to do
		// the objects paint themselves
	}
}
