package simulation;

import jgame.JGColor;

public class Spring extends Muscle {

	/* Spring constructor with springyness */
	public Spring(Mass m1, Mass m2, Double restL, Double K) {
		super(m1,m2,restL,K,0);
		color = JGColor.blue;
		colorExpand = JGColor.pink;
		colorContract = JGColor.white;
	}

}
