package my.learning;

/*

Write the substitution cipher algorithm that shifts all letters in an input string by
a number of positions in the English alphabet.


Example:
    assert cipher("abcdxyz", 5) == "fghicde" 


a -> 3: (!b,!c,=>) d
*/

// char[] alphabet = new char[] {a,b...z};

import java.lang.String;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


public class SolutionCipher {

	private double base;

	Integer longCalculation(Integer x) {
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException ignored) {
		}
		return x * 2;
	}

	public static double myPow(double x, int n) {

		BigDecimal xd = new BigDecimal(x);
		BigDecimal pow = xd;
		int i = 1;

		if (n == 0) {
			return 1;
		}

		boolean inverse = false;

		if (n < 0) {
			inverse = true;
			n = Math.abs(n);
		}

		while (i < n) {

			pow = pow.multiply(xd);

			i++;
		}

		if (inverse) {

			return 1 / pow.doubleValue();
		}
		return pow.doubleValue();
	}
	
	public double myPow(int n) {

		BigDecimal xd = new BigDecimal(base);
		BigDecimal pow = xd;
		int i = 1;

		if (n == 0) {
			return 1;
		}

		boolean inverse = false;

		if (n < 0) {
			inverse = true;
			n = Math.abs(n);
		}

		while (i < n) {

			pow = pow.multiply(xd);

			i++;
		}

		if (inverse) {

			return 1 / pow.doubleValue();
		}
		return pow.doubleValue();
	}
	

	static int correctShift(int shift) {
		shift = shift % 26;

		int correctedShift = shift;
		int i = 1;
		while (correctedShift < 0) {
			correctedShift = 26 * i + shift;
			i++;
		}

		return correctedShift;
	}

	static String cipher(String s, int shift) {
		char[] sa = s.toCharArray();

		shift = correctShift(shift);

		char[] cipher = new char[sa.length];

		for (int i = 0; i < sa.length; i++) {
			// 97 122 -- 123 => 97
			int temp = (int) sa[i] + shift;

			if (temp > (int) 'z') {
				temp = temp - 26;
			}
			cipher[i] = (char) (temp);
		}

		// join char array to make a string
		return new String(cipher);
	}

	public static void main(String[] args) {
		System.out.println(cipher("abcdxyz", 40));

		// System.out.println(-1 % 26);
		System.out.println(cipher("abcdxyz", -53));

		System.out.println(cipher("abcdxyz", -53));
		
		Scanner s = new Scanner(System.in);
        String bob = s.nextLine();                 // Reading input from STDIN
        String alice = s.nextLine();
		
		String al = "01010";
		String bo = "11000";
		
		
		
		String[] a = al.split("");
		String[] b = bo.split("");
		
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals( b[i])) {
				count++;
			}
		}
		
		System.out.println("likes: " + count);

//		System.out.println(myPow(2, -2));
//		System.out.println(myPow(1.00001, 123)); // 3.4368447520907104
//		System.out.println(myPow(1.01, 365)); // 3.4368447520907104
//		System.out.println(myPow(0.99, 365)); // 3.4368447520907104
	}
}

//char ch = 'a';
//int ascii_ch = (int) ch;

// import java.io.*;
// import java.util.*;
// import java.text.*;
// import java.math.*;
// import java.util.regex.*;

// public class Solution {

//     static int addNumbers(int a, int b) {
//       	return a+b; 
//    }

//  public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int a;
//         a = in.nextInt();
//         int b;
//         b = in.nextInt();
//         int sum;

//         sum = addNumbers(a, b);
//         System.out.println(sum);
//    }
// }
