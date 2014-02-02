package simulation;

public class Muscle extends Spring	{

	private double myAmplitude;
	
	protected Muscle(Mass m1, Mass m2, double restL, double K, 
			double amp) {
		super(m1, m2, restL, K);
		myAmplitude = amp;
	}
	
}
