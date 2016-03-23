/**
 * Section handout #3, Lecture 10
 * Program for random drawing of circles across the canvas
 * @author asus
 *
 */
import acm.program.*; 
import acm.graphics.*; 
import acm.util.*;

import java.awt.*;

public class MyDrawingRandomCircles extends GraphicsProgram {
	// first - the class variables
	/* Number of circles to draw */
	private static final int NUMBER_OF_CIRCLES = 10;
	/* Minimal radius for the circle, in pixels */
	private static final double MIN_RADIUS = 5.0;
	/* Maximal radius for the circle, in pixels */
	private static final double MAX_RADIUS = 50.0;
	/* We need to have pause time in order to achieve
	 * the impression of animation for the human eye */
	private static final int PAUSE_TIME = 100;
	
	
	public void run() {
		for (int i=0; i<NUMBER_OF_CIRCLES; i++) {
			drawRandomCircle();
		}
	}
	
		
	/* I have used top-down design - add the methods needed,
	 * and write them after that */
		
	/* Method for drawing the circle with random: 
	 * fill color, radius, position.
	 * Condition: Circle must fit into canvas */
	private void drawRandomCircle() {
		setupCircle();
		pause(PAUSE_TIME);
	}
	
	/** Method for setting up a circle - defining it, but without adding */
	private void setupCircle() {
		/* we are generating the diameter */
		double diam = rgen.nextDouble(MIN_RADIUS*2, MAX_RADIUS*2);
		/* Defining the position of the circle in a way that it fits into canvas 
		 * and generating the random position x, y */
		double x_position = rgen.nextDouble(diam, getWidth()-diam);
		double y_position = rgen.nextDouble(diam, getHeight()-diam);		
		/* Generating the circle with parameters above */ 
		GOval random_circle = new GOval(x_position, y_position, diam, diam);
		/* Generating custom color. We must declare the same color
		 * for outside borderline of the circle and filling of the circle */
		
		random_circle.setFilled(true);
		Color random_color = rgen.nextColor();
		random_circle.setColor(random_color);
		random_circle.setFillColor(random_color);
		
		/* Finally, adding the circle to the canvas */
		add(random_circle);
	}
	
	/* Private instance variables  for random generating*/
	private RandomGenerator rgen =
			RandomGenerator.getInstance();
}
