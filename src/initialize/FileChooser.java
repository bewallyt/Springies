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

public class FileChooser extends JFrame {

	private String environmentString;
	private String assemblyString;
	private String type;
	
	public String getAssemblyString() {
		return assemblyString;
	}

	public FileChooser(final String type) {

		super("Springies: Import XML File");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		this.type = type;
		JButton openButton;

		if (type.equals("assembly")) {
			openButton = new JButton("Open Assembly");
		} else {
			openButton = new JButton("Open Environment");
		}

		JButton openButtonFinish = new JButton("Done");
		final JLabel statusbar = new JLabel("Select XML files");

		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(FileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File sf = chooser.getSelectedFile();
					if (type.equals("assembly")) {
						assemblyString = sf.getName();
					} else {
						environmentString = sf.getName();
					}

					statusbar.setText("You chose " + sf.getName());
				}

			}

		});

		openButtonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				close();
				if (type.equals("environment")) {
					Springies.createSpringies(environmentString);
				}
			}
		});

		c.add(openButton);
		c.add(openButtonFinish);
		c.add(statusbar);

	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}

}