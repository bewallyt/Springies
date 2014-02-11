package simulation;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;

public class WallSetup extends PhysicalObjectRect {

	final double WALL_MARGIN = 10;
	final double WALL_THICKNESS = 10;
	final int increase = 10;
	private String side;


	public WallSetup(String side, double width, double thickness) {
		super("wall", 2, JGColor.green, width, thickness);
		this.side = side;
	}

	public void decreaseWall() {
		Vec2 position = myBody.getPosition();
		if (side.equals("top"))
			this.setPos(position.x, position.y + increase);
		if (side.equals("bottom"))
			this.setPos(position .x, position.y - increase);
		if (side.equals("left"))
			this.setPos(position.x + increase, position.y);
		if (side.equals("right"))
			this.setPos(position.x - increase, position.y);
	}
	
	public void increaseWall() {
		Vec2 position = myBody.getPosition();
		if (side.equals("top"))
			this.setPos(position.x, position.y - increase);
		if (side.equals("bottom"))
			this.setPos(position .x, position.y + increase);
		if (side.equals("left"))
			this.setPos(position.x - increase, position.y);
		if (side.equals("right"))
			this.setPos(position.x + increase, position.y);
	}
	

}
