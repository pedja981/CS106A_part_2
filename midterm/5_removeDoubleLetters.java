/** @file RemoveDoubleLetters.java */

import acm.program.*;

public class RemoveDoubleLetters extends ConsoleProgram{
	
	/* Method for removing all occurencies of double letters from string */
	private String removeDoubleLetters(String str) {
		String result = "";
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (i==0 || ch!= str.charAt(i-1)) {
				result += ch;
			}
		}
		return result;
	}
	
	public void run() {
		setFont("Times New Roman-24");
		while(true) {
			String line = readLine("Enter line from which to remove " +
					"double letters: ");
			if(line.equals("")) {
				break;
			}
			println("String with removed double letters is: " 
			+ removeDoubleLetters(line));
		}
		println("All double letters removed. Bye - Bye! ");
	}
	
}




