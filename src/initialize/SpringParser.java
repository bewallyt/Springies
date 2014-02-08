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
import simulation.Spring;

public class SpringParser {

	public List<Spring> createSprings(String xmlFile, List<Mass> masses) {

		List<Spring> springs = new ArrayList<Spring>();

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

			NodeList nodes = doc.getElementsByTagName("spring");

			for (int i = 0; i < nodes.getLength(); i++) {
				ArrayList<Object> springList = new ArrayList<Object>();
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					springList.add(element.getAttribute("a"));
					springList.add(element.getAttribute("b"));

					if (element.getAttribute("restlength").length() == 0) {
						springList.add(1.0);

					} else {
						springList.add(Double.parseDouble(element
								.getAttribute("restlength")));
					}

					if (element.getAttribute("constant").length() == 0) {
						springList.add(1.0);

					} else {
						springList.add(Double.parseDouble(element
								.getAttribute("constant")));
					}

					// new Mass(sbfusfdsdf)

					Mass m1 = findMass((String) springList.get(0), masses);
					Mass m2 = findMass((String) springList.get(1), masses);

					Spring tempSpring = new Spring(m1, m2,
							(Double) springList.get(2),
							(Double) springList.get(3));

					springs.add(tempSpring);

				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Collections.unmodifiableList(springs);
	}

	public Mass findMass(String massID, List<Mass> mass) {
		for (Mass m : mass) {
			if (m.getID().equals(massID)) {
				return m;
			}
		}
		return null;
	}

}
