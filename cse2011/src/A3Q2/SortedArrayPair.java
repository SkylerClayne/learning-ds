package A3Q2;

/**
 * Stores a pair of sorted arrays of equal length. The arrays are assumed to be
 * disjoint, i.e., none of the elements are repeated, either within or between
 * the arrays. Elements within each array are stricly increasing in value. A
 * method is provided to find the kth smallest value in the union of the two
 * arrays, in O(log k) time.
 * 
 * @author jameselder
 */
public class SortedArrayPair<E extends Comparable<E>> {
	E[] A1, A2;

	private int middle;

	/* Note that the constructor assumes the arrays to be pre-sorted. */
	public SortedArrayPair(E[] array1, E[] array2) throws NullPointerException,
			UnmatchedArrayPairException {
		if (array1 == null | array2 == null) {
			throw new NullPointerException();
		}
		if (array1.length != array2.length) {
			throw new UnmatchedArrayPairException("Unmatched array lengths");
		}
		A1 = array1;
		A2 = array2;
	}

	/**
	 * Returns the kth smallest value in the union of the two arrays, in O(log
	 * k) time. An exception is thrown if k is not positive, or if it is more
	 * than double the length of the arrays.
	 * 
	 * @param k
	 * @return
	 * @throws RankOutOfRangeException
	 */
	public E kthSmallestOfUnion(int k) throws RankOutOfRangeException {
		// implement this method
		int indexA1 = 0;
		int indexA2 = 0;

		if (k < 1 || k > (this.A1.length - 1)) {
			throw new RankOutOfRangeException("Range out of bounds!");
		}

		while (((indexA1 + indexA2) + 2) <= k) {
			if (A1[indexA1].compareTo(A2[indexA2]) < 0) {
				indexA1 = indexA1 + 1;
			} else if (A1[indexA1].compareTo(A2[indexA2]) > 0) {
				indexA2 = indexA2 + 1;
			}
		}

		if (A1[indexA1].compareTo(A2[indexA2]) < 0) {
			return A1[indexA1];
		} else if (A1[indexA1].compareTo(A2[indexA2]) > 0) {
			return A2[indexA2];
		} else
			return null;

	}

}