
/** @file RemoveOccurencies.java */

import acm.program.*;

public class RemoveOccurencies extends ConsoleProgram{
	
	/* Method for removing all occurencies of one characters from string */
	private String removeAllOccurrences(String str, String char_for_removal) {
		String result = "";
		
		char ch = char_for_removal.charAt(0);
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}
	
	public void run() {
		setFont("Times New Roman-24");
		while(true) {
			String line = readLine("Enter line from which to remove " +
					"occurencies: ");
			String char_for_removal = readLine("Enter which character you want" +
					" to remove: ");
			if(line.equals("")) {
				break;
			}
			println("String with removed characters is: " 
			+ removeAllOccurrences(line, char_for_removal));
		}
		println("All occurencies removed. Bye - Bye! ");
	}
	
}
