package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.BST;
import student_classes.BinaryTree;

public class StudentTests {

	@Test
	public void testBST() {
		BST<Integer> testBSTree = new BST<Integer>();
		testBSTree.add(1);
		testBSTree.add(2);
		testBSTree.add(3);
		testBSTree.add(4);
		testBSTree.add(5);
		
		assertTrue(testBSTree.contains(1));
		assertTrue(testBSTree.contains(2));
		assertTrue(testBSTree.contains(3));
		assertTrue(testBSTree.contains(4));
		assertTrue(testBSTree.contains(5));
		assertFalse(testBSTree.contains(10));
		assertTrue(BST.isBST(testBSTree));
		
		testBSTree.remove(1);
		assertFalse(testBSTree.contains(1));
		assertTrue(BST.isBST(testBSTree));
		testBSTree.remove(2);
		assertFalse(testBSTree.contains(2));
		assertTrue(BST.isBST(testBSTree));
		testBSTree.remove(3);
		assertFalse(testBSTree.contains(3));
		assertTrue(BST.isBST(testBSTree));
		testBSTree.remove(4);
		assertFalse(testBSTree.contains(4));
		assertTrue(BST.isBST(testBSTree));
		testBSTree.remove(5);
		assertFalse(testBSTree.contains(5));
		assertTrue(BST.isBST(testBSTree));
		
		testBSTree.add(1);
		testBSTree.add(2);
		testBSTree.add(3);
		testBSTree.add(4);
		testBSTree.add(5);
		
		int itemTest = 1;
		for (Integer item : testBSTree) {
			assertTrue(item.equals(itemTest));
			itemTest++;
		}
		
		assertTrue(testBSTree.getSize() == 5);
		assertTrue(testBSTree.height() == 4);
		
		BST<Integer> testTwo = new BST<Integer>();
		assertTrue(testTwo.height() == -1); //all empty trees have height -1
		
		BST<Integer> testThree = new BST<Integer>();
		testThree.add(6);
		testThree.add(5);
		testThree.add(4);
		testThree.add(7);
		testThree.add(8);
		testThree.add(9);
		testThree.add(10);
		assertTrue(testThree.height() == 4);
		assertTrue(BST.isBST(testThree));
	}
	
	@Test
	public void isBSTTest() {
		BST<Integer> test = new BST<Integer>();
		test.add(6);
		test.add(5);
		test.add(4);
		test.add(7);
		test.add(8);
		test.add(9);
		test.add(10);
		assertTrue(test.height() == 4);
		assertTrue(BST.isBST(test));
	}
	
	@Test
	public void testsameShape() {
		BST<Integer> testBSTree = new BST<Integer>();
		testBSTree.add(1);
		testBSTree.add(2);
		testBSTree.add(3);
		testBSTree.add(4);
		testBSTree.add(5);
		
		BST<Integer> testBSTree2 = new BST<Integer>();
		testBSTree2.add(1);
		testBSTree2.add(2);
		testBSTree2.add(3);
		testBSTree2.add(4);
		testBSTree2.add(5);
		testBSTree2.add(6);
		
		BST<Integer> testBSTree3 = new BST<Integer>();
		testBSTree3.add(1);
		testBSTree3.add(2);
		testBSTree3.add(3);
		testBSTree3.add(4);
		testBSTree3.add(5);
		
		assertFalse(BinaryTree.sameShape(testBSTree, testBSTree2));
		assertTrue(BinaryTree.sameShape(testBSTree, testBSTree3));
	}
	
	@Test
	public void testInternalNodeCount() {
		BST<Integer> testBSTree = new BST<Integer>();
		testBSTree.add(1);
		testBSTree.add(2);
		testBSTree.add(3);
		testBSTree.add(4);
		testBSTree.add(5);
		
		assertTrue(BinaryTree.internalNCount(testBSTree) == 3);
	}
	
	@Test
	public void testCountLeaf() {
		BST<Integer> testBSTree = new BST<Integer>();
		testBSTree.add(1);
		testBSTree.add(2);
		testBSTree.add(3);
		testBSTree.add(4);
		testBSTree.add(5);
		
		assertTrue(BinaryTree.leafCount(testBSTree) == 1);
	}
	
}
