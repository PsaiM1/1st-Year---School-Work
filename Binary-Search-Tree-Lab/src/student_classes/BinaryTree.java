package student_classes;
/**
 * Common "base class" for any and all directed di-graphs (or, what we will
 * be creating in this class). This class is extended by the Binary Search Tree
 * class (qv), and therefore relies upon some of its properties (indirectly) and
 * methods. Note that all of the methods belonging to this class address
 * the <emph>structure</emph> of the tree a di-graph, i.e., independently of what
 * values might be stored at these locations. In other words: the operations defined
 * by this class are common to all directed di-graphs, which means that they are
 * appropriate to the BST (binary search tree) that you will be required to define
 * using this as a base class. 
 * <br>
 * <P>Observe that the signatures for many of the methods on this class return
 * <emph>subtree</emph>s and <b>never</b> <code>BTNode</code>s. This ensures that
 * clients never know anything about the underlying representation of these objects
 * or any of their descendants. </P>
 * @author UMD CS Dept.
 *
 * @param <T>
 */
public class BinaryTree<T> {
	
	class BTNode {
		T	value;
		BTNode left, right;
		
		BTNode( T val, BTNode l, BTNode r ) { //note: full constructor
			this.value = val;
			this.left = l;
			this.right = r;
		}
		
		BTNode( T val ) {//note: partial constructor
			this( val, null, null );
		}
		public String toString() {
			return "L=" + this.left + " :R=" + this.right;
		}
	} // closes class BTNode
	/*
	 * You may choose to rename this private and use the public accessors, 
	 * or you may leave this protected and use a mixture of public accessors
	 * and/or direct field referrences.
	 */
	protected BTNode root=null;
	/** default ctor, required for inheritance mechanism.. */
	public BinaryTree() { }
	
	protected BinaryTree( BTNode root ) {
		this.root = root;
		this.root.value = root.value;
		this.root.left = root.left;
		this.root.right = root.right;
	}
	
