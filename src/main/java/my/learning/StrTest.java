package my.learning;

import java.math.BigInteger;

public class StrTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "vamsi";
		String lname = "Nerella";
		String fname = name + lname;
		System.out.println(fname);
		name = "appu";
		System.out.println(fname);		
		
		String s1 = new String("test").intern();
		String s2 = new String("Test").toLowerCase().intern();
		
		if ( s1 == s2)
			System.out.println("true bhai");
		
		BigInteger b = new BigInteger("1");
		
		for (int i = 0; i<100; i++) {
			b = b.add(new BigInteger(String.valueOf(i)));
		}
		
		System.out.println(b.intValue());
	}

}
