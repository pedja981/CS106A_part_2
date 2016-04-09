/** @file ThousendsCommas.java
 * Program for adding the commas on every thousend*/ 

import acm.program.ConsoleProgram;

public class ThousendsCommas extends ConsoleProgram{
	
	public void run() {
		setFont("Times New Roman-24");
		while(true) {
			String line = readLine("Enter the number: ");
			if(line.equals("")) {
				break;
			}
			println("Number with commas on thousends is: " 
			+ addCommasToNumericString(line));
		}
		println("All numbers processed. Bye - Bye! ");
	}

	// Method for adding commas, FOR loop in negative direction
	// for (i-value; i>=0l i--){...}
	private String addCommasToNumericString(String digits) {
		String result = "";
		int len = digits.length();
		int nDigits = 0;
		for (int i = len - 1; i >= 0; i--) {
			result = digits.charAt(i) + result;
			nDigits++;
			if (((nDigits % 3) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return result;
	}
	
}
