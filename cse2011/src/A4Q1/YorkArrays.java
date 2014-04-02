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
	}

	/*
	 * Sorts the input array of Integers a using mergeSort and returns result.
	 * Sorting is stable. Throws a null pointer exception if the input array is
	 * null.
	 */
	public static Integer[] mergeSort(Integer[] a) throws NullPointerException {
		return (mergeSort(a, 0, a.length - 1));
	}

	/*
	 * Sorts the input subarray of Integers a[p...q] using MergeSort and returns
	 * result. Sorting is stable.
	 */
	private static Integer[] mergeSort(Integer[] a, int p, int q) {
		// implement this method.
	}

	/*
	 * Merges two sorted arrays of Integers into a single sorted array. Given
	 * two equal elements, one in a and one in b, the element in a precedes the
	 * element in b.
	 */
	private static Integer[] merge(Integer[] a, Integer[] b) {
		// implement this method.
	}
}