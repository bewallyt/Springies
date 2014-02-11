package initialize;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AbstractParser {
	
	protected String myObjectType;
	protected ArrayList<String> myAttributes;
	protected List<List<String>> objects;
	
	/*Initialize object type and attributes to look for when parsing*/
	public AbstractParser()	{
		myObjectType = "";
		myAttributes = new ArrayList<String>();
		objects = new ArrayList<List<String>>();
	}
	
	/*Read the desired file and extract pertinent information*/
	public void readFile(String xmlFile)	{
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
			getObjects(nodes);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*Return a list of objects of desired type*/
	public void getObjects(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			ArrayList<String> data = new ArrayList<String>();
			Node node = nodes.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(String att:myAttributes){
					if(element.getAttribute(att).length() == 0)	{
						this.getDefaultValue(att,data);
					}
					else{
						data.add((String) element.getAttribute(att));
					}
				}
				objects.add(data);
			}

		}

	}

	abstract void getDefaultValue(String att, ArrayList<String> data);
	

}
