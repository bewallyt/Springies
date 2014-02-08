package initialize;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import simulation.Mass;

public class MassParser {

	public List<Mass> createMasses(String xmlFile) {

		List<Mass> masses = new ArrayList<Mass>();

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

			NodeList nodes = doc.getElementsByTagName("mass");

			for (int i = 0; i < nodes.getLength(); i++) {

				ArrayList<Double> massList = new ArrayList<Double>();

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					massList.add(Double.parseDouble(element.getAttribute("x")));
					massList.add(Double.parseDouble(element.getAttribute("y")));

					if (element.getAttribute("vx").length() == 0) {
						massList.add(0.0);

					} else {
						massList.add(Double.parseDouble(element
								.getAttribute("vx")));
					}

					if (element.getAttribute("vy").length() == 0) {
						massList.add(0.0);

					} else {
						massList.add(Double.parseDouble(element
								.getAttribute("vy")));
					}

					if (element.getAttribute("mass").length() == 0) {
						massList.add(1.0);

					} else {
						massList.add(Double.parseDouble(element
								.getAttribute("mass")));
					}

					// Change 50 to massList.get(4) for weight. Need to fix
					// forces first, however.

					Mass tempMass = new Mass(element.getAttribute("id"), 5,
							massList.get(0), massList.get(1), 50,
							massList.get(2), massList.get(3));
					
					tempMass.setPos(massList.get(0), massList.get(1));
					masses.add(tempMass);
				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		return Collections.unmodifiableList(masses);

	}
}
