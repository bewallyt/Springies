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

public class EnvironmentParser {

	/* Initializes Maps and Lists to hold data from XML */

	public List<Double> readGravity(String xmlFile) {

		List<Double> gravityList = new ArrayList<Double>();

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

			NodeList nodes = doc.getElementsByTagName("gravity");

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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return gravityList;

	}

	public double readViscosity(String xmlFile) {

		double viscosityValue = 0;

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

			NodeList nodes = doc.getElementsByTagName("viscosity");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					viscosityValue = Double.parseDouble(element
							.getAttribute("magnitude"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return viscosityValue;
	}

	public List<Double> readCOM(String xmlFile) {

		List<Double> centermassList = new ArrayList<Double>();

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

			NodeList nodes = doc.getElementsByTagName("centermass");

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
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return centermassList;

	}

	public List<List<Double>> readWall(String xmlFile) {

		List<List<Double>> wallList = new ArrayList<List<Double>>();

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

			NodeList nodes = doc.getElementsByTagName("wall");

			for (int i = 0; i < nodes.getLength(); i++) {
				ArrayList<Double> nestedWallList = new ArrayList<Double>();
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return wallList;
	}
}
