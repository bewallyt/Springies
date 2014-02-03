package simulation;

public class FixedMass extends Mass {

	/* Constructor */
	public FixedMass(String id, double xCoord, double yCoord) {
<<<<<<< HEAD
		super(id, 10, 10, xCoord, yCoord, 0, 0, 0);
		x = xCoord;
		y = yCoord;
=======
		super(id, 1.0, 1.0, xCoord, yCoord, 0, 0, 0);
		// Changed x and y to this.x and this.y
		this.x = xCoord;
		this.y = yCoord;
>>>>>>> 4b96fb4a6df493205d4241524869c030e41d0c92
	}
	
	public void move(){}

}
