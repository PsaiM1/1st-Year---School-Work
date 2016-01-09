package student_classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Consumer;





/**
 * A Binary Search Tree data-structure that maintains a count
 * of repetitive values (as nodes).
 * 
 * @author UMD CS Dept.
 *
 * @param <T>
 */
public class DenseSearchTree< T extends Comparable<T> > 
					implements Iterable<T> {

	// declare properties, inner classes, etc.
	class TNode {
		T	value;
		int count; //acts as the counter for the multiplicity of values
		TNode left, right;
		
		TNode( T val, TNode l, TNode r ) { //note: full constructor
			this.value = val;
			this.left = l;
			this.right = r;
			this.count = 1;
		}
		
		TNode( T val ) {//note: partial constructor
			this( val, null, null );
		}
		public String toString() {
			return "L=" + this.left + " :R=" + this.right;
		}
	} 
	
	private DenseSearchTree<T> getLeft() {
		if (this.root.left == null) {
			return null;
		} else {
			DenseSearchTree<T> leftSub = new DenseSearchTree<T>(this.root.left);
			return leftSub;
		}
	}
	private DenseSearchTree<T> getRight() {
		if (this.root.right == null) {
			return null;
		} else {
			DenseSearchTree<T> rightSub = new DenseSearchTree<T>(this.root.right);
			return rightSub;
		}
	}
	
	private int getCount() {
		return this.root.count;
	}
	
	
	private TNode root = new TNode(null);
	private DenseSearchTree(TNode node) {
		this.root = node;
	}
	public DenseSearchTree() { }
	/**
	 * Note: our trees follow a slightly different convention regarding 
	 * both our ordering relation and the placement of duplicates, viz:
	 * 
	 * Left branch contains all elements < tree.value;
	 * Right branch contains all elements >= tree.value.
	 * 
	 * Rather than place duplicate elements on the right branch,
	 * each Tree Node maintains a count of copies. For example, 
	 * if we had a tree of Integers containing two instances of the
	 * number 1, our Tree Node would logically appear as 
	 * [ left-branch 1:2 right-branch ], where
	 * 1:2 means 2 copies of the integer 1.
	 * 
	 * Thus, your add logic will either make a new node and insert
	 * it in the correct position in the tree, or it will find a
	 * node with the value (key) equal to the value you are adding,
	 * and increment its count.
	 * 
	 * This simplifies your remove logic: if the node's count
	 * is > 0, then decrement by 1, otherwise invoke the remove
	 * logic to physically remove the node and replace it with a 
	 * Binary Search Tree.
	 * 
	 * Finally, the "price you pay" for this simplification is that
	 * your Iterator (which presents an "in-order" view of the tree),
	 * needs to "inflate" or "expand" each node. That is, if you have
	 * a node 
	 * [ left 3:4 right ]
	 * then 4 instances of the integer 3 need to be created and 
	 * put into the iteration.
	 * 
	 * @param element
	 */
	public void add( T element ) {
		if (this.root.value == null) {
			this.root.value = element;
		} else if (element.compareTo(this.root.value) < 0) {
			if (this.root.value.equals(element)) { //if it encounters an element thats the same
				this.root.count++;
				return;
			}
			if (this.getLeft() == null) { //once you've reached the end
				TNode temp = new TNode(element);
				this.root.left = temp;
			} else {
				this.getLeft().add(element); //not there yet, keep going left
			}
		} else if (element.compareTo(this.root.value) >= 0) {
			if (this.root.value.equals(element)) { //if it encounters an element thats the same
				this.root.count++;
				return;
			}
			if (this.getRight() == null) {
				TNode temp = new TNode(element);
				this.root.right = temp;
			} else {
				this.getRight().add(element);
			}
		}
	}
	
	/**
	 * Returns true if at least instance of <code>target</code>
	 * is found in tree.
	 * 
	 * @param target
	 * @return
	 */
	public boolean contains( T target ) {
		return contains(this, target);
	}
	
	private boolean contains (DenseSearchTree<T> tree, T target) {
		boolean contain = false;
		if (tree == null) { //item is not in the tree
			contain = false;
		} else if (target.compareTo(tree.root.value) == 0) { //found the target
			contain = true;
		} else if (target.compareTo(tree.root.value) < 0) { //go to the left if target is smaller and not found
			contain = contains(tree.getLeft(), target);
		} else if (target.compareTo(tree.root.value) > 0){  //go to the right if the target is greater and not found
			contain = contains(tree.getRight(), target);
		} 
		return contain;
	}
	
	/**
	 * Returns an int >= 0, indicating how many occurrences
	 * of <code>target</code> reside in the tree. Note, this
	 * function returns 0 when the item is NOT found in tree.
	 * 
	 * @param target
	 * @param tree
	 * @return
	 */
	public int count( T target ) {
		if (!this.contains(target)) {
			return 0;
		}
		
		if (target.compareTo(this.root.value) < 0) {
			if (this.root.value.equals(target)) { //if it encounters an element thats the same
				return this.root.count;
			} else {
				if (this.getLeft() == null) { //if you went all the way
					return 0;
				}
				return this.getLeft().count(target); //not there yet, keep going left
			}
		} else if (target.compareTo(this.root.value) >= 0) {
			if (this.root.value.equals(target)) { //if it encounters an element thats the same
				return this.root.count;
			} else {
				if (this.getRight() == null) {//if you went all the way
					return 0;
				}
				return this.getRight().count(target);
			}
		} else { //target does not exist
			return 0;
		}
	}
	
	/**
	 * Returns "inflated" size of tree, meaning a count of
	 * all keys in the tree
	 * @return
	 */
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return getSize(this);
		}
	}
	
	private int getSize(DenseSearchTree<T> tree) {
		if (tree == null) {
			return 0;
		} else {
			return (tree.root.count + getSize(tree.getLeft()) + getSize(tree.getRight()));
		}
	}

	/**
	 * Returns the set representation of this Tree. In this case,
	 * the set will contain unique elements (i.e., it should omit
	 * multiple instances) that comprise the tree. Depending upon
	 * your internal representation, this may be challenging. If 
	 * you are having trouble, examine the Java online API; look
	 * under Collections. Study the concrete classes that implement
	 * the Set interface ... consider ConcurrentSkipLists ....
	 * @return
	 */
	public Set<T> asSet() {
		ConcurrentSkipListSet<T> treeTempList = new ConcurrentSkipListSet<T>();
		ArrayList<T> temp = this.inOrder();
		for (T element : temp) {
			treeTempList.add(element);
		}
		Set<T> treeSet = treeTempList;
		return treeSet;
	}
	
	/**
	 * Somewhat streamlined or simplified version of the classic
	 * BST remove algorithm. Because we're maintaining counts of 
	 * keys on each node, many times this method find the node
	 * whose value (key) equals the <code>target</code> and decrements
	 * the counter by 1. If that would result in the counter going to 
	 * 0, however, then the remove logic finds the greatest in order
	 * successor and replaces the node to be removed with that, and 
	 * then continues to remove the in order successor whose value
	 * was used to re-label the node to be removed.
	 * 
	 * @param target
	 */
	public void remove( T target ) {
		this.root = removal(target).root;
		
	}
	
	private DenseSearchTree<T> removal(T target) {
		if (target.compareTo(this.root.value) < 0) { //if less go left
			if (this.contains(target)) {
				this.root.left = this.getLeft().removal(target).root;
			} else { //the target is not in this tree so get out
				return this;
			}
		} else if (target.compareTo(this.root.value) > 0) {// if greater go right
			if (this.contains(target)) {
				this.root.right = this.getRight().removal(target).root; 
			} else { //target not in tree 
				return this;
			}
		} else { //you're at the node to delete
			if (this.getLeft() == null && this.getRight() == null) {//if its a leaf
				if (this.root.count > 1) {
					this.root.count--;
					return this;
				} else {
					this.root.count--;
					this.root = null;
					return this;
				}
			} else if (this.getLeft() != null && this.getRight() == null) {
				if (this.root.count > 1) {
					this.root.count--;
					return this;
				}
				DenseSearchTree<T> temp = this.getLeft();
				this.root.count--;
				this.root = temp.root;
				this.root.left = temp.root.left;
				this.root.right = temp.root.right;
				temp = null;
			} else if (this.getRight() != null && this.getLeft() == null) {
				if (this.root.count > 1) {
					this.root.count--;
					return this;
				}
				DenseSearchTree<T> temp = this.getRight();
				this.root.count--;
				this.root = temp.root;
				this.root.left = temp.root.left;
				this.root.right = temp.root.right;
				temp = null;
			} else { //find the min on the right to replace with
				if (this.root.count > 1) {
					this.root.count--;
					return this;
				}
				this.root.value = this.getLeft().getMax();
				this.getLeft().removal(this.root.value);
			} 
		}
		return this;
	}
	
	
	
	/**
	 * Returns a value of type T or throws an <code>IllegalStateException</code>
	 * if this function is called on an empty tree.
	 * 
	 * @return
	 */
	public T getMin() {
		if (this.root.left == null) {//if there's nothing to the left
			return this.root.value;
		} else if (this.getLeft().root.value == null) { //if the left is there but there's nothing in it
			return this.root.value;
		} else {
			return this.getLeft().getMin();
		}
	}
	
	/**
	 * Returns the max value (of type T) or throws an 
	 * <code>IllegalStateException</code> if this function
	 * is called on an empty tree.
	 * 
	 * @return
	 */
	public T getMax() {
		if (this.root.right == null) {//if there's nothing to the right
			return this.root.value;
		} else if (this.getRight().root.value == null) { //if the right is there but there's nothing in it
			return this.root.value;
		} else {
			return this.getRight().getMax();
		}
	}
	
	/** For sanity's sake ... please feel free to implement 
	 * whatever makes sense to you here ... this method is NOT
	 * tested.
	 */
	public String toString() {
		throw new UnsupportedOperationException("you must implement this method");
	}
	/**
	 * Returns an iterator over the DenseSearchTree. Note, this iterator should
	 * present an "in order" view of the keys in the tree.
	 */
	
	private ArrayList<T> inOrder() {
		ArrayList<T> containTreeList = new ArrayList<T>();
		printInOrderHelper(this, containTreeList);
		return containTreeList;
	}
	// helper method for the recursion and prints the duplicates
	private void printInOrderHelper(DenseSearchTree<T> tree, ArrayList<T> list) {
		if (tree == null) {
			return;
		} else {
			int counter = tree.getCount();
			printInOrderHelper(tree.getLeft(), list);
			while (counter > 1) {
				list.add(tree.root.value);
				counter--;
			}
			list.add(tree.root.value);
			printInOrderHelper(tree.getRight(), list);
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new treeIterator();
	}
	
	public class treeIterator implements Iterator<T> {
		DenseSearchTree<T> tree = DenseSearchTree.this;
		ArrayList<T> treeList;
		
		private int pos;
		public treeIterator() {
			treeList = tree.inOrder();
			pos = 0;
		}
		@Override
		public boolean hasNext() {
			return pos < treeList.size();
		}

		@Override
		public T next() {
			return treeList.get(pos++);
		}
		
	}
}
