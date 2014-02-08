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
import simulation.Mass;
import simulation.Muscle;

public class MuscleParser {

	public List<Muscle> createMuscles(String xmlFile, List<Mass> masses,
			List<FixedMass> fixedMasses) {

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
					Muscle tempMuscle;

					muscleList.add(element.getAttribute("a"));
					muscleList.add(element.getAttribute("b"));

					if (element.getAttribute("restlength").length() == 0) {
						muscleList.add(-1.0);

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
					if (m1 == null) {
						m1 = findFixed((String) muscleList.get(0), fixedMasses);
					}
					Mass m2 = findMass((String) muscleList.get(1), masses);
					if (m2 == null) {
						m2 = findFixed((String) muscleList.get(1), fixedMasses);
					}

					double dx = m2.getMassX() - m1.getMassX();
					double dy = m2.getMassY() - m1.getMassY();
					double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

					m1 = findMass((String) muscleList.get(0), masses);
					m2 = findMass((String) muscleList.get(1), masses);

					if ((Double) muscleList.get(2) == -1.0) {
						tempMuscle = new Muscle(m1, m2, dist,
								(Double) muscleList.get(3),
								(Double) muscleList.get(4));
					}

					else {
						tempMuscle = new Muscle(m1, m2,
								(Double) muscleList.get(2),
								(Double) muscleList.get(3),
								(Double) muscleList.get(4));
					}
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

	public Mass findFixed(String massID, List<FixedMass> fixedMass) {
		for (FixedMass m : fixedMass) {
			if (m.getID().equals(massID)) {
				return m;
			}
		}
		return null;
	}

}
