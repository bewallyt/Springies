package initialize2;

import java.util.ArrayList;
import java.util.List;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Muscle;

public class MuscleParser extends SpringParser{

	
	public MuscleParser(List<Mass> m, List<FixedMass> fm)	{
		super(m,fm);
		myObjectType = "muscle";
		myAttributes.add("amplitude");
	}

	@Override
	void getDefaultValue(String att, ArrayList<Object> data) {
		if(att.equals("restlength") || att.equals("constant"))
			super.getDefaultValue(att, data);
		else if(att.equals("amplitude"))
			data.add(1.0);
	}
	
	@Override
	void addObject(ArrayList<Object> data, List<Object> objects) {
		Muscle tempMuscle;

		Mass m1 = findMass((String) data.get(0), masses);
		if(m1 == null){
			m1 = findFixed((String) data.get(0), fixedMasses);
		}
		Mass m2 = findMass((String) data.get(1), masses);
		if(m2 == null){
			m2 = findFixed((String) data.get(1), fixedMasses);
		}
		double dx = m2.getMassX() - m1.getMassX();
		double dy = m2.getMassY() - m1.getMassY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double restL = Double.parseDouble((String) data.get(2));
		double K = Double.parseDouble((String) data.get(3));
		double amp = Double.parseDouble((String) data.get(4));
		
		if (restL == -1.0) {
			tempMuscle = new Muscle(m1, m2, dist, K, amp);
		}

		else {
			tempMuscle = new Muscle(m1, m2, restL, K, amp);
		}

		objects.add(tempMuscle);
	}
}
