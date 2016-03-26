/*
 * File: Breakout.java
 * -------------------
 * This file will eventually implement the game of Breakout.
 */

/*
 * Concept:
 * 1.Game has 3 turns, so for loop will be used
 * 2. break the game into steps
 * 3. define the finish of the game
 * 
 */
		
 /* paddle - moves with the mouse across x-axis, fixed y-position
  * ball is traveling, as in bouncing ball, without gravity
  * ball is launched from the center of the screen, with random angle (here the
  * question is - to maintain constant speed or not? Vector addition? ) = NO, ONLY
  * random vx component
  * on collision with brick - ball bounces as of the wall, and brick disappears  
  */
		
 /*
  * Steps: setup the game
  */



import java.awt.Color;
import java.awt.event.*;

import acm.program.GraphicsProgram;
import acm.graphics.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	public void run() {
		
		
		setupGame();
		
		// playGame();
		addMouseListeners();
		
	}
	
	/** Method for setting up the game in it's static state - building the basic
	 * objects with starting conditions  */
	private void setupGame() {
		createBricks();
		createPaddle();		
	}
	
	/** One of the methods in setup() method. Creates the paddle as GRect object */
	private void createPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, (getWidth()-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT); 
	}
	
	/** One of the methods in setup() method. Creates the bricks as GRect objects,
	 * puts them in appropriate position, and gives them appropriate colors*/
	private void createBricks() {
		for (int i=0; i<NBRICK_ROWS; i++) {
			int total_bricks_width = NBRICKS_PER_ROW*BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;
			int beginning_x = (WIDTH - total_bricks_width)/2;
			for (int j=0; j<NBRICKS_PER_ROW; j++) {
				brick = new GRect(beginning_x+ j*BRICK_WIDTH + j*BRICK_SEP, 
						BRICK_Y_OFFSET + i*BRICK_HEIGHT + i*BRICK_SEP, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				switch (i) {
					case 0: brick.setColor(Color.red); break;
					case 1: brick.setColor(Color.red); break;
					case 2: brick.setColor(Color.orange); break;
					case 3: brick.setColor(Color.orange); break;
					case 4: brick.setColor(Color.orange); break;
					case 5: brick.setColor(Color.yellow); break;
					case 6: brick.setColor(Color.yellow); break;
					case 7: brick.setColor(Color.green); break;
					case 8: brick.setColor(Color.green); break;
					case 9: brick.setColor(Color.cyan); break;
					case 10: brick.setColor(Color.cyan); break;
				}
				add(brick);
			}
		} // end of loop for creating bricks
	}	// end of method for creating bricks
	
	
	// Called on mouse press to record the coordinates of the click
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
		//centerPaddle();
		//gobj.move(, 0);
		
		
		while ((center - last.getX())<=0) {
			gobj.move(e.getX()-last.getX(), 0);
			// we now save the new position of the last
			last = new GPoint(e.getPoint());
		}
	}
	
	/*public void centerPaddle() {
		while ((center - last.getX())<=0) {
			gobj.move(e.getX()-last.getX(), 0);
			// we now save the new position of the last
			last = new GPoint(e.getPoint());
		}
	}*/
	
	public double center() {
		center = gobj.getX()+PADDLE_WIDTH/2;
		return center;
	}
	
	public void mouseMoved(MouseEvent e) {
		if (gobj != null) {
		if (last.getX() > 0) {	
		
				gobj.move(e.getX()-last.getX(), 0);
				// we now save the new position of the last
				last = new GPoint(e.getPoint());			
			}
		}
	}
	
	
	
	/* GPoint last = new GPoint((getWidth()-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
	public void mouseDragged(MouseEvent e) {
		if(paddle != null) {
		last = new GPoint((getWidth()-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		paddle.move(e.getX()-last.getX(), 0);
		last = new GPoint(e.getX(), e.getY());
		//HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT
	}} */
	
	
	/* private instance variables */ 
	private double center;
	private GRect brick;
	private GRect paddle;
	private GObject gobj; /* The object being directed - paddle */
	private GPoint last; /* The last mouse position */
	//private GPoint last; /* The last mouse position */
}
