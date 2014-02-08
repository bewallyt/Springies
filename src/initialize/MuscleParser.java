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
import simulation.Muscle;

public class MuscleParser {

	public List<Muscle> createMuscles(String xmlFile, List<Mass> masses) {

		List<Muscle> muscles = new ArrayList<Muscle>();

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

			NodeList nodes = doc.getElementsByTagName("muscle");

			for (int i = 0; i < nodes.getLength(); i++) {
				ArrayList<Object> muscleList = new ArrayList<Object>();
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					muscleList.add(element.getAttribute("a"));
					muscleList.add(element.getAttribute("b"));

					if (element.getAttribute("restlength").length() == 0) {
						muscleList.add(1.0);

					} else {
						muscleList.add(Double.parseDouble(element
								.getAttribute("restlength")));
					}

					if (element.getAttribute("constant").length() == 0) {
						muscleList.add(1.0);

					} else {
						muscleList.add(Double.parseDouble(element
								.getAttribute("constant")));
					}

					if (element.getAttribute("amplitude").length() == 0) {
						muscleList.add(1.0);

					} else {
						muscleList.add(Double.parseDouble(element
								.getAttribute("amplitude")));
					}

					Mass m1 = findMass((String) muscleList.get(0), masses);
					Mass m2 = findMass((String) muscleList.get(1), masses);

					Muscle tempMuscle = new Muscle(m1, m2,
							(Double) muscleList.get(2),
							(Double) muscleList.get(3),
							(Double) muscleList.get(4));

					muscles.add(tempMuscle);
				}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Collections.unmodifiableList(muscles);
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
