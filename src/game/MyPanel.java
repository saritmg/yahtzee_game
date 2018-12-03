package game;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private int width, height;
	
	public MyPanel(int w, int h) {
		width = w;
		height = h;
		
	}
	
	public MyPanel(int w, int h, int a, int b) {
		width = w;
		height = h;
		this.setLayout(new GridLayout(a,b));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

}
