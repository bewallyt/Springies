package simulation;

import jboxGlue.PhysicalObjectRect;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Mass extends PhysicalObjectRect{

	/* Hooks onto Spring. */
	private double hookX,hookY;
			
	/* Object Dimensions. */
	private double massHeight, massWidth;
	
	/* Constructor */
	public Mass(int collisionId,double width,double height, 
			double xCoord, double yCoord, double mass,double xv,
			double yv) {
		super("mass", collisionId, JGColor.black, width, height, mass);
		
		massHeight = height;
		massWidth = width;
		
		this.x = xCoord;
		this.y = yCoord;
		
		this.xspeed = xv;
		this.yspeed = yv;
		
		//Should mass hooks be at the center of the mass?
		//hookX = x + width/2.0;
		hookX = x;
		hookY = y+height/2.0;
	}
	
//	public void attachToSpring(double springHookX,double springHookY){
//		x = springHookX;
//		y = springHookY+myHeight/2;
//	}
	
	
}
	
	
	
	
