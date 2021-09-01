package my.learning;

import java.util.Arrays;

public class InsertionSort {

	/* Function to sort an array using insertion sort */
	static void insertionSort(int A[]) {
		int i, key, j;
		for (i = 1; i < A.length; i++) {
			key = A[i];
			j = i - 1;

			/*
			 * Move elements of A[0..i-1], that are greater than key, to one position ahead
			 * of their current position. This loop will run at most k times
			 */
			while (j >= 0 && A[j] > key) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = key;
			System.out.println(Arrays.toString(A));
		}
	}

	public static void main(String[] args) {
		int arr[] = {6, 5, 3, 2, 8, 10, 9};
		insertionSort(arr);
		
		System.out.println(Arrays.toString(arr));

	}

}
