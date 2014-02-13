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
		// implement this method

		if (e.equals(null)) {
			throw new NullPointerException();
		} else {

			apq.add(e);
			upheap(apq.indexOf(e));

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

		if ((apq.size() > pos) && (pos > 0)) {
			apq.remove(pos);
			downheap(pos);
		} else {
			throw new BoundaryViolationException();
		}
		// implement this method

	}

	/**
	 * Removes the first entry in the priority queue.
	 */
	public E poll() {

		// implement this method
		E removed = apq.remove(1);
		if (!(apq.size() == 2)) {
			downheap(1);
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

		// Index of parent
		int parentLocation = (pos) / 2;

		// +'ve if a > e; 0 if a = e; otherwise -'ve i

		if (apq.get(pos) != null && apq.get(parentLocation) != null) {
			if ((pos > 0)
					&& (comparator.compare(apq.get(pos),
							apq.get(parentLocation)) < 0)) {

				// Swap up element at pos to parent.
				swap(pos, parentLocation);

				// Set pos equal to the parent location.
				pos = (pos) / 2;

				// Recurse.
				upheap(pos);
			} else {
				return;
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

		// position of pos's left and right child element for comparisons
		int leftElement = (2 * pos);
		int rightElement = (2 * pos) + 1;
		int listSize = apq.size() - 1;

		// holds index of the smallest element among pos, leftElement, and
		// rightElement
		int currentSmallest = pos;

		// Check if a left node exists && if it's less than node at pos set
		// currentSmallest <- leftElement
		if ((leftElement <= listSize)
				&& (comparator.compare(apq.get(leftElement), apq.get(pos)) < 0)) {

			currentSmallest = leftElement;
		}

		/*
		 * else {
		 * 
		 * currentSmallest = pos; // else set currentSmallest <- pos }
		 */

		// Check right side exists, if rightElement < currentSmallest then set
		// currentSmallest <- rightElement
		if ((rightElement <= listSize)
				&& (comparator.compare(apq.get(rightElement),
						apq.get(currentSmallest)) < 0)) {

					currentSmallest = rightElement;
		}
		// If node at pos holds an element smaller than both the left and right
		// children, then the max-heap property already held, and we need do
		// nothing more. Otherwise, we need to swap node at pos with the larger
		// of the two children, and then recurse down the heap from the
		// larger
		// child.

		
		if (currentSmallest != pos) {
			swap(pos, currentSmallest);
			downheap(currentSmallest);
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