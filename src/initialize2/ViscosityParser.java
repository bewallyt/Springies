package initialize2;

import java.util.Arrays;

public class ViscosityParser extends EnvironmentParser	{
	
	public ViscosityParser()	{
		myObjectType = "viscosity";
		String[] attributeArray = {"magnitude"};
		myAttributes = Arrays.asList(attributeArray);
	}

}
