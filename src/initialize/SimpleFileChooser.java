package initialize;

// SimpleFileChooser.java
// A simple file chooser to see what it takes to make one of these work.
//
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import springies.Springies;

public class SimpleFileChooser extends JFrame {

	private String environmentString;


	public SimpleFileChooser() {

		super("Springies: Import XML Files");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButtonEnvironment = new JButton("Open Environment");
		JButton openButtonFinish = new JButton("Done");
		final JLabel statusbarEnvironment = new JLabel("Select XML files");

		openButtonEnvironment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File sf = chooser.getSelectedFile();
					environmentString = sf.getName();
					statusbarEnvironment.setText("You chose " + sf.getName());
				}

			}

		});

		openButtonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				
				close();		
				Springies.createSpringies(environmentString);
			}
		});

		c.add(openButtonEnvironment);
		c.add(openButtonFinish);
		c.add(statusbarEnvironment);

	}
	
	public void close(){
			this.setVisible(false);
			this.dispose();
	}
	
}