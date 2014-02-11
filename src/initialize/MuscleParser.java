package initialize;

import java.util.ArrayList;
import java.util.Collections;
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

	public List<Muscle> returnMuscles(String xml)	{
		readFile(xml);
		List<Muscle> output = new ArrayList<Muscle>();
		for(List<String> data:objects)	{
			setSpringValues(data);
			double amp = Double.parseDouble((String) data.get(4));
			Muscle tempMuscle = new Muscle(m1, m2, restL, K, amp);
			output.add(tempMuscle);
		}
		return Collections.unmodifiableList(output);
	}
	
	@Override
	void getDefaultValue(String att, ArrayList<String> data) {
		if(att.equals("restlength") || att.equals("constant"))
			super.getDefaultValue(att, data);
		else if(att.equals("amplitude"))
			data.add("1.0");
	}
}
