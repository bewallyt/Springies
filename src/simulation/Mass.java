package simulation;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;


public class Mass extends PhysicalObjectRect{

	/* Hooks onto Spring. */
	protected double hookX, hookY;
			
	/* Object Dimensions. */
	private double massHeight, massWidth;
	
	/* Constructor */
	public Mass(String id,double width,double height, 
			double xCoord, double yCoord, double mass,double xv,
			double yv) {
		super(id, 0, JGColor.blue, width, height, mass);
		
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
	
	double prevmousex = 0, prevmousey = 0;
	
	public void move()	{
		
		if(myEngine.getMouseButton(1) && (myEngine.getMouseX() >= x-massWidth*2 && 
				myEngine.getMouseX() <= x+massWidth*2) && 
				(myEngine.getMouseY() >= y-massHeight*2 &&
				myEngine.getMouseY() <= y+massHeight*2))	{
			x = myEngine.getMouseX()-massWidth/2;
			y = myEngine.getMouseY()-massHeight/2;
			xspeed = (x-prevmousex)/10.0;
			yspeed = (y-prevmousey)/10.0;
			prevmousex = myEngine.getMouseX();
			prevmousey = myEngine.getMouseY();
		}
	}
	
	
	
}
	
	
	
	
