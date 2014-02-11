package initialize;

import java.util.List;

public class COMParser extends EnvironmentParser	{
	
	public COMParser(){
		myObjectType = "centermass";
		String[] attributeArray = {"magnitude","exponent"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}
	
	public List<Double> returnCOM(String xml){
		readFile(xml);
		return objects;
	}
}