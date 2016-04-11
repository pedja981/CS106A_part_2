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
		
		int largest = -1;
		int secondLargest = -1;
		while (true) {
			int input = readInt(" ?: ");
			if (input == SENTINEL) break;
			
			if (input>largest) {
				secondLargest = largest;
				largest=input;
			} else if (input>secondLargest) {
				secondLargest = input;
			}
		}
		
		println("The largest value is: " + largest);
		println("The second largest value is: " + secondLargest);
	}
}


