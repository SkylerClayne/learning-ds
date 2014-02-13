package A1Q3;

import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides a
 * method for determining the minimum element on the stack in O(1) time.
 * 
 * @author jameselder
 */
public class MinStack<E extends Comparable> extends Stack<E> {

	private Stack<E> minStack;
	private Stack<E> minElementStack = new Stack<E>();
	private E minElement;

	public MinStack() {
		minStack = new Stack<E>();
	}

	/* must run in O(1) time */
	public E push(E element) {

		// Check if the stack is empty or if the value is less than the current
		// minimum.
		if (minStack.isEmpty() || (element.compareTo(minElement) <= 0)) {
			// If it is, add it to the stack and save it to minElement.
			minElementStack.push(minElement);
			minStack.push(element);
			minElement = element;
		} else {

			// If its not smaller, add it to the stack.
			minStack.push(element);
		}

		return null; // Dummy return to satisfy compiler. Remove once coded.
	}

	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E pop() {

		E returnElement = minStack.pop();

		// If the element is the minElement than pop the minElementStack to go
		// back to the previous minElement.
		if (returnElement.compareTo(minElement) == 0) {
			minElement = minElementStack.pop();
		}
		return returnElement; // Dummy return to satisfy compiler. Remove once
								// coded.
	}

	/* Returns the minimum value currenctly on the stack. */
	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E min() {
		
		return minElement; // Dummy return to satisfy compiler. Remove once
							// coded.
	}
}