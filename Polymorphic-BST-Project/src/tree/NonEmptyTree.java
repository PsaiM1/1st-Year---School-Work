package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		if (key.compareTo(this.key) == 0) { //you're already at the destination
			return this.value;
		} else if (key.compareTo(this.key) < 0) { // the key is less so go to the left
			return this.left.search(key);
		} else if (key.compareTo(this.key) > 0) { // the key is bigger to go to the right
			return this.right.search(key);
		}
		return null;
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		if (this.key.compareTo(key) == 0) { //make the destination node have this value; "update the value"
			this.value = value;
		} else if (this.key.compareTo(key) > 0) { //go to the left if you're not there yet
			this.left = this.left.insert(key, value);
		} else if (this.key.compareTo(key) < 0) { //go to the right if you're not there yet
			this.right = this.right.insert(key, value);
		}
		return this;
	}
	
	public Tree<K, V> delete(K key) {
		if (this.key.compareTo(key) > 0) { //if this key is greater than input key
			this.left.delete(key);
		} else if (this.key.compareTo(key) < 0) { //if this key is less than the input key
			this.right.delete(key);
		} else { // you're at the node to delete
			try { //try searching the left for a viable node to switch
				K max = this.left.max();
				this.value = this.search(max);
				this.key = max;
				this.left = this.left.delete(this.key);	
			} catch (TreeIsEmptyException e) {
				try { // try going to the right for a viable node to switch
					K min = this.right.min();
					this.value = this.search(min);
					this.key = min;
					this.right = this.right.delete(this.key);
				} catch (TreeIsEmptyException f) { //change the node to the singleton
					return EmptyTree.getInstance();
				}
			}
		}
		return this;
	}

	public K max() {
		try {
			this.right.max();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
		return this.key;
	}

	public K min() {
		try {
			this.left.min();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
		return this.key;
	}

	public int size() { // recurse through the whole tree of both sides and it will add both sides up
		return 1 + this.left.size() + this.right.size();
	}

	public void addKeysToCollection(Collection<K> c) {
		this.left.addKeysToCollection(c);
		this.right.addKeysToCollection(c);
		c.add(this.key);
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		if (this.key.compareTo(fromKey) < 0) {
			return this.right.subTree(fromKey, toKey);
		} else if (this.key.compareTo(toKey) > 0) {
			return this.left.subTree(fromKey, toKey);
		} else {
			return new NonEmptyTree<K, V>(this.key, this.value, this.left.subTree(fromKey, toKey), this.right.subTree(fromKey, toKey));
		}
		
	}
	
	public int height() {
		int leftHeight = this.left.height();
		int rightHeight = this.right.height();
		if (leftHeight < rightHeight) {
			return rightHeight + 1;
		} else {
			return leftHeight + 1;
		}
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) { //note this is left root right traversal
		this.left.inorderTraversal(p);
		p.performTask(this.key, this.value);
		this.right.inorderTraversal(p);
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) { //this is the reverse of the previous: right root left
		this.right.rightRootLeftTraversal(p);
		p.performTask(this.key, this.value);
		this.left.rightRootLeftTraversal(p);
	}	
}