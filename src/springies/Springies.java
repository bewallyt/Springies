package springies;

import forces.CenterOfMass;
import forces.Gravity;
import forces.Viscosity;
import forces.WallRepulsion;
import initialize.COMParser;
import initialize.FileChooser;
import initialize.FixedParser;
import initialize.GravityParser;
import initialize.MassParser;
import initialize.MuscleParser;
import initialize.SpringParser;
import initialize.ViscosityParser;
import initialize.WallParser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import jboxGlue.WorldManager;
import jgame.platform.JGEngine;

import org.jbox2d.common.Vec2;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Muscle;
import simulation.Spring;
import simulation.WallSetup;

@SuppressWarnings("serial")
public class Springies extends JGEngine {

	public static final Dimension SIZE = new Dimension(800, 600);
	public static final String TITLE = "Springies!";

	private String environmentString;
	private List<Double> comList;
	private List<Double> gravityList;
	private List<List<Double>> wallList;

	private boolean isGravityOn = false;
	private boolean isViscosityOn = false;
	private boolean isRightWallOn = false;
	private boolean isLeftWallOn = false;
	private boolean isTopWallOn = false;
	private boolean isBottomWallOn = false;
	private boolean isCOMOn = false;

	private boolean mouseMassCreated = false;
	private Mass mouseMass;
	private Mass closest;
	private Spring mouseSpring;

	private List<FixedMass> totalFixedMasses = new ArrayList<FixedMass>();
	private List<Mass> totalMasses = new ArrayList<Mass>();
	private List<Muscle> totalMuscles = new ArrayList<Muscle>();
	private List<Spring> totalSprings = new ArrayList<Spring>();
	private List<WallSetup> totalWalls = new ArrayList<WallSetup>();

	private double viscMag;

	public Springies(String environment) {
		int height = 700;
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

		/**
		 * initEnvironment() initializes Viscosity, Gravity, Wall Repulsion,
		 * Center of Mass addWalls() sets default wall dimensions. stored in
		 * totalWalls List.
		 */

		WorldManager.initWorld(this);
		addWalls();
		initEnvironment();

	}

	private void addWalls() {

		final double WALL_MARGIN = 10;
		final double WALL_THICKNESS = 10;
		final double WALL_WIDTH = displayWidth() - WALL_MARGIN * 2
				+ WALL_THICKNESS;
		final double WALL_HEIGHT = displayHeight() - WALL_MARGIN * 2
				+ WALL_THICKNESS;
		WallSetup wall = new WallSetup("top", WALL_WIDTH, WALL_THICKNESS);
		wall.setPos(displayWidth() / 2, WALL_MARGIN);
		totalWalls.add(wall);

		wall = new WallSetup("bottom", WALL_WIDTH, WALL_THICKNESS);
		wall.setPos(displayWidth() / 2, displayHeight() - WALL_MARGIN);
		totalWalls.add(wall);

		wall = new WallSetup("left", WALL_THICKNESS, WALL_HEIGHT);
		wall.setPos(WALL_MARGIN, displayHeight() / 2);
		totalWalls.add(wall);

		wall = new WallSetup("right", WALL_THICKNESS, WALL_HEIGHT);
		wall.setPos(displayWidth() - WALL_MARGIN, displayHeight() / 2);
		totalWalls.add(wall);
	}

	@Override
	public void doFrame() {
		WorldManager.getWorld().step(1f, 1);
		/**
		 * toggleAssembly(): "N" Opens File Chooser for Assembly. "C" Clears all
		 * Assemblies toggleForces(): Toggles all environment forces, wall
		 * repulsion/position, muscle amplitude. toggle statuses are displayed
		 * via paintframe()
		 */
		toggleAssembly();
		toggleForces();
		if (totalMasses.size() != 0) {
			initForces(totalMasses);
		}
		mouseAction();
		moveObjects();
		checkCollision(1 + 2, 1);
		clearLastKey();
	}

