package de.hdm.miit.uebung1.afg2;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ClickMe extends Applet implements MouseListener {
    private int x=-1, y=-1;
    private static int RADIUS = 7;

    public void paint(Graphics g) {
        this.drawBackground(g);
    }

    public void drawBackground(Graphics g) {
        //draw a black border and a white background
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }

    public void update(Graphics g) {
        this.drawBackground(g);
        //draw the spot
        g.setColor(Color.red);
        if (x != -1) {
            g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
        }
    }

    public void init() {
        this.addMouseListener(this);
    }

    public void mousePressed(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        this.repaint();
    }
    public void mouseClicked(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
}