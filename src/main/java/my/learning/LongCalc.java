package my.learning;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pair {
	double base;
	long pow;

	public Pair(double d, long n) {
		base = d;
		pow = n;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		Pair p = (Pair) o;
		return (this.base == p.base && this.pow == p.pow);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		long result = 1;
		result = PRIME * result + Double.hashCode(base);
		result = PRIME * result + pow;
		return Long.hashCode(result);
	}
}

class LongCalc {

	static double myPow(Pair p) {

		double x = p.base;
		long n = p.pow;

		BigDecimal result = new BigDecimal(x);

		int i = 1;

		if (n == 0) {
			return 1;
		}

		if (x == 1.0) {
			return 1;
		}

		while (i < n) {

			result = result.multiply(new BigDecimal(x));

			i++;
		}

		return result.doubleValue();
	}

	static private Map<Pair, Double> cache = new ConcurrentHashMap<>();

	static double powDnQ(Pair p) {

		if (cache.containsKey(p)) {
			return cache.get(p);
		}

		double x = p.base;
		long n = p.pow;

		double left = powDnQ(new Pair(x, n / 2));
		double right = powDnQ(new Pair(x, n - n / 2));

		return left * right;
	}

	static void printmatrix(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}

			System.out.println();
		}

	}

	public static double myPow(double x, long n) {
		Pair p2 = new Pair(x, 2);
		Pair p3 = new Pair(x, 3);
		// warm up
		cache.put(p2, myPow(p2));
		cache.put(p3, myPow(p3));
		boolean inverse = false;
		if (n < 0) {
			inverse = true;
			n = Math.abs(n);
		}

		double result;

		if (n == 0) {
			result = 1;
		}

		else if (n == 1) {
			result = x;
		}

		else {
			result = powDnQ(new Pair(x, n));
		}

		if (inverse) {

			return 1 / result;
		}
		return result;
	}

	static void findSplPos() {
		int[][] a = new int[][] { { 1, 3, 4 }, { 5, 2, 9 }, { 8, 7, 6 }, { 10, 11, 12 } };

		printmatrix(a);

		// System.out.println(a.length + " " + a[0].length);

		int[][] rowspl = new int[a.length][2];
		int[][] colspl = new int[a[0].length][2];

		for (int n = 0; n < a[0].length; n++) {
			colspl[n][0] = Integer.MAX_VALUE;
			colspl[n][1] = Integer.MIN_VALUE;
		}

		for (int m = 0; m < a.length; m++) {
			rowspl[m][0] = Integer.MAX_VALUE;
			rowspl[m][1] = Integer.MIN_VALUE;

			for (int n = 0; n < a[0].length; n++) {

				if (a[m][n] < rowspl[m][0]) {
					rowspl[m][0] = a[m][n]; // row min
				}
				if (a[m][n] > rowspl[m][1]) {
					rowspl[m][1] = a[m][n]; // row max
				}

				if (a[m][n] < colspl[n][0]) {
					colspl[n][0] = a[m][n]; // col min
				}
				if (a[m][n] > colspl[n][1]) {
					colspl[n][1] = a[m][n]; // col max
				}

			}

		}
		System.out.println("row spl");
		printmatrix(rowspl);

		System.out.println("col spl");
		printmatrix(colspl);

		int splpos = 0;
		for (int m = 0; m < a.length; m++) {
			for (int n = 0; n < a[0].length; n++) {
				if (a[m][n] == rowspl[m][0] || a[m][n] == rowspl[m][1] || a[m][n] == colspl[n][0]
						|| a[m][n] == colspl[n][1]) {
					splpos++;
				}
			}
		}

		System.out.println("spl positions: " + splpos);

	}
	
	static class RevComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o1.compareTo(o2);
		}
		
	}

	public static void main(String[] args) {

		System.out.println(myPow(2, 10));
		System.out.println(myPow(1.01, 10));
//		System.out.println(myPow(0.5, 2147483648l));
//		System.out.println(myPow(0.5, 2147483648l / (32 * 32 * 32 * 32 * 32 * 32 * 32 * 32)));
//		System.out.println(myPow(2.00000, -2147483648l));

//		Pair p = new Pair(1.01, 456789);
//		
//		Pair p2 = new Pair(1.01, 2);
//		Pair p3 = new Pair(1.01, 3);
//		Pair p5 = new Pair(1.01, 5);
//		
//		
//		// warm up
//		cache.put(p2, myPow(p2));
//		cache.put(p3, myPow(p3));
//		cache.put(p5, myPow(p5));
//		
//		
//		p2 = new Pair(2, 2);
//		p3 = new Pair(2, 3);
//		p5 = new Pair(2, 5);
//		
//		
//		// warm up
//		cache.put(p2, myPow(p2));
//		cache.put(p3, myPow(p3));
//		cache.put(p5, myPow(p5));
//		
//		System.out.println(Double.hashCode(10.252));
//		System.out.println(powDnQ (new Pair(2,40)));
//		
//		System.out.println(powDnQ (p));
//		System.out.println(myPow(p)); // 3.4368447520907104
		
	
		int[] arr = new int[] {1,6,-1,-5,5,6,-2,3};
		
		Arrays.sort(arr );
		
		
//		System.out.println(Arrays.toString(arr));
		
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(-4);
		seq.add(-23);
		seq.add(-3);
		seq.add(4);
		seq.add(-2);
		seq.add(5);
		seq.add(-5);
		seq.add(-1);
		System.out.println("raw list");
				
		seq.forEach(s -> System.out.print(s + " "));
		
		System.out.println("two skipped & reverse");
		seq = seq.stream().skip(2).collect(Collectors.toList());
		
		seq.stream().sorted(Collections.reverseOrder()).forEach(s -> System.out.print(s + " "));
		System.out.println("raw list");
		
		seq.forEach(s -> System.out.print(s + " "));
		System.out.println();
		
//		seq.sort(new Comparator() {
//
//			@Override
//			public int compare(Object o1, Object o2) {
//				return ((Integer) o1).compareTo((Integer) o2);
//			}});
		
		seq.sort(new RevComparator());
		System.out.println("sorted");
		seq.stream().forEach(s -> System.out.print(s + " "));
	}
	
	/* Problem Name is &&& Dist. Between Strings &&& PLEASE DO NOT REMOVE THIS LINE. */

