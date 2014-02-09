package simulation;

public class FixedMass extends Mass {
	
	String id;

	/* Constructor */
	public FixedMass(String id, double xCoord, double yCoord) {

		super(id, 1.0, 1.0, xCoord, yCoord, 0, 0);
		this.id = id;
		this.x = xCoord;
		this.y = yCoord;
	}

	public void move() {
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

}
