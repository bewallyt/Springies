package initialize2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulation.Mass;

public class MassParser extends AbstractParser{

	private double MASS_RADIUS = 5;
	
	public MassParser(){
		myObjectType = "mass";
		String[] attributeArray = {"id", "x", "y", "vx", "vy", "mass"};
		myAttributes = Arrays.asList(attributeArray);
	}

	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {
		// TODO Auto-generated method stub
		Mass tempMass;
		String id = (String) data.get(0);
		double x = (Double) data.get(1);
		double y = (Double) data.get(2);
		double xv = (Double) data.get(3);
		double yv = (Double) data.get(4);
		double mass = (Double) data.get(5);
		tempMass = new Mass(id,MASS_RADIUS,x,y,xv,yv,mass);
		objects.add(tempMass);
	}

	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {
		if(att.equals("vx") || att.equals("vy"))	{
			data.add(0.0);
		}
		else if(att.equals("mass"))	{
			data.add(1.0);
		}
	}
}
