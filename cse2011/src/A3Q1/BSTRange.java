package A3Q1;

import java.util.Iterator;

/**
 * Extends the BinarySearchTree class to allow convenient access to entries
 * within a specified range of key values (findAllInRange).
 * 
 * @author jameselder
 */
public class BSTRange<K, V> extends BinarySearchTree<K, V> {

	/*
	 * Returns the lowest (deepest) position in the subtree rooted at pos that
	 * is a common ancestor to positions with keys k1 and k2, or to the
	 * positions they would occupy were they present.
	 */
	protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2,
			Position<Entry<K, V>> pos) {

		if ((k1.equals(null)) || (k2.equals(null)) || (pos.equals(null))) {
			throw new NullPointerException("Passed a null value!");
		}
		Position<Entry<K, V>> rootPos = pos;
		boolean posLessThan = C.compare(k2, key(pos)) < 0;
		boolean greaterThan = C.compare(k1, key(pos)) > 0;
		// implement this method
		if (isInternal(rootPos)) {
			return rootPos;
		}
		if (posLessThan) {
			rootPos = findLowestCommonAncestor(k1, k2, left(pos)); // recurse
																	// left.
		} else if (greaterThan) {
			rootPos = findLowestCommonAncestor(k1, k2, right(pos)); // recurse
																	// right.
		} else {
			try {
				throw new Exception("Something wrong when searching for LCA");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rootPos;
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or greater
	 * and copies them to L, in non-decreasing order.
	 */
	protected void findAllAbove(K k, Position<Entry<K, V>> pos,
			PositionList<Entry<K, V>> L) {

		if ((k.equals(null)) || (pos.equals(null)) || (L.equals(null))) {
			throw new NullPointerException("Passed a null value");
		}
		Position<Entry<K, V>> rootPos = pos;
		Entry<K, V> element = pos.element();
		boolean posLtOrEt = C.compare(k, key(pos)) <= 0; // k less than or equal
															// to value at pos.
		// Base case.
		if (isInternal(pos)) {
			return;
		}
		// recursive cases.
		else if (posLtOrEt) {
			// Go left, add then look right.
			findAllAbove(k, left(rootPos), L);
			L.addLast(element);
			findAllAbove(k, right(rootPos), L);
		} else if (!(posLtOrEt)) {
			findAllAbove(k, right(rootPos), L);
		} else {
			try {
				throw new Exception(
						"Something went wrong when searching through tree!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or less and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllBelow(K k, Position<Entry<K, V>> pos,
			PositionList<Entry<K, V>> L) {
		// implement this method
		if ((k.equals(null)) || (pos.equals(null)) || (L.equals(null))) {
			throw new NullPointerException("Passed a null value!");
		}

		Position<Entry<K, V>> rootPos = pos;
		Entry<K, V> element = pos.element();
		// equal
		// to value at
		// pos.
		// Base case.
		if (isExternal(pos)) {
			return;
		} else if (C.compare(k, key(rootPos)) >= 0) {

			// Go left, add then look right.
			findAllBelow(k, left(rootPos), L);
			L.addLast(element);
			findAllBelow(k, right(rootPos), L);
		} else if (!(C.compare(k, key(rootPos)) >= 0)) {
			findAllBelow(k, left(rootPos), L);

		} else {
			try {
				throw new Exception("Something went wrong whe recursing!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Returns all entries with keys no less than k1 and no greater than k2, in
	 * non-decreasing order.
	 */
	public PositionList<Entry<K, V>> findAllInRange(K k1, K k2) {

		if ((k1.equals(null)) || (k2.equals(null))) {
			throw new NullPointerException("Passed a null value!");
		}

		PositionList<Entry<K, V>> nodeList = new NodePositionList<Entry<K, V>>(); // Create
																					// list
																					// to
																					// return.
		Position<Entry<K, V>> rootPos = root(); // Start at the root
		rootPos = findLowestCommonAncestor(k1, k2, rootPos);
		if (isExternal(rootPos)) {
			return nodeList;
		}

		// Go left, add then look right.
		findAllAbove(k1, left(rootPos), nodeList);
		nodeList.addLast(rootPos.element());
		findAllBelow(k2, right(rootPos), nodeList);

		return nodeList;
	}

}
