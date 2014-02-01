package simulation;

public class FixedMass extends Mass {

	/* Constructor */
	public FixedMass(String id, double xCoord, double yCoord) {
		super(id, 1.0, 1.0, xCoord, yCoord, 0, 0, 0);
		x = xCoord;
		y = yCoord;
	}

}