//
//	public class Solution {
//
//	  /*
//	   * == Instructions ==
//	   *
//	   * Debug why the included test cases aren't succeeding and account for them in the code
//	   *
//	   * A description of the expected behaviour is given below
//	   */
//
//	  /**
//	   * Given two words returns the shortest distance between their two midpoints in number of characters
//	   * Words can appear multiple times in any order and should be case insensitive.
//	   *
//	   * E.g. for the document="This is a sample document we just made up"
//	   
//	   * E.g. for the document="This is a sample document we just made sample test this this sample up"
//	   *   shortestDistance( document, "we", "just" ) == 4
//	   *   shortestDistance( document, "is", "a" ) == 2.5
//	   *   shortestDistance( document, "is", "not" ) == -1
//	   */
//	  public static double shortestDistance(String document, String word1, String word2) {
//	    String[] words = document.split(" ");
//
//	    int index = 0;
//	    double shortest = document.length();
//	    double word1Loc = 0;
//	    double word2Loc = 0;
//	    
//	    
//	    // test string
//	    for(String word : words) {
//	      
//	      if(word.toLowerCase().startsWith(word1.toLowerCase())) {
//	        word1Loc = index + (word1.length()/2.0);
//	      } else if(word.toLowerCase().startsWith(word2.toLowerCase())) {
//	        word2Loc = index + (word2.length()/2.0);
//	      }
//
//	      if(word1Loc > 0 && word2Loc > 0) {
//	        double current = Math.abs(word2Loc - word1Loc);
//	        if(current < shortest) {
//	          shortest = current;
//	        }
//	      }
//
//	      index += word.length() +1;
//	    }
//
//	    if(word1Loc == 0 || word2Loc == 0) {
//	      return -1;
//	    }
//
//	    return shortest;
//	  }
//
//	  /**
//	   * Returns true if the tests pass. Otherwise, false.
//	   */
//	  public static boolean doTestsPass() {
//	    // todo: implement more tests if you'd like
//	    // return  shortestDistance(_document, "this", "sample") == 11d ;
//	    
//	    
//	    return  shortestDistance(_document, "and", "graphic") == 6d &&
//	        shortestDistance(_document, "transfer", "it") == 14d &&
//	        shortestDistance(_document, "layout", "It" ) == 6d &&
//	        shortestDistance(_document, "Design", "filler" ) == 25d &&
//	        shortestDistance(_document, "It", "transfer") == 14d &&
//	        Math.abs(shortestDistance(_document, "of", "lorem") - 4.5) < 0.000001 &&
//	        shortestDistance(_document, "thiswordisnotthere", "lorem") == -1d;
//	  }
//
//	  /**
//	   * Execution entry point.
//	   */
//	  public static void main(String[] args) {
//	    
//	    System.out.println(shortestDistance(_document, "this", "sample"));
//	    
//	    System.out.println(shortestDistance(_document, "and", "graphic"));
//	    System.out.println(shortestDistance(_document,"transfer", "it"));
//	    System.out.println(shortestDistance(_document, "layout", "It" ));
//	    System.out.println(shortestDistance(_document, "Design", "filler"));
//	    
//	    System.out.println(shortestDistance(_document,"It", "transfer"));
//	    System.out.println(shortestDistance(_document, "of", "lorem"));
//	    System.out.println(shortestDistance(_document, "thiswordisnotthere", "lorem"));
//	    
//	    
//	    // Run the tests
//	    if (doTestsPass()) {
//	      System.out.println("All tests pass");
//	    } else {
//	      System.out.println("There are test failures");
//	    }
//	  }
//
//	  private static final String _document;
//	  static{
//	    StringBuffer sb = new StringBuffer();
//	    sb.append("In publishing and graphic design, lorem ipsum is a filler text commonly used to demonstrate the graphic elements");
//	    sb.append(" of a document or visual presentation. Replacing meaningful content that could be distracting with placeholder text");
//	    sb.append(" may allow viewers to focus on graphic aspects such as font, typography, and page layout. It also reduces the need");
//	    sb.append(" for the designer to come up with meaningful text, as they can instead use hastily generated lorem ipsum text. The");
//	    sb.append(" lorem ipsum text is typically a scrambled section of De finibus bonorum et malorum, a 1st-century BC Latin text by");
//	    sb.append(" Cicero, with words altered, added, and removed to make it nonsensical, improper Latin. A variation of the ordinary");
//	    sb.append(" lorem ipsum text has been used in typesetting since the 1960s or earlier, when it was popularized by advertisements");
//	    sb.append(" for Letraset transfer sheets. It was introduced to the Information Age in the mid-1980s by Aldus Corporation, which");
//	    sb.append(" employed it in graphics and word processing templates for its desktop publishing program, PageMaker, for the Apple");
//	    sb.append(" Macintosh. A common form of lorem ipsum reads: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do");
//	    sb.append(" eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation");
//	    sb.append(" ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit");
//	    sb.append(" esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui");
//	    sb.append(" officia deserunt mollit anim id est laborum.");
//
//	    //_document = sb.toString();
//	    
//	    // sb = new StringBuffer();
//	    // sb.append("This, is a sample document we just made up");
//	    _document = sb.toString();
//	  }
//	}


}
