/** 
 * @file SecondLargest.java
 * program for finding the largest and second largest number
 * entered by the user */

import acm.program.*;

public class SecondLargest extends ConsoleProgram {
	// Sentinel value - used to stop of entering the values
	private static final int SENTINEL = 0;
	
	public void run() {
		setFont("Courier-16");
		println("This is the program for " +
				"finding two biggest integers from your list. ");
		println("Enter your values, one number per line. When you want");
		println("to finish, use" + SENTINEL + "to break.");
		
		int largestNum = 0;
		int secondLargestNum = 0;
		while (true) {
			int value = readInt(" ?: ");
			if (value == SENTINEL) break;
			
			/* if and else if - to cover our two wanted cases
			 * if user enters new largestNum, the previous become
			 * second largest. If users enters second, we declare it
			 * to our variable
			 */
			if (value>largestNum) {
				secondLargestNum = largestNum;
				largestNum=value;
			} else if (value>secondLargestNum) {
				secondLargestNum = value;
			}
		}
		
		println("The largest value is: " + largestNum);
		println("The second largest value is: " + secondLargestNum);
	}
}



