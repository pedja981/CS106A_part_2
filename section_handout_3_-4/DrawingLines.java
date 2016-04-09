/** @file LineDrawer.java*/
/*
 * Program for drawing the lines with dragging
 * 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class LineDrawer extends GraphicsProgram {
	public void run() {
		
		/* We must add mouse listeners for events */
		addMouseListeners();
	}
	
	/* Create a new line when mouse is pressed */
	public void mousePressed(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		line = new GLine(x, y, x, y);
		add(line);
	}
	
	/* Method for changing the position of the second point
	 * of line when we drag the mouse */
	public void mouseDragged(MouseEvent e) {
		double x_dragged = e.getX();
		double y_dragged = e.getY();
		
		/* We use the setEndPoint() method */
		line.setEndPoint(x_dragged, y_dragged);
	}

	/* Private instance variables */
	private GLine line;
}
