package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.Bag;
import student_classes.DenseSearchTree;

public class StudentTests {
	// a place to design your own tests.
	@Test
	public void testBag() { 
		Bag<Integer> test = new Bag<Integer>();
		//test adding unique
		test.add(1);
		test.add(2);
		test.add(3);
		//test contains
		assertTrue(test.contains(1));
		assertTrue(test.contains(2));
		assertTrue(test.contains(3));
		assertFalse(test.contains(5));
		//test size
		assertTrue(test.size() == 3);
		//test adding multiples
		test.add(3);
		test.add(3);
		assertTrue(test.contains(3));
		//test count
		assertTrue(test.count(3) == 3);
		//test size again
		assertTrue(test.size() == 5);
		//test remove
		test.remove(1);
		assertFalse(test.contains(1));
		assertTrue(test.size() == 4);
	}
	
	@Test
	public void testBagContains() {
		Bag<Integer> test = new Bag<Integer>();
		//test adding unique
		test.add(1);
		test.add(2);
		test.add(3);
		//test contains
		assertTrue(test.contains(1));
		assertTrue(test.contains(2));
		assertTrue(test.contains(3));
		assertFalse(test.contains(5));
		test.add(1);
		assertTrue(test.count(1) == 2);
		assertTrue(test.contains(1));
		assertTrue(test.size() == 4);
		test.add(5);
		test.add(6);
		test.add(7);
		assertTrue(test.contains(5));
		assertTrue(test.contains(3));
		assertTrue(test.size() == 7);
		test.remove(1);
		assertTrue(test.contains(1));
		assertTrue(test.count(1) == 1);
		assertTrue(test.size() == 6);
		test.remove(1);
		assertFalse(test.contains(1));
		assertTrue(test.count(1) == 0);
		assertTrue(test.size() == 5);
		
		try {
			test.remove(1);
		} catch (IllegalStateException e) {
			assertEquals(e.getMessage(), null);
		}
	}
	
	@Test
	public void testBagIterator() {
		Bag<Integer> test = new Bag<Integer>();
		//test adding unique
		test.add(1);
		test.add(2);
		test.add(3);
		for (Integer item : test) {
			assertTrue(test.contains(item));
		}
		
		test.add(3);
		test.add(3);
		for (Integer item : test) {
			assertTrue(test.contains(item));
		}
		
		for (Integer item : test.asSet()) {
			assertTrue(test.contains(item));
		}
		
		
	}
	
	@Test
	public void testDenseTree() {
		DenseSearchTree<Integer> test = new DenseSearchTree<Integer>();
		test.add(5);
		test.add(4);
		test.add(6);
		test.add(7);
		test.add(3);
		//check contains
		assertTrue(test.contains(5));
		assertTrue(test.contains(3));
		assertTrue(test.contains(7));
		assertTrue(test.contains(6));
		assertTrue(test.contains(4));
		//check count
		assertTrue(test.count(5) == 1);
		assertTrue(test.count(3) == 1);
		assertTrue(test.count(7) == 1);
		//check size
		//assertTrue(test.size() == 5);
		//check if min/max works
		assertTrue(test.getMax() == 7);
		assertTrue(test.getMin() == 3);
		//check if multiplicty adding works
		test.add(5);
		test.add(4);
		test.add(6);
		//check count
		assertTrue(test.count(5) == 2);
		assertTrue(test.count(4) == 2);
		assertTrue(test.count(6) == 2);
		//check size
		assertTrue(test.size() == 8);
		//check remove
		test.remove(5);
		assertTrue(test.size() == 7);
		assertTrue(test.count(5) == 1);
		test.remove(5);
		assertTrue(test.size() == 6);
		assertTrue(test.count(5) == 0);
	}
	
	@Test
	public void testDSTIterator() {
		DenseSearchTree<Integer> test = new DenseSearchTree<Integer>();
		test.add(5);
		test.add(4);
		test.add(6);
		test.add(7);
		test.add(3);
		test.add(5);
		test.add(4);
		test.add(6);
		assertTrue(test.count(5) == 2);
		assertTrue(test.count(4) == 2);
		assertTrue(test.count(6) == 2);
		assertTrue(test.size() == 8);
		
		for (Integer item : test) {
			assertTrue(test.contains(item));
		}
		
		assertEquals(test.asSet().toString(), "[3, 4, 5, 6, 7]");
	}
	
	@Test
	public void testDSTRemove() {
		DenseSearchTree<Integer> test = new DenseSearchTree<Integer>();
		test.add(5);
		test.add(4);
		test.add(6);
		test.add(7);
		test.add(3);
		test.add(5);
		test.add(4);
		test.add(6);
		assertTrue(test.count(5) == 2);
		assertTrue(test.count(4) == 2);
		assertTrue(test.count(6) == 2);
		assertTrue(test.size() == 8);
		
		assertTrue(test.count(7) == 1);
		test.remove(7); //remove leaf type node
		assertTrue(test.count(7) == 0);
		assertFalse(test.contains(7));
		assertTrue(test.count(6) == 2);
		test.remove(6);
		assertTrue(test.count(6) == 1);
		test.remove(6); // remove internal node
		assertTrue(test.count(6) == 0);
		assertFalse(test.contains(6));
		test.add(6);
		test.add(7);
		
		assertTrue(test.count(5) == 2);
		test.remove(5);
		assertTrue(test.count(5) == 1);
		test.remove(5);
		assertTrue(test.count(5) == 0);
		assertFalse(test.contains(5));
		test.remove(1);
		
		
		
	}

}
