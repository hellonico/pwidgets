package net.hellonico.pwidgets;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;

public class EmptyFrame extends Frame {
	public EmptyFrame() {
		super();
		//super.setVisible(false);
	}

//	@Override
//	public Component add(Component comp, int index) {
//		return null;
//	}

//	public void paint(Graphics g) {
//		//super.paint(g);
//	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(false);
	}
	
}
