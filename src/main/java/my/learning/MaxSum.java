package my.learning;
import java.util.ArrayList;
import java.util.List;


public class MaxSum {
	static int[] findNextNumberLinear(int[] arr) {

		int len = arr.length - 1;
		int prev = arr[len];
		int i = len - 1;
		int nextMin = Integer.MAX_VALUE;
		int nextMinIndex = len;

		for (; i >= 0; i--) {

			if (arr[i] < prev) { // chance to find a larger number
				break;
			}
			prev = arr[i];
		}

		if (i >= 0) // found index to swap with last
		{
			// find the next big digit than arr[i] towards right of i

			for (int j = len; j > i; j--) {
				if (arr[j] > arr[i] && arr[j] < nextMin) {
					nextMin = arr[j];
					nextMinIndex = j;
				}
			}

			// swap

			int temp = arr[i];
			arr[i] = arr[nextMinIndex];
			arr[nextMinIndex] = temp;

			// reverse
			int last = len, first = i + 1;

			while (first < last) {

				temp = arr[first];
				arr[first] = arr[last];
				arr[last] = temp;

				first++;
				last--;
			}

		}

		return arr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(-4);
		seq.add(-23);
		seq.add(-3);
		seq.add(4);
		seq.add(-2);
		seq.add(5);
		seq.add(-5);
		seq.add(-1);
		
		int sum = 0;
		int maxSum = 0;
		for (int e : seq) {
			sum += e;
			
			if (sum > maxSum){
				maxSum = sum;
			} else if(sum < 0 ){
				sum = 0;
			}
		}
		
		System.out.println(maxSum);
		

	}

}
