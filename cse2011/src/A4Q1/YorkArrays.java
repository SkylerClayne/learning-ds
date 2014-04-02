package A4Q1;

import java.util.*;

/**
 * 
 * Provides two static methods for sorting Integer arrays (heapSort and
 * mergeSort)
 * 
 * @author jameselder
 */
public class YorkArrays {

	/*
	 * Sorts the input array of Integers a using HeapSort. Result is returned in
	 * a. Makes use of java.util.PriorityQueue. Sorting is NOT in place -
	 * PriorityQueue allocates a separate heap-based priority queue. Not a
	 * stable sort, in general. Throws a null pointer exception if the input
	 * array is null.
	 */
	public static void heapSort(Integer[] a) throws NullPointerException {
		// implement this method.
		PriorityQueue<Integer> pc = new PriorityQueue<Integer>();
		if (a.length > 0) {
			// Add the elements to the heap
			for (int i = a.length - 1; i > 0; i--) {
				pc.add(a[i]);
			}
			// Return the elements to the initial array.
			for (int i = 0; i < a.length; i++) {
				a[i] = pc.poll();
			}
		} else {
			throw new NullPointerException("Array is empty!");
		}
	}

	/*
	 * Sorts the input array of Integers a using mergeSort and returns result.
	 * Sorting is stable. Throws a null pointer exception if the input array is
	 * null.
	 */
	public static Integer[] mergeSort(Integer[] a) throws NullPointerException {

		// empty array is sorted already
		if (a.length == 0) {
			throw new NullPointerException("Array is empty!");
		}

		return (mergeSort(a, 0, a.length - 1));
	}

	/*
	 * Sorts the input subarray of Integers a[p...q] using MergeSort and returns
	 * result. Sorting is stable.
	 */
	private static Integer[] mergeSort(Integer[] a, int p, int q) {
		// implement this method.
		int midPoint = (p + q) / 2;
		Integer[] leftArray;
		Integer[] rightArray;

		// recursion base case
		if (p == q) {
			leftArray = new Integer[1];
			leftArray[0] = a[p];
			return leftArray;
		}

		leftArray = mergeSort(a, p, midPoint);
		rightArray = mergeSort(a, midPoint + 1, q);

		return merge(leftArray, rightArray);
	}

	/*
	 * Merges two sorted arrays of Integers into a single sorted array. Given
	 * two equal elements, one in a and one in b, the element in a precedes the
	 * element in b.
	 */
	private static Integer[] merge(Integer[] a, Integer[] b) {
		// implement this method.
		// create new array with length sum of a and b.
		Integer[] merged = new Integer[a.length + b.length];
		int indexa = 0;
		int indexb = 0;
		int indexMerged = 0; // index of arrays
		int lengtha = a.length - 1;
		int lengthb = b.length - 1;

		while (indexa <= lengtha && indexb <= lengthb) {
			// if element in a < b add a to merged, increment indexa.
			if (a[indexa] <= b[indexb]) {
				merged[indexMerged++] = a[indexa++];
			}
			// else if b < a add b to merged, increment indexb.
			else if (a[indexa] > b[indexb]) {
				merged[indexMerged++] = b[indexb++];
			} else {
				// something bad happened
			}
		}

		// Finish the arrays off
		if (indexa <= lengtha) {
			while (indexa <= lengtha)
				merged[indexMerged++] = a[indexa++];

		} else if (indexb <= lengthb) {
			while (indexb <= lengthb)
				merged[indexMerged++] = b[indexb++];

		}
		return merged;

	}
}