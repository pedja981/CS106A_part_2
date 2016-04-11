/*
 * File: BreakoutTwo.java
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
  * play the game
  * check for collisions - with paddle or bricks or wall
  * check for condition to win the game
  */

import java.awt.Color;
import java.awt.event.*;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.graphics.*;

public class BreakoutTwo extends GraphicsProgram {

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
	private static final int BRICK_HEIGHT = 10;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 90;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	private static double PADDLE_DELAY = 0.1;
	
	private static double BALL_DELAY = 15.0;
	
	public void run() {
		addMouseListeners();
		addKeyListeners();
		setupGame();		
		playGame();
		//addMouseListeners();
		//addKeyListeners();
		
	}
	
	/** Method for setting up the game in it's static state - building the basic
	 * objects with starting conditions  */
	private void setupGame() {
		createBricks();
		createPaddle();	
		createBall();
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
	
	private void createBall() {
		ball = new GOval(BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		ball.setColor(Color.RED);
		add(ball, (WIDTH - BALL_RADIUS*2)/2, (HEIGHT-BALL_RADIUS*2)/2);
	}
		
	// Called on mouse press to record the coordinates of the click
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		if ((getElementAt(last) != null) && (getElementAt(last) == paddle)) {
			gobj = getElementAt(last);
			/*
			 * When mouse is clicked on paddle, paddle is activated. in order
			 * to move it in a good fashion, we are first centering the paddle - 
			 * move the paddle so that it's center is matched with x-axis value
			 * of the mouse position. This also enables us better control over
			 * "left-right border condition" which says that paddle must not go 
			 * off the screen. If we try to control this condition using paddle position
			 * as a parameter, we go into the problems. But if we use e.getX() as 
			 * that parameter, things are much simpler
			 */
			
				// Centering the paddle with the mouse position
			gobj.setLocation(last.getX()-PADDLE_WIDTH/2, 
					HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		// first IF is to limit the paddle not to go off
		// the boundaries of the world
		if ((e.getX()>PADDLE_WIDTH/2) && (e.getX()<(WIDTH-PADDLE_WIDTH/2))){
			if (gobj != null) {
				gobj.move(e.getX()-last.getX(), 0);
				pause(PADDLE_DELAY);
				// we now save the new position of the last
				last = new GPoint(e.getPoint());
			}
		}
	} 
	
	
	/*
	 * LET'S PLAY the Game!
	 */
	private void playGame() {
		initializeBall();
		while (true) {
			moveBall();
			if (gameOver()) {
				remove(ball);
				break;
			}
		}
	}
	
	/*
	 * Determine the initial movement of the ball. It is defined by
	 * the velocity parameters vx and vy. vy is constant, vx varies,
	 * that's what we use random generator for
	 */
	private void initializeBall() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy = 3.0;
	}
	
	public void keyTurned(KeyEvent e) {
		playGame();
		//if (ball != null) {
		/*while (ball.getX() < WIDTH) {
			ball.move(vx, vy);
			pause(BALL_DELAY);
		}*/
		//}
	}
	
	private void moveBall() {
		ball.move(vx, vy);
		pause(BALL_DELAY);
		checkForCollision(); 
	}
		
	private void checkForCollision() {
		collideWithWalls();
		collideWithBricks();
		collideWithPaddle();
	}
	
	 private void collideWithWalls() { 
	      // determine if ball has dropped below the floor 
		 if (ball.getY() > (HEIGHT - BALL_RADIUS*2)) { 
	         // change ball's Y velocity to now bounce upwards 
	         vy = -vy;
		 }
		 
		// check for right side collision
	      if (ball.getX() > WIDTH - BALL_RADIUS*2) {
	    	  vx = -vx;
	      }
	      
	      // check for left side collision
	      if (ball.getX() < 0) {
	    	  vx = -vx;
	      }
	      
	      // check for top collision
	      if (ball.getY() < 0) {
	    	  vy = -vy;
	      }
	 }

	 private void collideWithBricks() {
	 
	 /* 4 points at the ball - when ball touches any object at one
	  * of that points, object is registered as collObj1-4
	  * We need this to simulate collisions artificially, but realistically */
	 collObj1 = getElementAt(ball.getX(), ball.getY());
	 collObj2 = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY());
	 collObj3 = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS*2);
	 collObj4 = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY()+BALL_RADIUS*2);
	 
