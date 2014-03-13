package A3Q1;
import java.util.Iterator;

/**
 * Extends the BinarySearchTree class to allow convenient access to entries
 * within a specified range of key values (findAllInRange).
 * @author jameselder
 */
public class BSTRange<K,V> extends BinarySearchTree<K,V>{


    /* Returns the lowest (deepest) position in the subtree rooted at pos
     * that is a common ancestor to positions with
     * keys k1 and k2, or to the positions they would occupy were they present.
     */
    protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2, Position<Entry<K, V>> pos) {
		//implement this method
    }

    /* Finds all entries in the subtree rooted at pos  with keys of k or greater
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllAbove(K k, Position<Entry<K, V>> pos, PositionList<Entry<K, V>> L) {
 		//implement this method
	}

    /* Finds all entries in the subtree rooted at pos with keys of k or less
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllBelow(K k, Position<Entry<K, V>> pos, PositionList<Entry<K, V>> L) {
 		//implement this method
	}

    /* Returns all entries with keys no less than k1 and no greater than k2,
     * in non-decreasing order.
     */
    public PositionList<Entry<K, V>> findAllInRange(K k1, K k2) {
		//implement this method
	}

}
