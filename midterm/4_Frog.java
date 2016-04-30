/** @file Frog.java  
 *
 * Program which puts one picture into the canvas, in the grid,
 * and enables moving it to the nearest quadrant, according
 * to the position of the user's mous click
 */

import acm.program.*;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;


public class Frog extends GraphicsProgram {
	
	/** constants given by task */
	public static final int SQUARE_SIZE = 75;
	public static final int NROWS = 4;
	public static final int NCOLUMNS = 7;
	
	public static final int APPLICATION_WIDTH = NCOLUMNS*SQUARE_SIZE;
	public static final int APPLICATION_HEIGHT = NROWS*SQUARE_SIZE;
	

	/* Boolean method for checking if center of the image is in
	 * the application frame */
	private boolean insideApplicationFrame(double x, double y) {
		return (x>=0 && x <= NCOLUMNS*SQUARE_SIZE && 
			y >=0 && y<= NROWS*SQUARE_SIZE);
	}
		
	/* Moves the image within the frame */
	
	private void moveImage(double dx, double dy) {
		if (insideApplicationFrame(x+dx, y+dy)) {
			x += dx;
			y += dy;
			image.move(dx, dy);
		}
	}
	
	
	/** main method */
	public void run() {
		image = new GImage("pedja.jpg");
		x = (NCOLUMNS/2+0.5)*SQUARE_SIZE;
		y = (NROWS-0.5)*SQUARE_SIZE;
		/* creating the image */
		add(image, x-image.getWidth()/2, y-image.getHeight()/2);
		
		/* listen to the clicks of the mouse */
		addMouseListeners();
	}
	
	/* Method which reacts on the mouse click 
	 * by moving the image in the appropriate part of
	 * the application frame*/
	public void mouseClicked(MouseEvent e) {
		double mouse_x_coord = e.getX();
		double mouse_y_coord = e.getY();
		
		/* We want to move image in the direction which 
		 * corresponds the smaller distance (x- or y-) 
		 * between the point where the mouse is clicked 
		 * and the point where is the center of the image */
		if (Math.abs(mouse_x_coord-x) > Math.abs(mouse_y_coord-y)) {
			if (mouse_x_coord > x) {
				moveImage(SQUARE_SIZE, 0);
			} else {
				moveImage(-SQUARE_SIZE, 0);
			}
		} else {
			if (mouse_y_coord > y) {
				moveImage(0, SQUARE_SIZE);
			} else {
				moveImage(0, -SQUARE_SIZE);
			}
		}
	}
	
	/* private instance variables */
	private GImage image; 
	double x;	/* x-coordinate of the center of the image */
	double y;  /* y-coordinate of the center of the image */

}



