package initialize2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallParser extends AbstractParser	{
	
	public WallParser()	{
		myObjectType = "wall";
		String[] attributeArray = {"id","magnitude","exponent"};
		myAttributes = Arrays.asList(attributeArray);
	}
	
	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {}
	
	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {
		ArrayList<Object> wallList = new ArrayList<Object>();
		Double id = Double.parseDouble((String) data.get(0)); 
		Double mag = Double.parseDouble((String) data.get(1));
		Double exp = Double.parseDouble((String) data.get(2));
		wallList.add(id);
		wallList.add(mag);
		wallList.add(exp);
		objects.add(wallList);
	}
}
