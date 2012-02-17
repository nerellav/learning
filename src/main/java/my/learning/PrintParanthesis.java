package my.learning;

import java.util.ArrayList;
import java.util.List;

public class PrintParanthesis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<WordStr> input = new ArrayList<WordStr>();
		input.add(new WordStr("a"));
		input.add(new WordStr("bc"));
		input.add(new WordStr("d"));
		
		List<String> words = new ArrayList<String>();
		
		String str = "";
		for(int i = 0; i < 10; i++) {
			WordStr s = input.get(i);
			str.concat(s.strAt(i));
//			for() {
//				
//			}
		}
		
		System.out.println("a" + getChar("bce") + "d");

	}
	private static String getChar(String s) {
		if (s.length() == 1 ) {
			return s;
		}
		else {
			return getChar(s.substring(1));
		}
	}

}