	@Override
	public void paintFrame() {
		drawString("G: " + (isGravityOn),20,20,-1);
		drawString("V: " + (isViscosityOn),20,50,-1);
		drawString("M: " + (isCOMOn),20,80,-1);
		drawString("1: " + (isTopWallOn),20,110,-1);
		drawString("2: " + (isRightWallOn),20,140,-1);
		drawString("3: " + (isBottomWallOn),20,170,-1);
		drawString("4: " + (isLeftWallOn),20,200,-1);
		drawString("-: " + (getKey('-')),20,230,-1);
		drawString("=: " + (getKey('=')),20,260,-1);
		drawString("Up: " + (getKey(KeyEvent.VK_UP)),20,290,-1);
		drawString("Down: " + (getKey(KeyEvent.VK_DOWN)),20,320,-1);
		drawString("Press N for new assembly",200,20,-1);
		drawString("Press C to clear assemblies",200,50,-1);
	}

	public void initForces(List<Mass> masses) {

		CenterOfMass com = new CenterOfMass(comList.get(0), comList.get(1));
		WallRepulsion wr0 = new WallRepulsion(1, wallList.get(0).get(1),
				wallList.get(0).get(2));
		WallRepulsion wr1 = new WallRepulsion(2, wallList.get(1).get(1),
				wallList.get(1).get(2));
		WallRepulsion wr2 = new WallRepulsion(3, wallList.get(2).get(1),
				wallList.get(2).get(2));
		WallRepulsion wr3 = new WallRepulsion(4, wallList.get(3).get(1),
				wallList.get(3).get(2));
		Gravity grav = new Gravity(gravityList.get(0), gravityList.get(1));
		Viscosity visc = new Viscosity(viscMag);

		for (Mass m : masses) {

			Vec2 wallForce0 = wr0.obtainForce(m, SIZE);
			Vec2 wallForce1 = wr1.obtainForce(m, SIZE);
			Vec2 wallForce2 = wr2.obtainForce(m, SIZE);
			Vec2 wallForce3 = wr3.obtainForce(m, SIZE);

			if (isGravityOn) {
				m.setForce(grav.applyGravity(m).x, grav.applyGravity(m).y);
			}

			Vec2 comForce = com.obtainForce(m);
			if (isCOMOn) m.setForce(comForce.x, comForce.y);
			if (isViscosityOn) visc.setViscosity(m.getVelocity());
			if (isTopWallOn) m.setForce(wallForce0.x * 90, wallForce0.y * 90);
			if (isRightWallOn) m.setForce(wallForce1.x * 90, wallForce1.y * 90);
			if (isBottomWallOn) m.setForce(wallForce2.x * 90, wallForce2.y * 90);
			if (isLeftWallOn) m.setForce(wallForce3.x * 90, wallForce3.y * 90);
		}
	}

	public void initEnvironment() {
		ViscosityParser visc = new ViscosityParser();
		COMParser com = new COMParser();
		WallParser wall = new WallParser();
		GravityParser grav = new GravityParser();

		viscMag = visc.returnViscosity(environmentString);
		comList = com.returnCOM(environmentString);
		wallList = wall.returnWalls(environmentString);
		gravityList = grav.returnGravity(environmentString);

	}

	/*
	 * Both initAssembly() and clearAssembly() below are called in
	 * toggleAssembly(), which is called in doFrame()
	 */

	public void initAssembly(String assembly) {
		FixedParser fixed = new FixedParser();
		MassParser mass = new MassParser();
		totalFixedMasses = fixed.returnFixedMasses(assembly);
		totalMasses = mass.returnMasses(assembly);
		MuscleParser muscle = new MuscleParser(totalMasses, totalFixedMasses);
		SpringParser spring = new SpringParser(totalMasses, totalFixedMasses);
		totalMuscles = muscle.returnMuscles(assembly);
		totalSprings = spring.returnSprings(assembly);
	}

