package simulation;

// SimpleFileChooser.java
// A simple file chooser to see what it takes to make one of these work.
//
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class SimpleFileChooser extends JFrame {

	public SimpleFileChooser() {
		
		

		super("File Chooser Test Frame");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButton = new JButton("Open Object");
		JButton openButton1 = new JButton("Open Environment");
		final JLabel statusbar = new JLabel("Select XML files");
		final JLabel statusbar1 = new JLabel("");

		// Create a file chooser that opens up as an Open dialog
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("assets/"));
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File sf = chooser.getSelectedFile();
					statusbar.setText("You chose " + sf.getName());
				}
			}
		});

		openButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("assets/"));
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File[] sf = chooser.getSelectedFiles();
					String filelist = "nothing";
					if (sf.length > 0)
						filelist = sf[0].getName();
					for (int i = 1; i < sf.length; i++) {
						filelist += ", " + sf[i].getName();
					}
					statusbar1.setText("You chose " + filelist);
				}
			}
		});

		c.add(openButton);
		c.add(openButton1);
		c.add(statusbar);
		c.add(statusbar1);
	}

	public static void main(String args[]) {
		SimpleFileChooser sfc = new SimpleFileChooser();
		sfc.setVisible(true);
	}
}