package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array. The order in which equal entries were added is
 * preserved.
 * 
 * @author jameselder
 * @param <E>
 *            The entry type.
 */
public class APQ<E> {

	private final ArrayList<E> apq; // will store the min heap
	private final Comparator<E> comparator; // to compare the entries
	private final Locator<E> locator; // to locate the entries within the queue

	/**
	 * Constructor
	 * 
	 * @param comparator
	 *            used to compare the entries
	 * @param locator
	 *            used to locate the entries in the queue
	 */
	public APQ(Comparator<E> comparator, Locator<E> locator)
			throws NullPointerException {
		if (comparator == null || locator == null) {
			throw new NullPointerException();
		}
		apq = new ArrayList<E>();
		apq.add(null); // dummy value at index = 0
		this.comparator = comparator;
		this.locator = locator;
	}

	/**
	 * Inserts the specified entry into this priority queue.
	 * 
	 * @param e
	 *            the entry to insert
	 * @throws NullPointerException
	 */
	public void offer(E e) throws NullPointerException {

		if (e.equals(null)) {
			throw new NullPointerException();
		} else {
			apq.add(e); // If the elements not null, add it to the list.
			upheap(apq.indexOf(e)); // Restore minheap property.
		}
	}

	/**
	 * Removes the entry at the specified location.
	 * 
	 * @param pos
	 *            the location of the entry to remove
	 * @throws BoundaryViolationException
	 */
	public void remove(int pos) throws BoundaryViolationException {

		// If there are elements in apq and index in question is > 0
		if ((apq.size() > pos) && (pos > 0)) {
			swap(pos, size()); // Swap position with the last element
			apq.remove(size()); // Remove node at last position in apq, O(1).
			downheap(pos); // Restore minheap property.
		} else {
			throw new BoundaryViolationException();
		}
		// implement this method

	}

	/**
	 * Removes the first entry in the priority queue.
	 */
	public E poll() {

		// grab the min element, the one at the top of the heap.
		E removed = apq.get(1);
		try {
			remove(1);
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!(apq.size() == 2)) {
			downheap(1); // restore minheap property.
		}
		return removed;
	}

	/**
	 * Returns but does not remove the first entry in the priority queue.
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return apq.get(1);
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public int size() {
		return apq.size() - 1; // dummy node at location 0
	}

	/**
	 * Shift the entry at pos upward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void upheap(int pos) {

		int parentLocation = (pos) / 2; // Index of parent.
		boolean posNodeIsNotNull = apq.get(pos) != null; // Check if node at pos
															// is not null.
		boolean parentNodeIsNotNull = apq.get(parentLocation) != null; // Check
																		// if
																		// node
																		// at
																		// pos
																		// is
																		// not
																		// null.

		if (!(posNodeIsNotNull || parentNodeIsNotNull)) {
			throw new NullPointerException();
		} else if (posNodeIsNotNull && parentNodeIsNotNull) {

			boolean posNodeLessThanParent = comparator.compare(apq.get(pos),
					apq.get(parentLocation)) < 0; // Check if node at pos is
													// less
													// than parent node.

			if ((pos > 0) && (posNodeLessThanParent)) {
				swap(pos, parentLocation); // Swap up element at pos to parent.
				pos = (pos) / 2; // Set pos equal to the parent location.
				upheap(pos); // Recurse.
			} else {
				return; // Drop off if recursive case fails.
			}
		}
	}

	/**
	 * Shift the entry at pos downward in the heap to restore the minheap
	 * property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void downheap(int pos) {
		// Basic algorithm opposite of upheap
		boolean posNodeIsNotNull = apq.get(pos) != null; // Check if node at pos
		// is not null.
		if (!(posNodeIsNotNull)) {
			throw new NullPointerException();
		}

		// index of pos's left and right child element for comparisons
		int leftElement = (2 * pos);
		int rightElement = (2 * pos) + 1;
		int lastElementInAPQ = apq.size() - 1; // last index of apq.
		int currentSmallest = pos; // Holds the index of the currentSmallest
									// node.
		boolean leftNodeExists = leftElement <= lastElementInAPQ; // Check if
																	// left node
																	// exists.
		boolean rightNodeExists = rightElement <= lastElementInAPQ; // Check if
		// right
		// node exists

		// Check if an there exists an element left node position in apq && if
		// it's less than node at pos set currentSmallest <- leftElement
		if ((leftNodeExists)
				&& (comparator.compare(apq.get(leftElement),
						apq.get(currentSmallest)) < 0)) {

			currentSmallest = leftElement;
		}

		// Check right side exists, if rightElement < currentSmallest then set
		// currentSmallest <- rightElement
		if ((rightNodeExists)
				&& (comparator.compare(apq.get(rightElement),
						apq.get(currentSmallest)) < 0)) {

			currentSmallest = rightElement;
		}

		// if the index of the currentSmallest is not the position of the
		// node in question then swap the node in question with the current
		// smallest and recurse till the node in question is the smallest node.
		if (currentSmallest != pos) {
			swap(pos, currentSmallest); // Swap the element at pos with the
										// current smallest.
			downheap(currentSmallest); // Recurse.
		} else {
			return; // Drop off if recursive case fails.
		}

	}

	/**
	 * Swaps the entries at the specified locations.
	 * 
	 * @param pos1
	 *            the location of the first entry
	 * @param pos2
	 *            the location of the second entry
	 */
	private void swap(int pos1, int pos2) {
		// Algorithm in comments at each step.

		// Create a place holder to hold the element at pos1.
		E placeHolder = apq.get(pos1);

		// Set element at pos1 to be the element currently in pos2.
		apq.set(pos1, apq.get(pos2));

		// Set placeHolder ( Element that was previously in pos1 ) to be in
		// pos2.
		apq.set(pos2, placeHolder);

	}
}