	public void clearAssembly() {

		for (Spring s : totalSprings) {
			s.terminate();
		}
		for (Muscle mu : totalMuscles) {
			mu.terminate();
		}
		for (Mass m : totalMasses) {
			m.remove();
		}
		for (FixedMass fm : totalFixedMasses) {
			fm.remove();
		}

		totalFixedMasses.clear();
		totalMasses.clear();
		totalMuscles.clear();
		totalSprings.clear();
	}

	public void toggleAssembly() {
		if (getKey('N')) {

			FileChooser fc = new FileChooser("assembly");
			fc.setVisible(true);
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			initAssembly(fc.getAssemblyString());
			clearKey('N');
		}

		if (getKey('C')) {
			clearAssembly();
			clearKey('C');
		}

	}

	public void mouseAction()	{
		if(getMouseButton(1) && totalMasses.size()>0)	{
			if(!mouseMassCreated)	{
				mouseMass = new Mass("mouseMass",getMousePos().x,getMousePos().y,0,0,0);
				closest = closestMass(mouseMass);
				double dist = getDist(mouseMass,closest);
				mouseSpring = new Spring(mouseMass,closest,dist,.5);
				mouseMassCreated = true;
			}
			else	{
				mouseMass.setPos(getMousePos().x,getMousePos().y);
			}
		}
		if(!getMouseButton(1) && mouseMassCreated)	{
			mouseSpring.terminate();
			mouseMass.remove();
			mouseMassCreated = false;
		}

	}

	public Mass closestMass(Mass m1){
		Mass closest = totalMasses.get(0);
		for(Mass m : totalMasses)
			if(getDist(m1,m) < getDist(m1,closest))
				closest = m;
		return closest;
	}

	public double getDist(Mass m1, Mass m2)	{
		double dx = m2.getMassX() - m1.getMassX();
		double dy = m2.getMassY() - m1.getMassY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	public void toggleForces() {
		// Toggle Gravity
		if (getKey('G')) {
			isGravityOn = !isGravityOn;
			clearKey('G');
		}
		// Toggle Viscosity
		if (getKey('V')) {
			isViscosityOn = !isViscosityOn;
			clearKey('V');
		}
		// Toggle Center of Mass
		if (getKey('M')) {
			isCOMOn = !isCOMOn;
			clearKey('M');
		}
		// Toggle Walls (1=Top,2=Right,3=Bottom,4=Left)
		if (getKey('1')) {
			isTopWallOn = !isTopWallOn;
			clearKey('1');
		}
		if (getKey('2')) {
			isRightWallOn = !isRightWallOn;
			clearKey('2');
		}
		if (getKey('3')) {
			isBottomWallOn = !isBottomWallOn;
			clearKey('3');
		}
		if (getKey('4')) {
			isLeftWallOn = !isLeftWallOn;
			clearKey('4');
		}
		// Toggle Muscle Amplitudes ('-' = decrease, '=' = increase)
		if (getKey('-')) {
			for (Muscle m : totalMuscles) {
				m.decreaseAmp();
			}
			clearKey('-');
		}
		if (getKey('=')) {
			for (Muscle m : totalMuscles) {
				m.increaseAmp();
			}
			clearKey('=');
		}
		// Change size of walled area
		if (getKey(KeyEvent.VK_UP)) {
			for (WallSetup ws : totalWalls)
				ws.changeWall(-10);
			clearKey(KeyEvent.VK_UP);
		}
		if (getKey(KeyEvent.VK_DOWN)) {
			for (WallSetup ws : totalWalls) 
				ws.changeWall(10);
			clearKey(KeyEvent.VK_DOWN);
		}
		clearLastKey();
	}

	/**
	 * createSpringies() is called in the FileChooser. 
	 * Environment is the XML Environment string.
	 */

	public static void createSpringies(String environment) {
		final Springies sp = new Springies(environment);

		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(sp, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);
	}
}