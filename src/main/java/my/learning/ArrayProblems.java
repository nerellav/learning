package my.learning;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.MinMaxPriorityQueue;

class MovingWindow {
	Queue<Integer> windowQ = new LinkedList<Integer>();
	
	int[] window = new int[] {0,0,0,0,0};
	double sum=0;
	int count = 1;
	
	void add(int val) {
		//add new val to sum
		sum = sum + val;
		
		//subtract old value
		sum = sum - window[count];
		
		//override old value
		window[count]=val;
		
		count = (count+1) % window.length;
	}
	
	double runningAvg() {
		return sum/window.length;
	}
}

public class ArrayProblems {

	static void printKthLargest(int[] arr, int k) {
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k);

		for (int i = 0; i < k; i++) {
			minheap.add(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			if (minheap.peek() < arr[i])
				minheap.add(arr[i]);

			// limit the size of the heap to "k"
			if (minheap.size() > k)
				minheap.poll();
		}

		System.out.println(minheap.peek());
	}

	static void printKthSmallest(int[] arr, int k) {
		PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k, Collections.reverseOrder());

//		MinMaxPriorityQueue<Integer> maxheap = MinMaxPriorityQueue.orderedBy(Collections.reverseOrder())
//			     .maximumSize(k)
//			     .create();

		for (int i = 0; i < arr.length; i++) {
			maxheap.add(arr[i]);

			// limit the size of the heap to "k"
			if (maxheap.size() > k)
				maxheap.poll();
		}

