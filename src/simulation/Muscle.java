package simulation;

public class Muscle extends Spring	{

	private double myAmplitude;
	
	protected Muscle(String m1, String m2, double restL, double K, 
			double amp) {
		super(m1, m2, restL, K);
		myAmplitude = amp;
	}
	
}
