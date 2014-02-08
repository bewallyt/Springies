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

import simulation.FixedMass;

public class FixedParser {

	public List<FixedMass> createFixedMasses(String xmlFile) {

		List<FixedMass> fixedMasses = new ArrayList<FixedMass>();

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

			NodeList nodes = doc.getElementsByTagName("fixed");

			for (int i = 0; i < nodes.getLength(); i++) {

				ArrayList<Double> fixedList = new ArrayList<Double>();

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					fixedList
							.add(Double.parseDouble(element.getAttribute("x")));
					fixedList
							.add(Double.parseDouble(element.getAttribute("y")));

					FixedMass tempFixed = new FixedMass(
							element.getAttribute("id"), fixedList.get(0),
							fixedList.get(1));

					fixedMasses.add(tempFixed);
				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		return Collections.unmodifiableList(fixedMasses);

	}
}
