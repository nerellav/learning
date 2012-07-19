package my.learning;
import java.util.ArrayList;
import java.util.List;


public class MaxSum {

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
