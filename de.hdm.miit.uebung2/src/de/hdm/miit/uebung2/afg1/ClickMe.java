package de.hdm.miit.uebung2.afg1;

import java.awt.*;
import java.awt.event.*;

/**
 * ClickMe application class as a JFrame
 * @author Sebastian Fink
 *
 */
@SuppressWarnings("serial")
public class ClickMe extends javax.swing.JFrame {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]) {
		new ClickMe();
	}
	
	/**
	 * Constructor of the ClickMe object
	 */
	public ClickMe() {
		super("ClickMe");
		setContentPane(new DrawPane());
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		
		setVisible(true);
	}
}

/**
 * DrawPane class as a JPanel
 * @author Sebastian Fink
 *
 */
@SuppressWarnings("serial")
class DrawPane extends javax.swing.JPanel implements MouseListener {
	/**
	 * Constructor of the DrawPane object
	 */
	public DrawPane() {
		addMouseListener(this);
	}
	
	/**
	 * X- and y-coordinates for the position. Initialization value -1 to prevent object to be drawn on init.
	 */
	private int x = -1, y = -1;
	/**
	 * Radius of the object to be drawn.
	 */
	private static int RADIUS = 7;
	
	/**
	 * Background drawing method. Defines the size, color and framing.
	 * @param g
	 */
	public void drawBackground(Graphics g) {
		// draw a 2 line blue border on a white background
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
		g.setColor(Color.blue);
		g.drawRect(1, 1, this.getSize().width - 3, this.getSize().height - 3);
		g.drawRect(2, 2, this.getSize().width - 5, this.getSize().height - 5);
	}
	
	/**
	 * Paint method for drawing the background and a object.
	 * @param g
	 */
	public void paint(Graphics g) {
		// draw the spot
		if (x != -1) {
			drawBackground(g);
			g.setColor(Color.red);
			g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
		} else {
			drawBackground(g);
		}
	}
	
	/**
	 * Implemented method from the MouseListener interface. This method is called upon the mouse being pressed.
	 * @param event
	 */
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}