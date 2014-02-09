package initialize2;

import java.util.Arrays;

public class GravityParser extends EnvironmentParser	{
	
	public GravityParser(){
		myObjectType = "gravity";
		String[] attributeArray = {"direction","magnitude"};
		myAttributes = Arrays.asList(attributeArray);
	}

}
