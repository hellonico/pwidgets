package net.hellonico.pwidgets;

import javax.swing.JFrame;

/**
 * A simple example showing how to use {@link FileDrop}
 * 
 * @author Robert Harder, rob@iharder.net
 */
public class FileDropFrame extends JFrame {

	public FileDropFrame() {
		super("FileDrop");
		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder(
				"Drop 'em");
		final ImagePanel image = new ImagePanel();
		
		getContentPane().add(new javax.swing.JScrollPane(image),
				java.awt.BorderLayout.CENTER);

		new FileDrop(System.out, image,
				new FileDrop.Listener() {
					public void filesDropped(java.io.File[] files) {
						image.setImage(files[0]);
					} 
				}); 
		setBounds(100, 100, 300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/** Runs a sample program that shows dropped files */
	public static void main(String[] args) {
		new FileDropFrame();
	}

}
