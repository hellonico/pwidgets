package net.hellonico.pwidgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image;

	public ImagePanel() {
	}

	public ImagePanel(String path) {
		setImage(path);
	}

	public void setImage(String path) {
		setImage(new File(path));
	}

	public void setImage(File file) {
		try {
			image = ImageIO.read(file);
		} catch (IOException ex) {
			image = null;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, null);
	}
	
}
