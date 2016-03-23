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
	private static final int PAUSE_TIME = 50;
	
	
	
	
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
		/* I feel uncomfortable with dealing with the
		 * first condition - we must try creating the circles,
		 * for each 10, and keep trying until the condition
		 * is satisfied. So, I would use the "loop and a half"*/
	//	while (true) {
			setupCircle();
		//	if (circleInCanvas()) {
				//add(random_circle);
				pause(PAUSE_TIME);
			//	break;
			//}
	//	}
	}
	
	/** Method for setting up a circle - defining it, but without adding */
	private void setupCircle() {
		/* 
		 * ball = new GOval(X_START, Y_START, DIAM_BALL, DIAM_BALL);
		ball.setFilled(true);
		add(ball);
		 * */
		
		double x_position = rgen.nextInt(0, getWidth());
		double y_position = rgen.nextInt(0, getHeight());		
		double diam = rgen.nextDouble(MIN_RADIUS*2, MAX_RADIUS*2); 
		GOval random_circle = new GOval(x_position, y_position, diam, diam);
		add(random_circle);
	
	
	}
	
	
	
	/* Private instance variables  for random generating*/
	private RandomGenerator rgen =
			RandomGenerator.getInstance();
}
