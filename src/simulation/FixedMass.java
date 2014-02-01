package simulation;

public class FixedMass extends Mass {

	/* Constructor */
	public FixedMass(int collisionId, double xCoord, double yCoord) {
		super(collisionId, 1.0, 1.0, xCoord, yCoord, 0, 0, 0);
		x = xCoord;
		y = yCoord;
	}

}
