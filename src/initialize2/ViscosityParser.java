package initialize2;

public class ViscosityParser extends EnvironmentParser	{
	
	public ViscosityParser()	{
		myObjectType = "viscosity";
		String[] attributeArray = {"magnitude"};
		for(String str:attributeArray){
			myAttributes.add(str);
		}
	}
	
	/*There is only one value for viscosity*/
	public double returnViscosity(String xml){
		readFile(xml);
		return objects.get(0);
	}

}
