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

			// 
			apq.add(e);
			upheap(apq.indexOf(e));

			// SORT APQ
			/*
			 * for(E a: apq) { // +'ve if a > e; 0 if a = e; otherwise -'ve if
			 * (comparator.compare(a, e) > 0){ apq.add(apq.indexOf(a) + 1 , e);
			 * upheap(apq.indexOf(a) + 1); } else { apq.add(e);
			 * upheap(apq.indexOf(e)); } }
			 */

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
		E removed = apq.remove(0);
		upheap(1);
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
		// implement this method
		//apq.add(pos - 1, apq.remove(pos));
		
		
		// +'ve if a > e; 0 if a = e; otherwise -'ve if
		// get the element at position compare to
		if( (pos > 0) && (comparator.compare(apq.get(pos), apq.get((pos-1)/2)) > 0)){
			
			swap(pos, (pos-1)/2);
			pos = (pos-1)/2;
			upheap(pos);
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
		// implement this method
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