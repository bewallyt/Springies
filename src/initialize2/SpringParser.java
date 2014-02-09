package initialize2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Spring;

public class SpringParser extends AbstractParser{

	protected List<Mass> masses;
	protected List<FixedMass> fixedMasses;

	public SpringParser(List<Mass> m, List<FixedMass> fm){
		myObjectType = "spring";
		String[] attributeArray = {"a","b","restlength","constant"};
		myAttributes = Arrays.asList(attributeArray);
		masses = m;
		fixedMasses = fm;
	}

	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {
		if(att.equals("restlength"))	{
			data.add(-1.0);
		}
		else if(att.equals("constant"))	{
			data.add(1.0);
		}
	}

	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {
		Spring tempSpring;
		String id1 = (String) data.get(0);
		String id2 = (String) data.get(1);
		Mass m1 = findMass(id1, masses);
		if(m1 == null){
			m1 = findFixed(id1, fixedMasses);
		}
		Mass m2 = findMass(id2, masses);
		if(m2 == null){
			m2 = findFixed(id2, fixedMasses);
		}
		double dx = m2.getMassX() - m1.getMassX();
		double dy = m2.getMassY() - m1.getMassY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double restL = Double.parseDouble((String) data.get(2));
		double K = Double.parseDouble((String) data.get(3));
		if (restL == -1.0) {
			tempSpring = new Spring(m1, m2, dist, K);
		}

		else {
			tempSpring = new Spring(m1, m2, restL, K);
		}

		objects.add(tempSpring);
	}

	public Mass findMass(String massID, List<Mass> mass) {
		for (Mass m : mass)
			if (m.getID().equals(massID))
				return m;
		return null;
	}

	public Mass findFixed(String massID, List<FixedMass> fixedMass) {
		for (FixedMass m : fixedMass)
			if (m.getID().equals(massID))
				return m;
		return null;
	}
}
