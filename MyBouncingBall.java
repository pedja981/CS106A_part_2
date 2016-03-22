/* 
 * File: MyBouncingBall.java 
 * ----------------------- 
 * This program graphically simulates a bouncing ball. 
 */ 
// import sun.awt.X11.ColorData;
import acm.program.*; 
import acm.graphics.*; 
import acm.util.*;
import java.awt.*;
public class MyBouncingBall extends GraphicsProgram {
	/** Size (diameter) of the ball */ 
	private static final int DIAM_BALL = 30; 
	/** Amount Y velocity is increased each cycle as a
	* result of gravity */ 
	private static double GRAVITY = 3;
	/** Delay or pause time between the ball moves in miliseconds */
	private static int DELAY = 75;
	/** Initial X and Y location of the ball */
	private static final double X_START = DIAM_BALL / 2;
	private static final double Y_START = 100;
	/** X velocity */
	private static final double X_VEL = 2;
	/** Amount Y velocity is reduced when it bounces */
	private static final double BOUNCE_REDUCE = 0.97;
	/** Starting X and Y velocities */
	private double xVel = X_VEL;
	private double yVel = 0.0;
	/* private instance variable */
	private GOval ball;
   
	public void run () {
		/* method for creating the ball object */
		setup();
		// Simulation ends when the ball goes off 
		// right hand end of the screen
		while (ball.getX() < getWidth()) {
			moveBall();
			checkForCollision();
			pause(DELAY);
		}
	}
	
	/** Create and place the ball */
	private void setup() {
		ball = new GOval(X_START, Y_START, DIAM_BALL, DIAM_BALL);
		ball.setFilled(true);
		add(ball);
	}
		
	/**  Update and move the ball*/
	private void moveBall() {
		// Increase the yVelocity due to gravity on each cycle
		yVel += GRAVITY;
		ball.move(xVel, yVel);
	}	
	
	private void checkForCollision() { 
	/** Determine if collision with floor, update velocities
	 * and location as appropriate */
	// Color ballColor = rgen.nextColor();
		if (ball.getY() > getHeight() - DIAM_BALL) {
			yVel = -yVel * BOUNCE_REDUCE;
			/* assume bounce will move ball an amount above the
			* floor equal to the amount it would have dropped
			* below the floor
			*/
			double diff = ball.getY() - (getHeight() - DIAM_BALL);
			ball.move(0, -2 * diff);
			Color ballColor = rgen.nextColor();
			ball.setColor(ballColor);
			ball.setFillColor(ballColor);
			//ball.setFillColor(rgen.nextColor());
			//ball.setColor(rgen.nextColor());
		}
	}
	
	/* Private instance variables  */
	private RandomGenerator rgen =
			RandomGenerator.getInstance();
	
}

