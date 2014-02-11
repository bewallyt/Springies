package initialize2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulation.Mass;

public class MassParser extends AbstractParser{

	private double MASS_RADIUS = 5;
	
	public MassParser(){
		myObjectType = "mass";
		String[] attributeArray = {"id", "x", "y", "vx", "vy", "mass"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}

	public List<Mass> returnMasses(String xml)	{
		readFile(xml);
		List<Mass> output = new ArrayList<Mass>();
		for(List<String> data:objects)	{
			Mass tempMass;
			String id = (String) data.get(0);
			double x = Double.parseDouble((String) data.get(1));
			double y = Double.parseDouble((String) data.get(2)) + 200;
			double xv = Double.parseDouble((String) data.get(3));
			double yv = Double.parseDouble((String) data.get(4));
			double mass = Double.parseDouble((String) data.get(5));
			tempMass = new Mass(id,MASS_RADIUS,x,y,mass,xv,yv);
			tempMass.setPos(x,y - 200);
			//For Test
			tempMass.setForce(80, -100);
			output.add(tempMass);
		}
		return Collections.unmodifiableList(output);
	}
	
	@Override
	void getDefaultValue(String att, ArrayList<String> data) {
		if(att.equals("vx") || att.equals("vy"))	{
			data.add("0.0");
		}
		else if(att.equals("mass"))	{
			data.add("1.0");
		}
	}
}
