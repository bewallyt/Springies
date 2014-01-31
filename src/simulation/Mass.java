package simulation;

import jboxGlue.PhysicalObjectRect;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

//import package SpringAndMass;

public class Mass extends PhysicalObjectRect{

	//private int myMass;
	//These integers are used to indicate where the mass is connected
	//to the spring (ie hook points)
	private double hookX,hookY;
			
	//height and width
	private double myHeight,myWidth;
	
	//constructor for our Mass
	public Mass(int collisionId,double width,double height, 
			double xCoord, double yCoord, double mass,double xVelocity,
			double yVelocity) {
		super("mass", collisionId, JGColor.black, width, height, mass);
		
		//Height and width of mass
		myHeight = height;
		myWidth = width;
		
		//Set Mass position
		this.x = xCoord;
		this.y = yCoord;
		
		//Set initial velocities
		this.xspeed = xVelocity;
		this.yspeed = yVelocity;
		
		//Default: hook point defined as the midsections of the
		//top of the mass
		hookX = x;
		hookY = y+height/2.0;
	}
	
	//Method to attach the mass to the spring
	public void attachToSpring(double springHookX,double springHookY){
		x = springHookX;
		y = springHookY+myHeight/2;
	}
	
	
	
	
	
	
	
}
