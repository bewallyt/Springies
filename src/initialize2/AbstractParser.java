package initialize2;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AbstractParser {
	
	protected String myObjectType;
	protected List<String> myAttributes;
	
	/*Initialize object type and attributes to look for when parsing*/
	public AbstractParser()	{
		myObjectType = "";
		myAttributes = new ArrayList<String>();
	}
	
	/*Read the desired file and extract pertinent information*/
	public List<Object> readFile(String xmlFile)	{
		List<Object> objects = new ArrayList<Object>();
		try {
			/* Initializes XML Reader */
			File xmlCode = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlCode);
			doc.getDocumentElement().normalize();

			/**
			 * Reads model in depending on type: mass(es), spring(s), fixed,
			 * muscle.
			 */

			NodeList nodes = doc.getElementsByTagName(myObjectType);
			objects = getObjects(nodes);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Collections.unmodifiableList(objects);
	}

	/*Return a list of objects of desired type*/
	public List<Object> getObjects(NodeList nodes) {
		List<Object> objects = new ArrayList<Object>();
		for (int i = 0; i < nodes.getLength(); i++) {
			ArrayList<Object> data = new ArrayList<Object>();
			Node node = nodes.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(String att:myAttributes){
					if(element.getAttribute(att).length() == 0)	{
						this.getDefaultValue(att,data);
					}
					else{
						data.add(element.getAttribute(att));
					}
				}
				addObject(data, objects);
			}

		}
		return objects;
	}

	abstract void getDefaultValue(String att, ArrayList<Object> data);
	
	abstract void addObject(ArrayList<Object> data, List<Object> objects);

}
