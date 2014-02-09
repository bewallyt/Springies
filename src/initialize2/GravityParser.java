package initialize2;

import java.util.List;

public class GravityParser extends EnvironmentParser	{
	
	public GravityParser(){
		myObjectType = "gravity";
		String[] attributeArray = {"direction","magnitude"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}
	
	public List<Double> returnGravity(String xml)	{
		readFile(xml);
		return objects;
	}
}
