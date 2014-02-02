package simulation;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.JGObject;


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
		
		float xvel = (float) xv;
		float yvel = (float) yv;
		Vec2 velocity = new Vec2(xvel,yvel);
		this.myBody.setLinearVelocity(velocity);
		/**
		this.xspeed = xv;
		this.yspeed = yv;
		*/
		
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
		
		//Move object with mouse when mouse is pressed in bounds of object
		if(myEngine.getMouseButton(1) && (myEngine.getMouseX() >= x-massWidth*2 && 
				myEngine.getMouseX() <= x+massWidth*2) && 
				(myEngine.getMouseY() >= y-massHeight*2 &&
				myEngine.getMouseY() <= y+massHeight*2))	{
			mouseMove();
		}
		else	{
			
		}
	}

	public void mouseMove()	{
		x = myEngine.getMouseX()-massWidth/2;
		y = myEngine.getMouseY()-massHeight/2;
		float xvel = (float) ((x-prevmousex)/10.0);
		float yvel = (float) ((y-prevmousey)/10.0);
		Vec2 velocity = new Vec2(xvel,yvel);
		/**
		xspeed = (x-prevmousex)/10.0;
		yspeed = (y-prevmousey)/10.0;
		*/
		prevmousex = myEngine.getMouseX();
		prevmousey = myEngine.getMouseY();
	}
	
}
	
	
	
	
