import initialize.FileChooser;

/**
 * Creates window that can be moved, resized, and closed by the user.
 * 
 * @author Robert C. Duvall, In-Young Jo, Benson Tran.
 */

public class Main {

	public static void main(String args[]) {
		FileChooser fc = new FileChooser("environment");
		fc.setVisible(true);

	}

}