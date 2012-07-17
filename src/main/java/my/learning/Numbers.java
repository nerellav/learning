package my.learning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Numbers {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\vamsi\\Desktop\\C-small-practice.in"));

		int no_test_cases = Integer.valueOf(br.readLine());
		
		int counter = 1;
		
		final BigDecimal root_five = new BigDecimal(String.valueOf(Math.sqrt(5)));
		final BigDecimal three_plus_root_five = new BigDecimal(3).add(root_five);
		
		System.out.println(root_five);
//		System.out.println(three_plus_root_five);
		
		while (counter <= no_test_cases) {

			int power = Integer.valueOf(br.readLine());
			
			BigDecimal ans = three_plus_root_five.pow(power);

//			System.out.println("double: " + ans + " long: " + ans.longValue());

			System.out.printf("Case #%d: %03d\n", counter, ans.toBigInteger()
					.remainder(new BigInteger("1000")));
			counter++;
		}
		br.close();
	}
}
