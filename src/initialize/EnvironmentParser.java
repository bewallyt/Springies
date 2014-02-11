package initialize;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnvironmentParser extends AbstractParser{
	
	protected List<Double> objects;
	
	@Override
	public void getObjects(NodeList nodes) {
		objects = new ArrayList<Double>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(String att:myAttributes){
					objects.add(Double.parseDouble(element.getAttribute(att)));
				}
			}

		}
	}
	
	/*Shouldn't execute this code for gravity*/
	@Override
	void getDefaultValue(String att, ArrayList<String> data) {}
}
