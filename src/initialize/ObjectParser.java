package initialize;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectParser{

	int massCounter = 0;

	/* Initializes Maps and Lists to hold data from XML */

	private static Map<String, List<Double>> massMap = new HashMap<String, List<Double>>();
	private static Map<String, List<Double>> fixedMap = new HashMap<String, List<Double>>();
	private static List<List<Object>> springList = new ArrayList<List<Object>>();
	private static List<List<Object>> muscleList = new ArrayList<List<Object>>();

	public Map<String, List<Double>> getMassMap() {
		return massMap;
	}

	public Map<String, List<Double>> getFixedMap() {
		return fixedMap;
	}

	public List<List<Object>> getSpringList() {
		return springList;
	}

	public List<List<Object>> getMuscleList() {
		return muscleList;
	}

	public void parseObject(String xmlFile) {

		readXML(xmlFile, "mass");
		readXML(xmlFile, "fixed");
		readXML(xmlFile, "spring");
		readXML(xmlFile, "muscle");
		
	}

	public void readXML(String xmlFile, String type) {
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

			NodeList nodes = doc.getElementsByTagName(type);

			if (type.equals("mass")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					ArrayList<Double> massList = new ArrayList<Double>();
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						massList.add(Double.parseDouble(element
								.getAttribute("x")));
						massList.add(Double.parseDouble(element
								.getAttribute("y")) - 200);

						if (element.getAttribute("vx").length() == 0) {
							double vx = 0;
							massList.add(vx);

						} else {
							massList.add(Double.parseDouble(element
									.getAttribute("vx")));
						}

						if (element.getAttribute("vy: ").length() == 0) {
							double vy = 0;
							massList.add(vy);

						} else {
							massList.add(Double.parseDouble(element
									.getAttribute("vy")));
						}
						massMap.put(element.getAttribute("id"), massList);
					}
				}

			}

			if (type.equals("fixed")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					ArrayList<Double> fixedList = new ArrayList<Double>();
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						fixedList.add(Double.parseDouble(element
								.getAttribute("x")));
						fixedList.add(Double.parseDouble(element
								.getAttribute("y")));

						fixedMap.put(element.getAttribute("id"), fixedList);
					}
				}
			}

			else if (type.equals("spring")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					ArrayList<Object> nestedSpringList = new ArrayList<Object>();
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						nestedSpringList.add(element.getAttribute("a"));
						nestedSpringList.add(element.getAttribute("b"));
						nestedSpringList.add(Double.parseDouble(element
								.getAttribute("restlength")));

						if (element.getAttribute("constant").length() == 0) {
							double springConstant = 1.0;
							nestedSpringList.add(springConstant);

						} else {
							nestedSpringList.add(Double.parseDouble(element
									.getAttribute("constant")));
						}
						springList.add(nestedSpringList);
					}
				}
			}

			else if (type.equals("muscle")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					ArrayList<Object> nestedMuscleList = new ArrayList<Object>();
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						nestedMuscleList.add(element.getAttribute("a"));
						nestedMuscleList.add(element.getAttribute("b"));
						nestedMuscleList.add(Double.parseDouble(element
								.getAttribute("restlength")));

						if (element.getAttribute("constant").length() == 0) {
							double muscleConstant = 1;
							nestedMuscleList.add(muscleConstant);

						} else {
							nestedMuscleList.add(Double.parseDouble(element
									.getAttribute("constant")));
						}

						nestedMuscleList.add(Double.parseDouble(element
								.getAttribute("amplitude")));

						muscleList.add(nestedMuscleList);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}