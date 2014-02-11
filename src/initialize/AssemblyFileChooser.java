package initialize;

// SimpleFileChooser.java
// A simple file chooser to see what it takes to make one of these work.
//
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import simulation.FixedMass;
import simulation.Mass;

public class AssemblyFileChooser extends JFrame {

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

				//addAssemblies();
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

	public List<Mass> addAssemblies() {
		List<FixedMass> fixedmassesV2;
		List<Mass> massesV2;

		FixedParser fixed = new FixedParser();
		MassParser mass = new MassParser();
		MuscleParser muscle = new MuscleParser();
		SpringParser spring = new SpringParser();

		fixedmassesV2 = fixed.createFixedMasses(assemblyString);
		massesV2 = mass.createMasses(assemblyString);
		muscle.createMuscles(assemblyString, massesV2, fixedmassesV2);
		spring.createSprings(assemblyString, massesV2, fixedmassesV2);
		return massesV2;
		


	}

}