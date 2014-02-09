package initialize2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulation.FixedMass;

public class FixedParser extends AbstractParser{

	public FixedParser(){
		myObjectType = "fixed";
		String[] attributeArray = {"id", "x", "y"};
		myAttributes = Arrays.asList(attributeArray);
	}

	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {
		FixedMass tempFixedMass;
		String id = (String) data.get(0);
		double x = (Double) data.get(1);
		double y = (Double) data.get(2);
		tempFixedMass = new FixedMass(id,x,y);
		objects.add(tempFixedMass);
	}

	/*Shouldn't have to run this code for fixed masses*/
	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {}
}
