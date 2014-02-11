package initialize;

// SimpleFileChooser.java
// A simple file chooser to see what it takes to make one of these work.
//


import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AssemblyFileChooser extends JFrame{

	private String assemblyString;
	

	public String getAssemblyString() {
		return assemblyString;
	}

	public AssemblyFileChooser() {

		super("Springies: Add Object");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButtonObject = new JButton("Open Object");
		JButton openButtonFinish = new JButton("Done");
		final JLabel statusbarObject = new JLabel("Select XML files");

		// Create a file chooser that opens up as an Open dialog
		openButtonObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(AssemblyFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File sf = chooser.getSelectedFile();
					assemblyString = sf.getName();
					statusbarObject.setText("You chose " + sf.getName());
				}
			}
		});

		openButtonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				close();

			}
		});

		c.add(openButtonObject);
		c.add(openButtonFinish);
		c.add(statusbarObject);

	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}


}