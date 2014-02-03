package simulation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	private HashMap<String, ArrayList<Double>> massMap = new HashMap<String, ArrayList<Double>>();
	private HashMap<String, ArrayList<Double>> fixedMap = new HashMap<String, ArrayList<Double>>();
	private ArrayList<ArrayList<Object>> springList = new ArrayList<ArrayList<Object>>();
	private ArrayList<ArrayList<Object>> muscleList = new ArrayList<ArrayList<Object>>();

	int massCounter = 0;
	
	public HashMap<String, ArrayList<Double>> getMassMap() {
		return massMap;
	}

	public void setMassMap(HashMap<String, ArrayList<Double>> massMap) {
		this.massMap = massMap;
	}

	public HashMap<String, ArrayList<Double>> getFixedMap() {
		return fixedMap;
	}

	public void setFixedMap(HashMap<String, ArrayList<Double>> fixedMap) {
		this.fixedMap = fixedMap;
	}

	public ArrayList<ArrayList<Object>> getSpringList() {
		return springList;
	}

	public void setSpringList(ArrayList<ArrayList<Object>> springList) {
		this.springList = springList;
	}

	public ArrayList<ArrayList<Object>> getMuscleList() {
		return muscleList;
	}

	public void setMuscleList(ArrayList<ArrayList<Object>> muscleList) {
		this.muscleList = muscleList;
	}

	public void readXMLObject(String xmlFile){
		
		
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
			 * muscle. Reads environment in depending on type: gravity,
			 * viscosity, centermass, wall.
			 */

			NodeList nodes = doc.getElementsByTagName(type);

			if (type.equals("mass")) {
				

				for (int i = 0; i < nodes.getLength(); i++) {
					
					ArrayList<Double> massList = new ArrayList<Double>();


					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
//
//						System.out.println("id: " + element.getAttribute("id"));
//						System.out.println("x: " + element.getAttribute("x"));
//						System.out.println("y: " + element.getAttribute("y"));
						
						massList.add(Double.parseDouble(element.getAttribute("x")));
						massList.add(Double.parseDouble(element.getAttribute("y"))-200);
						
						if (element.getAttribute("vx").length() == 0) {
							double vx = 0;
//							System.out.println("vx: " + vx);
							massList.add(vx);
						} else {
//							System.out.println("vx: "
//									+ element.getAttribute("vx"));
							massList.add(Double.parseDouble(element.getAttribute("vx")));
						}

						if (element.getAttribute("vy: ").length() == 0) {
							double vy = 0;
//							System.out.println("vy: " + vy);
							massList.add(vy);
						} else {
//							System.out.println("vy: "
//									+ element.getAttribute("vy"));
							massList.add(Double.parseDouble(element.getAttribute("vy")));
						}
						
						massMap.put(element.getAttribute("id"), massList);

					}
				}

			}

			if (type.equals("fixed")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					
					ArrayList<Double> fixedList = new ArrayList<Double>();

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
//
//						System.out.println("id: " + element.getAttribute("id"));
//						System.out.println("x: " + element.getAttribute("x"));
//						System.out.println("y: " + element.getAttribute("y"));
						
						fixedList.add(Double.parseDouble(element.getAttribute("x")));
						fixedList.add(Double.parseDouble(element.getAttribute("y")));
						
						fixedMap.put(element.getAttribute("id"), fixedList);

					}
					
				}

			}

			else if (type.equals("spring")) {
				

				for (int i = 0; i < nodes.getLength(); i++) {
					
					ArrayList<Object> nestedSpringList = new ArrayList<Object>();

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

//						System.out.println("a: " + element.getAttribute("a"));
//						System.out.println("b: " + element.getAttribute("b"));
//						System.out.println("restlength: "
//								+ element.getAttribute("restlength"));
						
						nestedSpringList.add(element.getAttribute("a"));
						nestedSpringList.add(element.getAttribute("b"));
						nestedSpringList.add(Double.parseDouble(element.getAttribute("restlength")));
						
						if (element.getAttribute("constant").length() == 0) {
							double springConstant = 1.0;
//							System.out.println("constant: "
//									+ springConstant);
							nestedSpringList.add(springConstant);
						} else {
//							System.out.println("constant: "
//									+ element.getAttribute("constant"));
							nestedSpringList.add(Double.parseDouble(element.getAttribute("constant")));
						}
						
						springList.add(nestedSpringList);

					}
					
//					massCounter ++;
//					System.out.println(massCounter);

				}
			}

			else if (type.equals("muscle")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					
					ArrayList<Object> nestedMuscleList = new ArrayList<Object>();

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

//						System.out.println("a: " + element.getAttribute("a"));
//						System.out.println("b: " + element.getAttribute("b"));
//						System.out.println("restlength: "
//								+ element.getAttribute("restlength"));
						
						nestedMuscleList.add(element.getAttribute("a"));
						nestedMuscleList.add(element.getAttribute("b"));
						nestedMuscleList.add(Double.parseDouble(element.getAttribute("restlength")));
						
						
						if (element.getAttribute("constant").length() == 0) {
							double muscleConstant = 1;
//							System.out.println("constant: "
//									+ muscleConstant);
							nestedMuscleList.add(muscleConstant);
						} else {
//							System.out.println("constant: "
//									+ element.getAttribute("constant"));
							nestedMuscleList.add(Double.parseDouble(element.getAttribute("constant")));
						}
						
//						System.out.println("amplitude: "
//								+ element.getAttribute("amplitude"));
						nestedMuscleList.add(Double.parseDouble(element.getAttribute("amplitude")));
						
						muscleList.add(nestedMuscleList);

					}

				}
			}

			else if (type.equals("gravity")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("direction: "
								+ element.getAttribute("direction"));
						System.out.println("magnitude: "
								+ element.getAttribute("magnitude"));

					}

				}

			}

			else if (type.equals("viscosity")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("magnitude: "
								+ element.getAttribute("magnitude"));

					}

				}

			}

			else if (type.equals("centermass")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("magnitude: "
								+ element.getAttribute("magnitude"));
						System.out.println("exponent: "
								+ element.getAttribute("exponent"));

					}

				}

			}

			else if (type.equals("wall")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
//					System.out.println("\nCurrent Element :"
//							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("id: " + element.getAttribute("id"));
						System.out.println("magnitude: "
								+ element.getAttribute("magnitude"));
						System.out.println("exponent: "
								+ element.getAttribute("exponent"));

					}

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
//    public static void main (String args[])
//    {
//
//    }

}
