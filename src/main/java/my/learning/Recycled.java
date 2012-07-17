package my.learning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Recycled {

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

		while (counter <= no_test_cases) {

			String line = br.readLine();

			int howmany = 0;
			int A, B;

			A = Integer.valueOf(line.split("\\s+")[0]);
			B = Integer.valueOf(line.split("\\s+")[1]);

			int num = A;
			int l = 0;
			for (int i = 1; i <= A; l++, i *= 10)
				;

			final int len = l;
			while (num < B) {
				int rot_num = rotate_num(num, len);
				Map<String, String> map = new HashMap<String, String>();

				// bruteforce all rotations and see they are in range
				for (l = 0; l < len; l++) {
					if (num < rot_num && rot_num <= B) {

						// there is no need to put all elements in map;
						// just those whose multiple rotations might duplicate

						if (!map.containsKey(num + "|" + rot_num)) {
							map.put(num + "|" + rot_num, "");
							howmany++; // increment only if it is not a dup

						}
					}
					rot_num = rotate_num(rot_num, len);
				}
				num++;

			}
			System.out.println("Case #" + counter + ": " + howmany);
			counter++;
		}
		br.close();
	}

	static int rotate_num(int num, int length) {
		return (num / 10 + num % 10 * (int) Math.pow(10, length - 1));
	}
}
