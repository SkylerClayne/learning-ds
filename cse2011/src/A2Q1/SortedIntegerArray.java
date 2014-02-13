package A2Q1;

import java.util.*;

/**
 * Represents a sorted integer array. Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * 
 * @author jameselder
 */
public class SortedIntegerArray {

	protected int[] sortedIntegerArray;

	public SortedIntegerArray(int[] integerArray) {
		sortedIntegerArray = integerArray.clone();
		Arrays.sort(sortedIntegerArray);
	}

	/**
	 * Determines whether the array contains two elements that sum to a given
	 * integer k. Runs in O(n) time, where n is the length of the array.
	 * 
	 * @author jameselder
	 */
	public boolean kPairSum(Integer k) {

		if (this.sortedIntegerArray.length == 0
				|| this.sortedIntegerArray.length == 1) {
			// if the array is empty or only contains one element.
			return false;
		} else {
			// recurse through using the indexes of the array.
			
			int lastArrayElement = this.sortedIntegerArray.length - 1;
			int firstArrayElement = 0;
			
			boolean result = search(k, firstArrayElement, lastArrayElement);
			return result;
		}

	}

	private boolean search(Integer k, int left, int right) {

		boolean result = false;
		int sum = this.sortedIntegerArray[left]
				+ this.sortedIntegerArray[right];

		// Recursive algorithm definition:
		// look at the end of the array and the start add them.
		// if sum > k decrement the right if sum < k increment left and recurse.
		// go till left == right.

		if (sum == k) {
			// If the sum exists return true.
			return true;
		} else if (left == right) {
			// If you're at the middle and haven't found a sum, return false.
			return false;
		} else {
			if (sum > k) {
				// if the sum of the two elements is larger than the desired
				// integer, move right index 1 to the left, and reset sum.
				sum = 0;
				result = search(k, left, right - 1);
			} else {
				// else reset the sum, and move the left index up the array.
				sum = 0;
				result = search(k, left + 1, right);
			}
		}

		return result;
	}
}

// {1, 2, 3, 4, 5} 