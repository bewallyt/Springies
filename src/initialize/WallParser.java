package initialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WallParser extends AbstractParser	{
	
	public WallParser()	{
		myObjectType = "wall";
		String[] attributeArray = {"id","magnitude","exponent"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}
	
	public List<List<Double>> returnWalls(String xml)	{
		readFile(xml);
		List<List<Double>> output = new ArrayList<List<Double>>();
		for(List<String> data:objects)	{
			List<Double> wallList = new ArrayList<Double>();
			Double id = Double.parseDouble((String) data.get(0)); 
			Double mag = Double.parseDouble((String) data.get(1));
			Double exp = Double.parseDouble((String) data.get(2));
			wallList.add(id);
			wallList.add(mag);
			wallList.add(exp);
			output.add(wallList);
		}
		return Collections.unmodifiableList(output);
	}
	
	@Override
	void getDefaultValue(String att, ArrayList<String> data) {}

}
