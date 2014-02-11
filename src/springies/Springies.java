package springies;

import forces.CenterOfMass;
import forces.Viscosity;
import forces.WallRepulsion;
import initialize.AssemblyFileChooser;
import initialize.COMParser;
import initialize.EnvironmentParser;
import initialize.FixedParser;
import initialize.MassParser;
import initialize.MuscleParser;
import initialize.SpringParser;
import initialize.ViscosityParser;
import initialize.WallParser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.platform.JGEngine;

import org.jbox2d.common.Vec2;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Muscle;
import simulation.Spring;

@SuppressWarnings("serial")
public class Springies extends JGEngine {

	public static final Dimension SIZE = new Dimension(800, 600);
	public static final String TITLE = "Springies!";

	private String environmentString;
	private List<Double> comList;
	private List<List<Double>> wallList;

	private List<FixedMass> totalFixedMasses = new ArrayList<FixedMass>();
	private List<Mass> totalMasses = new ArrayList<Mass>();
	private List<Muscle> totalMuscles = new ArrayList<Muscle>();
	private List<Spring> totalSprings = new ArrayList<Spring>();

	private double viscMag;

	public Springies(String environment) {
		int height = 480;
		double aspect = 16.0 / 9.0;
		initEngineComponent((int) (height * aspect), height);

		this.environmentString = environment;
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(1, 1, displayWidth(), displayHeight(), null, null,
				null);

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
		//WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.1f));
		addWalls();

		// Initialize Environment Forces (Vectors need to be passed into Mass'
		// move)
		EnvironmentParser environment = new EnvironmentParser();
		// environment.readGravity(environmentString);
		ViscosityParser visc = new ViscosityParser();
		COMParser com = new COMParser();
		WallParser wall = new WallParser();
		viscMag = visc.returnViscosity(environmentString);
		comList = com.returnCOM(environmentString);
		wallList = wall.returnWalls(environmentString);

	}

	/**
	 * public void addBall() { // add a bouncy ball // NOTE: you could make this
	 * into a separate class, but I'm lazy PhysicalObject ball = new
	 * PhysicalObjectCircle("ball", 1, JGColor.blue, 10, 5); {
	 * 
	 * @Override public void hit(JGObject other) { // we hit something! bounce
	 *           off it! Vec2 velocity = myBody.getLinearVelocity(); // is it a
	 *           tall wall? final double DAMPING_FACTOR = 0.8; boolean isSide =
	 *           other.getBBox().height > other.getBBox().width; if (isSide) {
	 *           velocity.x *= -DAMPING_FACTOR; } else { velocity.y *=
	 *           -DAMPING_FACTOR; } // apply the change
	 *           myBody.setLinearVelocity(velocity); } } ; ball.setPos(285,
	 *           190);
	 * 
	 *           ball.setForce(8000, -10000);
	 * 
	 *           }
	 */

	/**
	 * Instantiate objects from XML files below.
	 */

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

		if (getKey('N')) {
			clearKey('N');
			System.out.println("n pressed");
			AssemblyFileChooser afc = new AssemblyFileChooser();
			afc.setVisible(true);
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			initAssembly(afc.getAssemblyString());
		}

		if (getKey('C')) {
			clearKey('C');
			clearAssembly();
			
		}
		
		if(totalMasses.size() != 0){
			initForces(totalMasses);
		}

		WorldManager.getWorld().step(1f, 1);
		// update game objects

		// createSprings();
		moveObjects();
		checkCollision(1 + 2, 1);
		// initForces(masses);

	}

	@Override
	public void paintFrame() {
		
	}

	public void initForces(List<Mass> masses) {
		// apple COM & Wall Repulsion & Viscosity
		CenterOfMass com = new CenterOfMass(comList.get(0), comList.get(1));

		WallRepulsion wr0 = new WallRepulsion(1, wallList.get(0).get(1),
				wallList.get(0).get(2));

		WallRepulsion wr1 = new WallRepulsion(2, wallList.get(1).get(1),
				wallList.get(1).get(2));

		WallRepulsion wr2 = new WallRepulsion(3, wallList.get(2).get(1),
				wallList.get(2).get(2));

		WallRepulsion wr3 = new WallRepulsion(4, wallList.get(3).get(1),
				wallList.get(3).get(2));

		Viscosity visc = new Viscosity(viscMag);

		for (Mass m : masses) {

			Vec2 comForce = com.obtainForce(m);
			m.setForce(comForce.x, comForce.y);
			visc.setViscosity(m.getVelocity());

			Vec2 wallForce0 = wr0.obtainForce(m, SIZE);
			Vec2 wallForce1 = wr1.obtainForce(m, SIZE);
			Vec2 wallForce2 = wr2.obtainForce(m, SIZE);
			Vec2 wallForce3 = wr3.obtainForce(m, SIZE);
			m.setForce(wallForce0.x * 90, wallForce0.y * 90);
			m.setForce(wallForce1.x * 90, wallForce1.y * 90);
			m.setForce(wallForce2.x * 90, wallForce2.y * 90);
			m.setForce(wallForce3.x * 90, wallForce3.y * 90);
		}
	}

	public void initAssembly(String assembly) {

		List<FixedMass> fixedmasses;
		List<Mass> masses;
		List<Muscle> muscles;
		List<Spring> springs;

		// Remember how to cast: muscles = (List<Muscle>)(List<?>)
		// muscle.readFile(object);

		FixedParser fixed = new FixedParser();
		MassParser mass = new MassParser();
		fixedmasses = fixed.returnFixedMasses(assembly);
		masses = mass.returnMasses(assembly);

		MuscleParser muscle = new MuscleParser(masses, fixedmasses);
		SpringParser spring = new SpringParser(masses, fixedmasses);
		muscles = muscle.returnMuscles(assembly);
		springs = spring.returnSprings(assembly);
		
		

		for (Mass m : masses) {
			totalMasses.add(m);
		}
		for (FixedMass fm : fixedmasses) {
			totalFixedMasses.add(fm);
		}
//		for (Muscle mu : muscles) {
//			totalMuscles.add(mu);
//		}
		for (Spring s : springs) {
			totalSprings.add(s);
		}
	}

	public void clearAssembly() {
		
		for (Spring s : totalSprings) {
			s.terminate();
		}
		
		for (Mass m : totalMasses) {
			m.remove();
		}
		for (FixedMass fm : totalFixedMasses) {
			fm.remove();
		}
//		for (Muscle mu : totalMuscles) {
//			mu.remove();
//		}

		totalFixedMasses.clear();
		totalMasses.clear();
//		totalMuscles.clear();
		totalSprings.clear();
	}

	public static void createSpringies(String environment) {
		final Springies sp = new Springies(environment);

		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(sp, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);

	}

}
