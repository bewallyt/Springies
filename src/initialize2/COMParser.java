package initialize2;

import java.util.Arrays;

public class COMParser extends GravityParser{
	
	public COMParser(){
		myObjectType = "centermass";
		String[] attributeArray = {"magnitude","exponent"};
		myAttributes = Arrays.asList(attributeArray);
	}
	
}
