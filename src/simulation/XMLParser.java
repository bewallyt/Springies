package simulation;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

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

					Node node = nodes.item(i);
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("id: " + element.getAttribute("id"));
						System.out.println("x: " + element.getAttribute("x"));
						System.out.println("y: " + element.getAttribute("y"));

						if (element.getAttribute("vx").length() == 0) {
							double vx = 0;
							System.out.println("vx: " + vx);
						} else {
							System.out.println("vx: "
									+ element.getAttribute("vx"));
						}

						if (element.getAttribute("vy: ").length() == 0) {
							double vy = 0;
							System.out.println("vy: " + vy);
						} else {
							System.out.println("vy: "
									+ element.getAttribute("vy"));
						}

					}
				}

			}

			if (type.equals("fixed")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("id: " + element.getAttribute("id"));
						System.out.println("x: " + element.getAttribute("x"));
						System.out.println("y: " + element.getAttribute("y"));

					}
				}

			}

			else if (type.equals("spring")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("a: " + element.getAttribute("a"));
						System.out.println("b: " + element.getAttribute("b"));
						System.out.println("restlength: "
								+ element.getAttribute("restlength"));
						
						if (element.getAttribute("constant").length() == 0) {
							double springConstant = 1;
							System.out.println("constant: "
									+ springConstant);
						} else {
							System.out.println("constant: "
									+ element.getAttribute("constant"));
						}

					}

				}
			}

			else if (type.equals("muscle")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						System.out.println("a: " + element.getAttribute("a"));
						System.out.println("b: " + element.getAttribute("b"));
						System.out.println("restlength: "
								+ element.getAttribute("restlength"));
						
						if (element.getAttribute("constant").length() == 0) {
							double muscleConstant = 1;
							System.out.println("constant: "
									+ muscleConstant);
						} else {
							System.out.println("constant: "
									+ element.getAttribute("constant"));
						}
						
						System.out.println("amplitude: "
								+ element.getAttribute("amplitude"));

					}

				}
			}

			else if (type.equals("gravity")) {

				for (int i = 0; i < nodes.getLength(); i++) {

					Node node = nodes.item(i);
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

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
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

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
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

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
					System.out.println("\nCurrent Element :"
							+ node.getNodeName());

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

}
