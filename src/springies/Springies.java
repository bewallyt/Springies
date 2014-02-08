package springies;

import initialize.EnvironmentParser;
import initialize.FixedParser;
import initialize.MassParser;
import initialize.MuscleParser;
import initialize.SpringParser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.platform.JGEngine;

import org.jbox2d.common.Vec2;

import simulation.FixedMass;
import simulation.Mass;

@SuppressWarnings("serial")
public class Springies extends JGEngine {
	
	public static final Dimension SIZE = new Dimension(800, 600);
	public static final String TITLE = "Springies!";
	private String object;
	private String environmentString;

	public Springies(String object, String environment) {
		int height = 480;
		double aspect = 16.0 / 9.0;
		initEngineComponent((int) (height * aspect), height);
		
		this.object = object;
		this.environmentString = environment;
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(1, 1, displayWidth(),displayHeight(),null,null,null); 
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
		//addBall();
		addWalls();
		
		// Initialize Objects
		FixedParser fixed = new FixedParser();
		MassParser mass = new MassParser();
		MuscleParser muscle = new MuscleParser();
		SpringParser spring = new SpringParser();

		List<FixedMass> fixedMasses = fixed.createFixedMasses(object);
		List<Mass> masses = mass.createMasses(object);
		muscle.createMuscles(object, masses, fixedMasses);
		spring.createSprings(object, masses, fixedMasses);
		
		// Initialize Environment Forces (Vectors need to be passed into Mass' move)
		EnvironmentParser environment = new EnvironmentParser();
		environment.readGravity(environmentString);
		environment.readViscosity(environmentString);
		environment.readCOM(environmentString);
		environment.readWall(environmentString);
		

	}

	public void addBall() {
		// add a bouncy ball
		// NOTE: you could make this into a separate class, but I'm lazy
		PhysicalObject ball = new PhysicalObjectCircle("ball", 1, JGColor.blue,10, 5);
		{
			// @Override
			// public void hit(JGObject other) {
			// // we hit something! bounce off it!
			// Vec2 velocity = myBody.getLinearVelocity();
			// // is it a tall wall?
			// final double DAMPING_FACTOR = 0.8;
			// boolean isSide = other.getBBox().height > other.getBBox().width;
			// if (isSide) {
			// velocity.x *= -DAMPING_FACTOR;
			// } else {
			// velocity.y *= -DAMPING_FACTOR;
			// }
			// // apply the change
			// myBody.setLinearVelocity(velocity);
			// }
		};
		ball.setPos(285, 190);

		// ball.setForce(8000, -10000);

	}

	/**
	 * Instantiate objects from XML files below.
	 */
	

	
//	public double[] applyCenterForce(ArrayList<Mass> massesCenter){
//		
//		return CenterOfMass.centerForce(massesCenter);
//		
//	}
//


	
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
		// createSprings();
		moveObjects();
		checkCollision(1 + 2, 1);

//		for (Mass m : massesCenter) {
//			m.setForce(applyCenterForce(massesCenter)[0],applyCenterForce(massesCenter)[1]);
//
//		}

		

	}

	@Override
	public void paintFrame() {
	}
	
	public static void createSpringies(String object, String environment){
		final Springies sp = new Springies(object, environment);
		JButton jb = new JButton("Make new Ball");

		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(sp, BorderLayout.CENTER);
		frame.getContentPane().add(jb, BorderLayout.SOUTH);
		frame.pack();
		
		frame.setVisible(true);
		
		
		
	}
	

}
