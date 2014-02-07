package simulation;

import initialize.ObjectParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FixedMass extends Mass {

	/* Constructor */
	public FixedMass(String id, double xCoord, double yCoord) {

		super(id, 1.0, 1.0, xCoord, yCoord, 0, 0);
		this.x = xCoord;
		this.y = yCoord;
	}

	public void move() {
	}

	public void createFixedMasses() {

		ObjectParser importFixed = new ObjectParser();

		Map<String, List<Double>> fixedMap = new HashMap<String, List<Double>>(
				importFixed.getFixedMap());

		Map<String, FixedMass> fixedMasses = new HashMap<String, FixedMass>();

		for (Entry<String, List<Double>> entry : fixedMap.entrySet()) {
			String key = entry.getKey();
			List<Double> value = entry.getValue();

			fixedMasses
					.put(key, new FixedMass(key, value.get(0), value.get(1)));

		}
	}

}
