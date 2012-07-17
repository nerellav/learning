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

public class MinScalarProd {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\vamsi\\Desktop\\A-large-practice.in"));

		int no_test_cases = Integer.valueOf(br.readLine());

		int counter = 1;

		while (counter <= no_test_cases) {

			int inputs = Integer.valueOf(br.readLine());

			String v1Line = br.readLine();
			String v2Line = br.readLine();

			List<Integer> v1 = new ArrayList<Integer>();
			List<Integer> v2 = new ArrayList<Integer>();

			for (String s : v1Line.split("\\s+", inputs)) {
				v1.add(Integer.valueOf(s));
			}

			for (String s : v2Line.split("\\s+")) {
				v2.add(Integer.valueOf(s));
			}
			BigInteger product = new BigInteger("0");

			Collections.sort(v1);
			Collections.sort(v2, Collections.reverseOrder());

			for (int i = 0; i < v1.size(); i++) {
				BigInteger val = new BigInteger(String.valueOf(v1.get(i)))
						.multiply(new BigInteger(String.valueOf(v2.get(i))));
				product = product.add(val);

			}

			System.out.println("Case #" + counter + ": " + product.toString());
			counter++;
		}
		br.close();
	}
}
