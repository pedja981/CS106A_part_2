/** 
 * @file createBorder.java
 *   */

import stanford.karel.*;

public class createBorder extends SuperKarel {
	public void run() {
		moveUp();
		for (int i=0; i<4; i++) {
			createBorder();
			prepareForNextRow();
		}

	
	private void moveUp() {
		turnLeft();
		move();
		turnRight();
	}
	
	private void createBorder() {
		move();
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	
	private void prepareForNextRow() {
		turnRight();
		move();
		turnRight();
		move();
		turnRight();
	}
	
	
}