	 /* Also, the game was too easy by default demands in handout.
	  * Bricks were too easy to destroy due to easily achiavable
	  * "breakout" effect...
	  * I've made it more interesting with these conditions, in order
	  * to make the game more like real version */
	 	if ((collObj1 != paddle) && (collObj1 != null) && (vy<0)) {
			remove(collObj1);
			// value of counter increases with every brick hit
			bricksDestroyed++;
			// bounce of the ball
			vy = -vy;
		}
		
		if ((collObj2 != paddle) && (collObj2 != null) && (vy<0)) {
			remove(collObj2);
			bricksDestroyed++;
			vy = -vy;
		}
		
		if ((collObj3 != paddle) && (collObj3 != null)&& (vy>0)) {
			remove(collObj3);
			bricksDestroyed++;
			vy = -vy;
		}
		
		if ((collObj4 != paddle) && (collObj4 != null)&& (vy>0)) {
			remove(collObj4);
			bricksDestroyed++;
			vy = -vy;
		}
	 }
	 
	 private void collideWithPaddle() {
		 /*collObj = getElementAt(ball.getX(), ball.getY());
			if (collObj == paddle) {
				vy = -vy;
			}*/
		
		collObj3 = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS*2);
		collObj4 = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY()+BALL_RADIUS*2);
	
		/* Special addition :-)
		 * We add some fun into game - depending on which part of paddle 
		 * the player hit the ball, ball behaves in different manner.
		 * This increases dynamics of game and makes it more fun!  */
		if ((collObj3 == paddle) && (collObj3 != null) && (vy>0)) {
			vy = -vy;
			if (((paddle.getX()+PADDLE_WIDTH)-ball.getX())<20 && 
					((paddle.getX()+PADDLE_WIDTH)-ball.getX())>10) {
				vx = 1.20*vx;
			}
			if (((paddle.getX()+PADDLE_WIDTH)-ball.getX())<10 && vx<0) {
				vx = - 0.80*vx;
			}
		}
		
		if ((collObj4 == paddle) && (collObj4 != null) && (vy>0)) {
			vy = -vy;
			
			
			if (((ball.getX()+BALL_RADIUS)-paddle.getX())<20 && 
					((ball.getX()+BALL_RADIUS)-paddle.getX())>10) {
				vx = 1.20*vx;
			}
			if (((ball.getX()+BALL_RADIUS)-paddle.getX())<10 && vx>0) {
				vx = - 0.80*vx;
			}
		}
		
	}
	
	/* Method for decide when the player have won the game */
	private boolean gameOver() {
		// condition for win the game
		return (bricksDestroyed == startNumberOfBricks);
	} 
	 
	/* private instance variables */ 
	private GRect brick;
	private GRect paddle;
	private GOval ball;
	private double vx, vy; /* velocities of the ball in x- and y- direction */
	private GObject gobj; /* The object being directed - paddle */
	private GPoint last; /* The last mouse position */
	private GObject collObj;
	private GObject collObj1;
	private GObject collObj2;
	private GObject collObj3;
	private GObject collObj4;
	
	private int startNumberOfBricks = 
			NBRICKS_PER_ROW * NBRICK_ROWS; // number of brick - used for winning the game
	private int bricksDestroyed = 0; // counter for checking the condition for
									// winning the game - all brick destroyed
	
	/* random generator private instance variable - for
	 * generating the direction of the ball at the beginning */
	private RandomGenerator rgen = 
			RandomGenerator.getInstance();

}
