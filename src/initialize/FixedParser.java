package initialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulation.FixedMass;

public class FixedParser extends AbstractParser{

	public FixedParser(){
		myObjectType = "fixed";
		String[] attributeArray = {"id", "x", "y"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}

	public List<FixedMass> returnFixedMasses(String xml)	{
		readFile(xml);
		List<FixedMass> output = new ArrayList<FixedMass>();
		for(List<String> data:objects)	{
			FixedMass tempFixedMass;
			String id = (String) data.get(0);
			double x = Double.parseDouble((String) data.get(1));
			double y = Double.parseDouble((String) data.get(2));
			tempFixedMass = new FixedMass(id,x,y);
			output.add(tempFixedMass);
		}
		return Collections.unmodifiableList(output);
	}

	/*Shouldn't have to run this code for fixed masses*/
	@Override
	void getDefaultValue(String att, ArrayList<String> data) {}
}
