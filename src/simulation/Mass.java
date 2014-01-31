package simulation;

import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Mass extends PhysicalObjectRect {
	
	
	public Mass(String id, int collisionId, JGColor color, double width,
			double height, double mass) {
		super(id, collisionId, color, width, height, mass);
		// TODO Auto-generated constructor stub
		
		
	}

}
