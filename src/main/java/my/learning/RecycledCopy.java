package my.learning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecycledCopy {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\vamsi\\Desktop\\C-large-practice.in"));

		int no_test_cases = Integer.valueOf(br.readLine());

		int counter = 1;

		String line = "";
		int tens[] = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000 };

		while (counter <= no_test_cases) {

			line = br.readLine();

			int howmany = 0;
			int A, B;

			A = Integer.valueOf(line.split("\\s+")[0]);
			B = Integer.valueOf(line.split("\\s+")[1]);

			int d;
			for (d = 0; tens[d] <= A; d++);

			for (int x = A; x < B; x++) {
				List<Integer> vals = new ArrayList<Integer>(10);

				vals.add(0);
				for (int i = 1; i < d; i++) {
					vals.add(x / tens[i] + x % tens[i] * tens[d - i]);
				}
				
				Collections.sort(vals);
				
				for (int i = 1; i < d; i++) {
					if (x < vals.get(i) && vals.get(i - 1) < vals.get(i)
							&& vals.get(i) <= B)
						howmany++;
				}
			}
			System.out.printf("Case #%d: %d\n", counter, howmany);
			counter++;
		}
	}
}
