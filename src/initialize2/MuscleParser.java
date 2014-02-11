package initialize2;

import java.util.ArrayList;
import java.util.List;

import simulation.FixedMass;
import simulation.Mass;
import simulation.Muscle;

public class MuscleParser extends SpringParser{

	public MuscleParser(List<Mass> m, List<FixedMass> fm)	{
		super(m,fm);
		this.myObjectType = "muscle";
		this.myAttributes.add("amplitude");
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
		setSpringValues(data);
		double amp = Double.parseDouble((String) data.get(4));
		tempMuscle = new Muscle(m1, m2, restL, K, amp);
		objects.add(tempMuscle);
	}
}
