package student_classes;

import java.util.*;


/**
 * <P>This version of the classic Binary Search Tree is similar
 * to the commonly found textbook implementations. My purpose
 * in creating the lab is to have you practice implementing
 * many commonly-referenced "binary tree" and "binary search tree" algorithms.
 * Note the distinction: not every Binary Tree is a Binary Search Tree. 
 * Hence. some operations belong to <b>all</b> trees (i.e., are shared
 * by all directed di-graphs), such as counting the number of nodes contained
 * in the tree, obtaining a left or right subtree, determining if a tree is a
 * leaf, etc. Other operations, however, only make sense in the setting 
 * of a Binary Search Tree, such as insertion, deletion, and searching because
 * in the case of BSTs, some ordering relation has been specified and this guides
 * the structure of the tree itself.
 *</P>
 *<br>
 *<h1>Differences/Exceptional Requirements</h1>
 *<br>
 * You will first implement a generic <code>BinaryTree</code> class that is
 * parameterized on general types (objects). This class must provide a handful
 * of "structural" operations, such as retriving left and right branches, retrieving
 * values from the root (topmost node of a tree), and a handful of predicates.
 * <br>
 * Your BST must <em>extend</em> the <code>BinaryTree</code> class, and
 * must, in addition, restrict the values that may reside within the BST to
 * homogeneous Comparable types. implementation must accept any homogeneous, 
 * <code>Comparable</code> objects as values. The
 * construction algorithm should treat place values
 * that are less than or equal to the root to the
 * left, and those greater to the right. Duplications
 * will be permitted.
 * <h2>Additional Static Method required</h2>
 * In addition to the "standard" dynamic methods (and those inherited from the
 * <code>BinaryTree</code> class), you must implement a <code>static</code> method
 * called <code>isBST(BinaryTree tree)</code> that returns <code>true</code> iff
 * <code>tree</code> satisfies all of the requirements for a Binary Search Tree (as
 * we've defined it):
 * <ul>
 * <li> It is empty;</li>
 * <li> It is a leaf node (contains no left or right children); or,</li>
 * <li> It is a binary tree whose left elements are less than or equal to its root,
 * and its right elements are greater than its root value.</li>
 * </ul>
 * 
 * <P>
 * Finally, your implementation should provide an
 * <code>Iterator</code> that allows users to iterate
 * through the nodes of a BST <em>in sort order</em>, i.e., 
 * the order in which nodes values' should be presented
 * by the Iterator must correspond to their <em>natural ordering</em>
 * as determined by the <code>compareTo</code> method.
 * @author UMD CS Department
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>>
	extends BinaryTree< T > implements Iterable<T> {


	//private BTNode root = null;
	/**
	 * Default ctor(): depending upon how one initialized the internal tree, 
	 * this method's body is likely empty.
	 */
	public BST() {
		// TODO Auto-generated constructor stub
	}
	private BST( BTNode node ) {
		this.root = node;
	}
	private BST<T> getLeftBST() {
		if (this.getLeft() == null) {
			return null;
		} else {
			BST<T> leftSub = new BST<T>(this.root.left);
			return leftSub;
		}
	}
	private BST<T> getRightBST() {
		if (this.getRight() == null) {
			return null;
		} else {
			BST<T> rightSub = new BST<T>(this.root.right);
			return rightSub;
		}
	}
	
	
	/**
	 * Per our discussion last week: the pattern established by this method will
	 * serve well as a guiding pattern for implementing add and remove logic. 
	 * <BR>
	 * This method returns true only when <code>ele</code> appears somewhere in the
	 * tree; Uses the <code>compareTo</code> method to guide our search through the
	 * underlying Binary Search tree.
	 * @param ele
	 * @return
	 */
	public boolean contains( T ele ) {
		if (this.isEmpty()) {
			return false;
		} else if (ele.compareTo(this.getValue()) == 0) {
			return true;
		} else if (ele.compareTo(this.getValue()) < 0) {
			if (this.getLeft() == null) {
				return false;
			} else {
				return this.getLeftBST().contains(ele);
			}
		} else if (ele.compareTo(this.getValue()) > 0) {
			if (this.getRight() == null) {
				return false;
			} else {
				return this.getRightBST().contains(ele);
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Inserts <code>value</code> in its correct position in the
	 * tree as determined by its natural ordering.
	 * 
	 * @param value
	 * @return
	 */
	public void add( T value ) {
		if (this.isEmpty()) {
			this.setValue(value);
		} else if (value.compareTo(this.getValue()) <= 0) {
			if (this.getLeft() == null) { //once you've reached the end
				this.setLeftValue(value);
			} else {
				this.getLeftBST().add(value); //not there yet, keep going left
			}
		} else if (value.compareTo(this.getValue()) > 0) {
			if (this.getRight() == null) {
				this.setRightValue(value);
			} else {
				this.getRightBST().add(value);
			}
		}
	}
	/**
	 * Remove <b>all</b> occurrences of <code>value</code> from the tree. 
	 * Do this by recursively walking the tree, splicing out
	 * nodes whose values matches <code>value</code>. 
	 * @param value
	 * @return
	 */
	public BST<T> remove( T value ) {
		if (value.compareTo(this.getValue()) < 0) { //if less go left
			this.root.left = this.getLeftBST().remove(value).root;
		} else if (value.compareTo(this.getValue()) > 0) {// if greater go right
			this.root.right = this.getRightBST().remove(value).root;
		} else { //you're at the node to delete
			if (this.isLeaf()) {
				this.root = null;
				return this;
			} else if (this.getLeft() != null && this.getRight() == null) {
				BST<T> temp = this.getLeftBST();
				this.root = temp.root;
				this.root.left = temp.root.left;
				this.root.right = temp.root.right;
				temp = null;
				return this;
			} else if (this.getRight() != null && this.getLeft() == null) {
				BST<T> temp = this.getRightBST();
				this.root = temp.root;
				this.root.left = temp.root.left;
				this.root.right = temp.root.right;
				temp = null;
				return this;
			} else {
				try {
					BinaryTree<T> max = this.getLeftBST().findMax();
					this.root.value = max.getValue();
					this.getLeftBST().remove(this.root.value);
				} catch (RuntimeException e) {
					try { // try going to the right for a viable node to switch
						BinaryTree<T> min = this.getRight().findMin();
						this.root.value = min.getValue();
						this.getRightBST().remove(this.root.value);
					} catch (RuntimeException f) { 
						return null;
					}
				}
			}
		}
		return this;
	}
	
	/**
	 * Untested, but valuable method ... you should consider
	 * writing this method as well as a printInOrder() method, which
	 * might help you in constructing your Iterator.
	 */
	public void printPreOrder() {
		ArrayList<T> containTreeList = new ArrayList<T>();
		printPreOrderHelper(this, containTreeList);
		containTreeList.toString();
	}
	//to help with the recursion
	private void printPreOrderHelper(BST<T> tree, ArrayList<T> list) {
		if (tree == null) {
			return;
		} else {
			list.add(tree.getValue());
			printPreOrderHelper(tree.getLeftBST(), list);
			printPreOrderHelper(tree.getRightBST(), list);
		}
	}
	/**
	 * private method that returns an ArrayList with all the elements of the BST
	 * put in an In Order sequence and is used for the iterator.
	 * @return
	 */
	private ArrayList<T> inOrder() {
		ArrayList<T> containTreeList = new ArrayList<T>();
		printInOrderHelper(this, containTreeList);
		return containTreeList;
	}
	// helper method for the recursion
	private void printInOrderHelper(BST<T> tree, ArrayList<T> list) {
		if (tree == null) {
			return;
		} else {
			printInOrderHelper(tree.getLeftBST(), list);
			list.add(tree.getValue());
			printInOrderHelper(tree.getRightBST(), list);
		}
	}
	/**
	 * Returns an Iterator<T>, that enumerates the values in 
	 * the underlying tree <b>in order</b>. (You might
	 * find it easier to create an internal list by
	 * performing an in-order traversal of the tree, adding
	 * to the internal list as you go and going from there!)
	 */
	@Override
	public Iterator< T > iterator() {
		// TODO Auto-generated method stub
		return new treeIterator();
	}
	
	private class treeIterator implements Iterator<T> {
		ArrayList<T> treeList = new ArrayList<T>(BST.this.inOrder()); //deep copy

		@Override
		public boolean hasNext() {
			if ( ! treeList.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public T next() {
			return treeList.remove(0);
		}
		
	}
	/**
	 * Returns true iff the <code>BinaryTree</code> is either empty, a leaf, or
	 * has an internal structure that satisfies the definition of the <code>BST</code>
	 * class, see above. (Note: this method should rely <b>only upon</b> the <code>public</code>
	 * interface (methods) provided by the <code>BinaryTree</code> class and Java's 
	 * <code>compareTo</code> logic.
	 * @param tree
	 * @return
	 */
	public static < T extends Comparable< T >> boolean isBST( BinaryTree< T > tree ) {
		/*if (tree == null || tree.isEmpty()) { //empty is a BST
			return true;
		} else if (tree.getLeft() == null && tree.getRight() == null) { //if its a leaf
			return true;
		} else if (tree.getLeft() == null && tree.getValue().compareTo(tree.getRight().findMin().getValue()) < 0) {
			return BST.isBST(tree.getRight()); //if the tree is skewed to the right or left is null
		} else if (tree.getRight() == null && tree.getValue().compareTo(tree.getLeft().findMax().getValue()) > 0) {
			return BST.isBST(tree.getLeft()); //if the tree is skewed to the left or right is null
		} else if (tree.getValue().compareTo(tree.getLeft().findMax().getValue()) > 0 
				&& tree.getValue().compareTo(tree.getRight().findMin().getValue()) < 0) { // if there are two children
			//if the "greatest lower bound" is less than the parent, and if 
			//if the "least upper bound" is greater than the parent then recurse through
				return (BST.isBST(tree.getLeft()) && BST.isBST(tree.getRight()));
		} else { //if the structure does not follow BST properties it fails
			return false;
		}*/
		if (tree.root == null || tree.isLeaf()) {
			return true; // Base case 1: empty tree is a BST and so is a singleton node
		} else if (!(tree.getLeft().getValue().compareTo(tree.getValue()) <= 0) && tree.getLeft() != null) {
			return false; // Base Case 2: not following BST structure
		}else if (!(tree.getRight().getValue().compareTo(tree.getValue()) > 0) && tree.getRight() != null) {
			return false;
		} else {
			return (isBST(tree.getLeft()) && isBST(tree.getRight()));
		}
	}
	
	/*private static <T extends Comparable<T>> boolean isBST_aux(BinaryTree<T> tree) {
		if (tree.root == null || tree.isLeaf()) {
			return true; // Base case 1: empty tree is a BST and so is a singleton node
		} else if (!(tree.getLeft().getValue().compareTo(tree.getValue()) <= 0) || !(tree.getRight().getValue().compareTo(tree.getValue()) > 0)) {
			return false; // Base Case 2: not following BST structure
		} else {
			return (isBST_aux(tree.getLeft()) && isBST_aux(tree.getRight()));
		}
	}*/
	
}