		System.out.println(maxheap.peek());
	}

	static boolean increasingTriplet(int[] a) {

		int l = a.length - 1;

		int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {

			if (a[i] <= first) {
				// first will be smallest from left before encountering a greater number
				first = a[i];
			} else if (a[i] <= second) {
				// second will be greater than first
				second = a[i];
			} else {
				// found third one greater than first and second
				return true;
			}
		}

		return false;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

//		int cl = nums1.length + nums2.length;
//		int[] c = new int[cl];
//		// using the pre-defined function arraycopy
//		System.arraycopy(nums1, 0, c, 0, nums1.length);
//		System.arraycopy(nums2, 0, c, nums1.length, nums2.length);
//		Arrays.sort(c);
//		double m = (cl % 2 == 1 ? c[cl / 2] : (c[cl / 2 - 1] + c[cl / 2]) / 2.0);
//		return m;

			int len = nums1.length + nums2.length;
			
			int mi = len /2;
			int m=0, n=0;
			
			for (int i = 0; i < mi; i++) {
				if (nums1[m] < nums2[n]) {
					m++;
				} else {
					n++;
				}
				
			}
			
			if (len % 2 !=0) {
				
			}
		
			return 0;
			
	}

	static List<List<Integer>> threeSumSlow(int[] a) {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();

		Set<List<Integer>> op = new HashSet<List<Integer>>();

		for (int i = 0; i < a.length; i++) {
			m.put(a[i], i);
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (i < j) {
					int sum = a[i] + a[j];

					if (m.containsKey(-1 * sum)) {
						int zsi = m.get(-1 * sum);
						if (zsi > j) {

							List<Integer> tuple = new ArrayList<Integer>();
							tuple.add(a[i]);
							tuple.add(a[j]);
							tuple.add(-1 * sum);
							Collections.sort(tuple);
							// int[] tuple = new int[] {a[i], a[j], -1 * sum};
							op.add(tuple);
						}
					}
				}
			}
		}

		return new ArrayList<>(op);
	}

	static List<List<Integer>> threeSum(int[] a) {
		Set<List<Integer>> op = new HashSet<List<Integer>>();

		Arrays.sort(a);

		for (int i = 0; i < a.length - 1; i++) {
			int l = i + 1;
			int r = a.length - 1;

			while (l < r) {
				if (a[i] + a[l] + a[r] == 0) {

				}
			}

		}

		return new ArrayList<>(op);
	}

	static void pairExists(int arr[], int sum) {
		Arrays.sort(arr);

		int tail = arr.length - 1;
		int head = 0;

		for (; head < tail;) {
			int currSum = arr[head] + arr[tail];
			if (currSum == sum) {
				System.out.printf("found pair %s, %s\n", arr[head], arr[tail]);
				head++;
			} else if (currSum < sum) {
				head++;
			} else if (currSum > sum) {
				tail--;
			}
		}

		System.out.println("no such pair found");

	}

	static int search(int[] nums, int target, int left, int right) {

		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;

		if (target == nums[mid]) {
			return nums[mid];
		} else if (target <= nums[mid]) {
			left = mid + 1;
		} else {
			right = mid - 1;
		}

		return search(nums, target, left, right);
	}

	static int findRotationPoint(int arr[], int start, int end) {

		// 8, 9 10, 11, 12, 1, 2, 3, 4
		// 11, 12, 1, 2

		// no rotation
		if (start + 1 > end) {
			return -1;
		}

//		//found rotation - looks unnecessary condition
//		if (start == end) {
//			return start;
//		}
//		

		int mid = (start + end) / 2;

		// default cases -- > < conditions make sure it works even when duplicate values
		if (mid < end && arr[mid] > arr[mid + 1]) {
			return mid + 1;
		}
		if (mid > start && arr[mid - 1] > arr[mid]) {
			return mid;
		}

		if (arr[start] > arr[mid]) {
			return findRotationPoint(arr, start, mid - 1);
		} else {
			return findRotationPoint(arr, mid + 1, end);
		}

	}

	static void pairExistsRotated(int arr[], int sum) {

		int tail = 0;
		int head = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				head = i + 1;
				tail = arr.length + i;
			}
		}

		for (; head < tail;) {
			int tailmod = tail % arr.length;
			int headmod = head % arr.length;
			int currSum = arr[headmod] + arr[tailmod];
			if (currSum == sum) {
				System.out.printf("found pair %s, %s\n", arr[headmod], arr[tailmod]);
				head++;
			} else if (currSum < sum) {
				head++;
			} else if (currSum > sum) {
				tail--;
			}
		}

		System.out.println("no such pair found");

	}

	static int searchRotated(int[] nums, int target, int left, int right) {

		// not found
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;

		// found
		if (target == nums[mid]) {
			return nums[mid];
		}

		// 8, 9, 10, 1, 2, 3, 4, 5, 6, 7

		if (nums[left] <= nums[mid]) { // left to mid is sorted
			if (target >= nums[left] && target <= nums[mid]) { // rotation is in left half
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		} else { // mid to right is sorted
			if (target >= nums[mid] && target <= nums[right]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return searchRotated(nums, target, left, right);

	}

	public static int searchRotated(int[] nums, int target) {
		return searchRotated(nums, target, 0, nums.length - 1);
	}

	// in sorted array
	public static int[] findRange(int[] nums, int target) {
		return new int[] { findRangeLeft(nums, target, 0, nums.length - 1),
				findRangeRight(nums, target, 0, nums.length - 1) };
	}

	public static int findRangeLeft(int[] nums, int target, int left, int right) {
		// not found
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;

		// found
		if (target == nums[mid]) {
			if (mid == left || (mid > left && nums[mid] != nums[mid - 1])) {
				return mid;
			}
		}

		if (target <= nums[mid]) {
			right = mid - 1;
		} else {
			left = mid + 1;
		}

		return findRangeLeft(nums, target, left, right);

	}

	public static int findRangeRight(int[] nums, int target, int left, int right) {
		// not found
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;

		// found
		if (target == nums[mid]) {
			if (mid == right || (mid < right && nums[mid] != nums[mid + 1])) {
				return mid;
			}
		}

		if (target >= nums[mid]) {
			left = mid + 1;
		} else {
			right = mid - 1;
		}

		return findRangeRight(nums, target, left, right);

	}

	static void pairExistsHash(int arr[], int sum) {
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int i = 0; i < arr.length - 1; i++) {
			m.put(arr[i], arr[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			if (m.containsKey(sum - arr[i])) {
				System.out.printf("pair exists: %s, %s\n", arr[i], sum - arr[i]);
				// already found a pair for this element.
				m.remove(arr[i]);
			}
		}

	}

	// S is string.
	// T is a set of characters.
	// Find smallest substring of S, which contains all characters from T?

	// S= GoogleogInc
	// T= Goe

//	String findSubString(String S, char[] T) {
//	    int t = T.length;
//	    int s = S.size()
//	    
//	    int left = 0, right = t;
//	    
//	    Arrays.sort(T);
//	    
//	    while (right < n) {
//	      s = S.substring(left, right);
//	      int minlength= INT_MAX;
//	      if (isSubset(s, T)) {
//	        left++;
//	        minlength = s.length;
//	      
//	      } else {
//	        right++;
//	      }
//	    
//	    }
//	}
//
//	boolean isSubset(String s, char[] T) {
//	//TODO
//	}
	
	public static int[] findSubArray(int[] arr, int sum) {
		
		int curr_sum = 0, left_index =0, right_index =0;
		
		for (int i=0;i<arr.length; i++) {
			curr_sum = arr[i];
			
			for (int j = i+1; j < arr.length; j++) {
				
				if (curr_sum == sum) {
					left_index = i;
					right_index = j;
					return new int[]{left_index, right_index-1};
				}
				
				curr_sum +=arr[j];
			}
			
			if (curr_sum == sum) {
				left_index = i;
				return new int[]{left_index, arr.length-1};
			}
		}
		
		return new int[]{-1,-1};
	}
	
	public static int[] findSubArrayHash(int[] arr, int sum) {
		
		int[] indexSum = new int[arr.length];
		
		indexSum[arr.length-1] = arr[arr.length-1];
		
		for (int i=arr.length-2;i>=0; i--) {
			
			indexSum[i] = arr[i] + indexSum[i+1];
		
		}
		
		return new int[]{-1,-1};
	}
	
	static int[][] rotateMatrix(int[][] matrix) {
        //transpose
        for (int i = 0; i < matrix.length; i++)  {
            for (int j = i; j < matrix.length; j++)  {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        int n = matrix.length;
        //transpose-reverse
        for (int i = 0; i < matrix.length; i++)  {
            for (int j = 0; j < n/2 ; j++)  {
                //replace i,j with i, n-j
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
        return matrix;
   }

	public static void main(String[] args) {
		int arr[] = { 7, 10, 4, 2, 19, 15, 1, 12, 18 };

		int rarr[] = { 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4 };
		printKthLargest(arr, 3);

		printKthSmallest(arr, 3);
		
		System.out.println(Arrays.toString(findSubArray(arr, 16)));
		
		System.out.println(Arrays.toString(findSubArray(arr, 19)));
		System.out.println(Arrays.toString(findSubArray(arr, 30)));

//		pairExists(arr, 22);
		pairExistsRotated(rarr, 11);
//		pairExistsHash(arr, 22);

		System.out.println(findRotationPoint(rarr, 0, rarr.length - 1));

		rarr = new int[] { 8, 9, 9, 0, 0, 1, 2, 3, 3, 4, 5, 6, 7, 7 };

		System.out.println(findRotationPoint(rarr, 0, rarr.length - 1));
		
		System.out.println("searching in rotated array");
		System.out.println(searchRotated(rarr, 1) == 1);
		System.out.println(searchRotated(rarr, 5) == 5);
		System.out.println(searchRotated(rarr, 2) == 2);
		System.out.println(searchRotated(rarr, 8) == 8);
		System.out.println(searchRotated(rarr, 9) == 9);
		System.out.println(searchRotated(rarr, 7) == 7);

		rarr = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(searchRotated(rarr, 0) == 0);

		rarr = new int[] { 2, 1 };

		System.out.println(searchRotated(rarr, 1) == 1);

		rarr = new int[] { 10, 7, 8, 9 };

		System.out.println(findRotationPoint(rarr, 0, rarr.length - 1));

		rarr = new int[] { 1, 2, 3 };

		System.out.println(findRotationPoint(rarr, 0, rarr.length - 1));

		rarr = new int[] { 2, 1 };

		System.out.println(findRotationPoint(rarr, 0, rarr.length - 1));

		arr = new int[] { 1, 1, 1, 2, 2, 3, 4, 4, 4, 4 };
		System.out.println("finding range");
		System.out.println(findRangeLeft(arr, 4, 0, arr.length - 1) == 6);
		System.out.println(findRangeLeft(arr, 1, 0, arr.length - 1) == 0);
		System.out.println(findRangeLeft(arr, 2, 0, arr.length - 1) == 3);

		arr = new int[] { 1, 1, 1, 2, 3, 4, 4, 4, 4, 5, 5, 6, 7, 8, 8, 8, 8, 8, 8, 9, 10, 11, 12, 12, 12 };
		System.out.println(Arrays.toString(findRange(arr, 4)).equals(Arrays.toString(new int[] { 5, 8 })));
		System.out.println(Arrays.toString(findRange(arr, 1)).equals(Arrays.toString(new int[] { 0, 2 })));

		arr = new int[] { 1, 2, 2, 4, 4, 4, 4, 4 };
		System.out.println(findRangeLeft(arr, 4, 0, arr.length - 1) == 3);

		System.out.println(Arrays.toString(findRange(arr, 4)).equals(Arrays.toString(new int[] { 3, 7 })));

		arr = new int[] { 1, 1, 2, 4, 4, 4, 4, 4 };
		System.out.println(findRangeRight(arr, 4, 0, arr.length - 1) == 7);
		System.out.println(findRangeRight(arr, 2, 0, arr.length - 1) == 2);
		System.out.println(findRangeRight(arr, 1, 0, arr.length - 1) == 1);

		System.out.println(Arrays.toString(findRange(arr, 4)).equals(Arrays.toString(new int[] { 3, 7 })));
		System.out.println(Arrays.toString(findRange(arr, 1)).equals(Arrays.toString(new int[] { 0, 1 })));
		
		//double med = findMedianSortedArrays(m, n);
		
		MovingWindow win = new MovingWindow();
		
		win.add(2);
		win.add(2);
		win.add(2);
		win.add(2);
		
		
		System.out.println(win.runningAvg());
		
		win.add(2);
		win.add(2);
		
		
		System.out.println(win.runningAvg());
		
		win.add(2);
		win.add(2);
		
		
		System.out.println(win.runningAvg());
		
		win.add(10);
		win.add(15);
		
		
		System.out.println(win.runningAvg());
		
		win.add(20);
		System.out.println(win.runningAvg());
		
		win.add(25);
		System.out.println(win.runningAvg());
		
		win.add(30);
		System.out.println(win.runningAvg());
	}

}
