package initialize2;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnvironmentParser extends AbstractParser{
	@Override
	public List<Object> getObjects(NodeList nodes) {
		List<Object> objects = new ArrayList<Object>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(String att:myAttributes){
					objects.add(element.getAttribute(att));
				}
			}

		}
		return objects;
	}
	
	/*Shouldn't execute this code for gravity*/
	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {}
	
	/*Shouldn't execute this code for gravity*/
	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {}
}