	/* Public Interface: */
	/** Returns true iff this tree is empty. Note: this is the only "safe" method
	 * that is provided for this purpose. Clients are expected to use this method
	 * rather than relying upon catching a runtime exception.
	 * @return
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Given any non-empty binary tree, returns the value at its root. Note: if
	 * the <code>tree</code> is empty, then a runtime exception is thrown.
	 * @param tree
	 * @return
	 */
	public T getValue() {
		return this.root.value;
	}
	/**
	 * Replaces the "value" stored on this tree (its root) with <code>newValue</code>. 
	 * @param newValue
	 */
	public void setValue( T newValue ) {
		if (this.root == null) {
			BTNode temp = new BTNode(newValue);
			this.root = temp;
		} else {
			this.root.value = newValue;
		}
	}
	/**
	 * Returns true iff this tree (or subtree) is non-null and
	 * has a non-null left branch.
	 * @return
	 */
	public boolean hasLeft() {
		if (this.root != null && this.root.left != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Throws an unspecified exception if this tree is empty; otherwise,
	 * returns the left subtree attached to this tree. Note, the left
	 * subtree may be null.
	 * @return
	 */
	public BinaryTree< T > getLeft() {
		if (this.root.left == null) {
			return null;
		} else {
			BinaryTree<T> leftSub = new BinaryTree<T>(this.root.left);
			return leftSub;
		}
	}
	/**
	 * Replaces value of left child with <code>value</code>.
	 * @param value
	 */
	public void setLeftValue( T value ) {
		if (this.isEmpty()) {
			throw new RuntimeException();
		}
		if (this.root.left == null) {
			this.root.left = new BTNode(value);
		} else {
			this.root.left.value = value;
		}
	}
	/**
	 * Returns true iff this tree (or subtree) is non null and has a
	 * non-null right branch.
	 * @return
	 */
	public boolean hasRight() {
		if (this.root != null && this.root.right != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Throws an unspecified exception if this tree is empty; otherwise
	 * returns the right subtree attached to this tree. Note, the right
	 * subtree may be null.
	 * @return
	 */
	public BinaryTree< T > getRight() {
		if (this.isEmpty()) {
			throw new RuntimeException();
		}
		if (this.root.right == null) {
			return null;
		} else {
			BinaryTree<T> rightSub = new BinaryTree<T>(this.root.right);
			return rightSub;
		}
	}
	/**
	 * Replaces value of right child with <code>value</code>/
	 * @param value
	 */
	public void setRightValue( T value ) {
		if (this.root.right == null) {
			this.root.right = new BTNode(value);
		} else {
			this.root.right.value = value;
		}
	}
	/**
	 * Returns true iff this tree (or subtree) is non-empty
	 * and has neither a left nor a right branch.
	 * @return
	 */
	public boolean isLeaf() {
		if (this.root != null && (this.root.left == null && this.root.right == null)) {
			return true;
		} else {
			return false;
		}
	}
	/** 
	 * Returns the size of any binary tree, which is the number of nodes it contains.
	 * @return
	 */
	public int getSize() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return getSizeCalc(this.root);
		}
	}
	/*recursive method, works with BTNode since that is an inherited property of
	  a binary tree instead of being just a Binary tree itself, which in itself
	  can't call this method without throwing null pointers
	*/
	private int getSizeCalc(BTNode root) {
		if (root == null) {
			return 0;
		} else {
			return (1 + getSizeCalc(root.left) + getSizeCalc(root.right));
		}
	}
	/**
	 * The "height" of any binary tree is the number of edges in the longest path
	 * from its root to its deepest child (leaf) node. (You may use Java's <code>Math</code>
	 * library for part of this function's computation if you wish.)
	 * 
	 * <h1>Very important</h1>
	 * Numerous "conventions" are available here. Some give the height of an empty tree to be 0, 
	 * others -1. We will specify that the height of an empty tree is -1.
	 * 
	 * @return a non-negative integer
	 */
	public int height() {
		int height = -1;
		if (this.hasLeft()) {
			height = Math.max(height, this.getLeft().height());
		} 
		if (this.hasRight()) {
			height = Math.max(height, this.getRight().height());
		} else {
			if (this.isEmpty()) {
				height = -1;
				return height;
			}
		}
		return height + 1;
		/*int leftHeight = 0;
		int rightHeight = 0;
		if (this.isEmpty()) {
			return -1;
		}else if (this.root.left != null) {
			leftHeight = this.getLeft().height(); //recurse through left's height
		} else if (this.root.right != null) {
			rightHeight = this.getRight().height(); //recurse through right's height
		} else {
			return -1;
		}
		if (leftHeight <= rightHeight) {
			return rightHeight + 1; //base case 1
		} else {
			return leftHeight + 1; //base case 2
		}*/
	}
	
	// Find the node that's the maximum
	protected BinaryTree<T> findMax() {
		if (this.root.right == null) {//if there's nothing to the right
			return this;
		} else if (this.getRight().isEmpty()) { //if the right is there but there's nothing in it
			return this;
		} else {
			return this.getLeft().findMax();
		}
	}
	// Find the node that's the minimum
	protected BinaryTree<T> findMin() {
		if (this.root.left == null) { //if there's nothing to the left
			return this;
		} else if (this.getLeft().isEmpty()) { //if the left is there but there's nothing in it
			return this;
		} else {
			return this.getLeft().findMin();
		}
	}
	
	public static <T> boolean sameShape(BinaryTree<T> tree1, BinaryTree<T>   tree2) {
		if (tree1 == null && tree2 == null) { //the trees are both empty hence same shape, they basically followed form till the end
			return true;
		} else if (tree1 != null && tree2 != null) { //both trees follow the same shape so far
			//so recurse through the tree
			return sameShape(tree1.getLeft(), tree2.getLeft()) && sameShape(tree1.getRight(), tree2.getRight());
		} else { //the shape doesn't follow so fail
			return false;
		}
	}
	
	public static<T> int internalNCount(BinaryTree<T> tree)  {
		return helper(true, tree);
	}
	
	private static <T> int helper(boolean isRoot, BinaryTree<T> tree2) {
		if (tree2 == null) { //base case for catching nulls //again for exams it would be isEmpty(tree2)
			//like right there VVV in that if statement
			return 0;
		} else if (tree2.isEmpty() || tree2.isLeaf()) { //base case
			return 0;
		} else if (isRoot == true) { //you're currently the root node
			return helper(false, tree2.getLeft()) + helper(false, tree2.getRight()); //so start the recursion
		} else {
			return 1 + helper(false, tree2.getLeft()) + helper(false, tree2.getRight()); //since youre an IN Node add one
		}
	}
	
	public static<T> int leafCount(BinaryTree<T> tree) {
		if (tree == null) { //on the exam it would be isEmpty(tree) which would check if its null for you
			return 0; //tree is empty
		} else if (tree.isLeaf()) {
			return 1;
		} else {
			return leafCount(tree.getLeft()) + leafCount(tree.getRight());
		}
	}
}

