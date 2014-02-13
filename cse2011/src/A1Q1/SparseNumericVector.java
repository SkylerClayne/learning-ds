package A1Q1;

import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value. The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like inner
 * products (projections). Note that location indices can be any integer from 1
 * to Long.MAX_VALUE. The representation is based upon a singly-linked list,
 * with header and trailer nodes to facilitate operations. The following methods
 * are supported: iterator, getSize, getFirst, add, remove, and project, which
 * projects the vector onto a second vector passed as a parameter.
 * 
 * @author jameselder
 * 
 * 
 *         Methods add, remove and project implemented by Skyler Layne,
 *         212166906. Notes have been added to explain implementation.
 */
public class SparseNumericVector implements Iterable {

	protected SparseNumericNode header;
	protected SparseNumericNode trailer;
	protected long size;

	/**
	 * Constructor
	 */
	public SparseNumericVector() {
		trailer = new SparseNumericNode(new SparseNumericElement(
				Long.MAX_VALUE, 0), null);
		header = new SparseNumericNode(new SparseNumericElement(1, 0), trailer);
		size = 0;
	}

	/**
	 * Iterator
	 */
	public Iterator<SparseNumericElement> iterator() { // iterator
		return new SparseNumericIterator(this);
	}

	/**
	 * @return number of non-zero elements in vector
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the first node in the list.
	 */
	public SparseNumericNode getFirst() {
		if (size == 0)
			return null;
		else
			return header.getNext();
	}

	/**
	 * Add the element to the vector. It is inserted to maintain the vector in
	 * increasing order of index. If an element with the same index already
	 * exists, its value is updated.
	 * 
	 * @param e
	 *            element to add
	 */
	public void add(SparseNumericElement e) {
		// implement this method

		// Create 2 dumbie nodes, to iterate through the "current" list.
		SparseNumericNode prev = this.header;
		SparseNumericNode next = this.header.getNext();
		SparseNumericNode addNode = new SparseNumericNode(e, null);

		// check if you're at the end of the list. i.e. first add.
		if (next.equals(trailer)) {
			prev.setNext(addNode);
			addNode.setNext(next);
			this.size++;
		} else {

			// if you're somewhere else in the list, look for where to add the
			// element.
			while (!(next.equals(trailer))) {
				if (addNode.getElement().getIndex() == next.getElement()
						.getIndex()) {
					next.getElement().setValue(addNode.getElement().getValue());

				}

				// add the node into the list, by setting the previous node to
				// point to the new node and the new node to point to the next
				// node.
				else if ((prev.getElement().getIndex() < addNode.getElement()
						.getIndex())
						&& (addNode.getElement().getIndex() < next.getElement()
								.getIndex())) {

					prev.setNext(addNode);
					addNode.setNext(next);

				}

				
				//	Get next elements.
				prev = prev.getNext();
				next = next.getNext();
			}
		}

	}

	/**
	 * If an element with the specified index exists, it is removed and the
	 * method returns true. If not, it returns false.
	 * 
	 * @param index
	 *            of element to remove
	 * @return true if removed, false if does not exist
	 */
	public boolean remove(Long index) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it
		// with your code.

		// initiate a check value to be false.
		boolean check = false;

		// Create a node starting at the head.
		SparseNumericNode prevNode = this.header;

		// Start looping through until you reach the index.
		while (prevNode.getNext().getElement().getIndex() != index) {

			// If you reach the trailer without removing, removed false.
			if (prevNode.equals(trailer)) {
				return false;
			}

			// Move to the next node.
			prevNode = prevNode.getNext();
		}

		// If you're at the node before the one of the desired index, set the
		// current node equal to the next, next node and set the desired node to
		// point to null.
		if (prevNode.getNext().getElement().getIndex() == index) {
			SparseNumericNode nextNode = prevNode.getNext();
			prevNode.setNext(nextNode.getNext());
			nextNode.setNext(null);
			check = true;
		}

		return check;
	}

	/**
	 * Returns the inner product of the vector with a second vector passed as a
	 * parameter. The vectors are assumed to reside in the same space. Runs in
	 * O(m+n) time, where m and n are the number of non-zero elements in each
	 * vector.
	 * 
	 * @param Y
	 *            Second vector with which to take inner product
	 * @return result of inner product
	 */

	public double project(SparseNumericVector Y) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it
		// with your code.

		// Create a sum to tabulate.
		double sum = 0;

		// Initialize iterators over the lists.
		SparseNumericIterator first = new SparseNumericIterator(this);
		SparseNumericIterator second = new SparseNumericIterator(Y);

		while (first.hasNext() && second.hasNext()) {

			// If their indexes are the same, multiply and add them to
			// the sum
			if (first.position.getElement().getIndex() == second.position
					.getElement().getIndex())
				sum += first.next().getValue() * second.next().getValue();

			// If the second vector index is higher than the first, get the next
			// first and keep the same second element.
			else if ((first.position.getElement().getIndex()) < (second.position
					.getElement().getIndex())) {
				first.next();
			}

			// only other option is if the second is smaller.
			else
				second.next();

		}

		return sum;
	}

	/**
	 * returns string representation of sparse vector
	 */

	public String toString() {
		String sparseVectorString = "";
		Iterator<SparseNumericElement> it = iterator();
		SparseNumericElement x;
		while (it.hasNext()) {
			x = it.next();
			sparseVectorString += "(index " + x.getIndex() + ", value "
					+ x.getValue() + ")\n";
			// System.out.println("(index " + x.getIndex() + ", value " +
			// x.getValue() + ")");
		}
		return sparseVectorString;

	}
}
