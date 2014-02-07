package initialize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnvironmentParser{

	/* Initializes Maps and Lists to hold data from XML */

	private static List<List<Object>> wallList = new ArrayList<List<Object>>();
	private static List<Double> centermassList = new ArrayList<Double>();
	private static List<Double> gravityList = new ArrayList<Double>();
	private static double viscosityValue;

	public List<List<Object>> getWallList() {
		return wallList;
	}

	public List<Double> getGravityList() {
		return gravityList;
	}

	public List<Double> getCentermassList() {
		return centermassList;
	}

	public double getViscosityValue() {
		return viscosityValue;
	}

	public void parseEnvironment(String myXMLFile) {

		readXML(myXMLFile, "gravity");
		readXML(myXMLFile, "viscosity");
		readXML(myXMLFile, "centermass");
		readXML(myXMLFile, "wall");
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
			 * Reads environment in depending on type: gravity, viscosity,
			 * centerofmass, wall
			 */

			NodeList nodes = doc.getElementsByTagName(type);

			if (type.equals("gravity")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						gravityList.add(Double.parseDouble(element
								.getAttribute("direction")));
						gravityList.add(Double.parseDouble(element
								.getAttribute("magnitude")));

					}
				}
			}

			else if (type.equals("viscosity")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						viscosityValue = Double.parseDouble(element
								.getAttribute("magnitude"));
					}
				}
			}

			else if (type.equals("centermass")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						centermassList.add(Double.parseDouble(element
								.getAttribute("magnitude")));
						centermassList.add(Double.parseDouble(element
								.getAttribute("exponent")));
					}
				}
			}

			else if (type.equals("wall")) {

				for (int i = 0; i < nodes.getLength(); i++) {
					ArrayList<Object> nestedWallList = new ArrayList<Object>();
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						nestedWallList.add(Double.parseDouble(element
								.getAttribute("id")));
						nestedWallList.add(Double.parseDouble(element
								.getAttribute("magnitude")));
						nestedWallList.add(Double.parseDouble(element
								.getAttribute("exponent")));
					}
					wallList.add(nestedWallList);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
