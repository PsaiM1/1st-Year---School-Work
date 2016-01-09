package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.DynArray;


/** 
 * Reserved for you to implement your own tests. Note: you may
 * need to add JUnit4 to your build path to use these. Ask 
 * your TA for help, or let Eclipse lead you through
 * the process.
 * 
 * @author CMSC Student
 *
 */
public class StudentTests {
	// uses these as you wish: they are "samples" of what kinds
	// of things are good test candidates.
	@Test
	public void testDynArrayBoolean() { //ctor
		DynArray<Integer> test = new DynArray<Integer>(true);
	}

	@Test
	public void testDynArray() { //ctor
		DynArray<Integer> test = new DynArray<Integer>();
	}

	@Test
	public void testDynArrayIntBoolean() {
		DynArray<Integer> test = new DynArray<Integer>(10, true);
	}
	
	@Test
	public void testDynArrayCopyConstruct() {
		Integer testVar = 1;
		Integer testVarTwo = 2;
		Integer testVarThree = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVarThree);
		test.add(testVarTwo);
		test.add(testVarThree);
		DynArray<Integer> testCopy = new DynArray<Integer>(test);
		assertTrue(test.equals(testCopy));
	}

	@Test
	public void testAdd() {
		Integer testVar = 1;
		Integer testVarTwo = 2;
		Integer testVarThree = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVarThree);
		test.add(testVarTwo);
		test.add(testVarThree);
		assertEquals(test.get(0), testVar);
		assertEquals(test.get(1), testVarTwo);
		assertEquals(test.get(2), testVarThree);
		assertEquals(test.get(3), testVarTwo);
		assertEquals(test.get(4), testVarThree);
		assertTrue(test.size() == 5);
		test.add(testVar);
		test.add(testVarTwo);
		assertTrue(test.size() == 7);
		try {
			test.add(null);
			fail("you need a null pointer exception");
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), "no nulls");
		}
		test.add(testVar);
		test.add(testVarTwo);
		assertTrue(test.size() == 9);
	}

	@Test
	public void testRemove() {
		Integer testVar = 1;
		Integer testVarTwo = 2;
		Integer testVarThree = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVarThree);
		test.add(testVarTwo);
		test.add(testVarThree);
		assertEquals(test.get(0), testVar);
		assertEquals(test.get(1), testVarTwo);
		assertEquals(test.get(2), testVarThree);
		assertEquals(test.get(3), testVarTwo);
		assertEquals(test.get(4), testVarThree);
		assertTrue(test.size() == 5);
		try {
			test.add(null);
			fail("you need a null pointer exception");
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), "no nulls");
		}
		
		assertEquals(test.remove(2), testVarThree);
		assertTrue(test.size() == 4);
		assertEquals(test.remove(3), testVarThree);
		assertTrue(test.size() == 3);
		assertEquals(test.remove(2), testVarTwo);
		assertTrue(test.size() == 2);
		try {
			test.remove(5);
			fail("there should be an array index out of bounds exception");
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "5");
		}

	}

	@Test
	public void testGet() {
		Integer testVar = 3;
		Integer testVarTwo = 4;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		assertTrue(test.size() == 1);
		test.add(testVarTwo);
		assertTrue(test.size() == 2);
		assertEquals(test.get(0), testVar);
		assertEquals(test.get(1), testVarTwo);
		try {
			test.get(10);
			fail("there should be an array index out of bounds exception");
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVar);
		assertEquals(test.get(6), null);
	}

	@Test
	public void testSet() {
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		Integer testVar = 3;
		Integer testVarTwo = 4;
		test.set(0, testVar);
		assertEquals(test.get(0), testVar);
		test.set(1, testVarTwo);
		assertEquals(test.get(1), testVarTwo);
		
		DynArray<Integer> testNoNull = new DynArray<Integer>(false);
		testNoNull.set(0, testVar);
		assertEquals(testNoNull.get(0), testVar);
		assertTrue(testNoNull.size() == 1);
		testNoNull.set(0, testVarTwo);
		assertEquals(testNoNull.get(0), testVarTwo);
		assertTrue(testNoNull.size() == 1);
		try {
			testNoNull.set(0, null);
			fail("there should be a illegal state exception");
		} catch (IllegalStateException e) {
			assertEquals(e.getMessage(), "no nulls");
		}
		
		try {
			testNoNull.set(20, testVar);
			fail("there should be an array index out of bounds exception");
		} catch (ArrayIndexOutOfBoundsException d) {
			assertEquals(d.getMessage(), null);
		}
	}

	@Test
	public void testSize() {
		Integer testVar = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		assertTrue(test.size() == 1);
		test.add(testVar);
		assertEquals(test.remove(1), testVar);
		assertTrue(test.size() == 1);
	}
	
	@Test
	public void testEquals() {
		Integer testVar = 1;
		Integer testVarTwo = 2;
		Integer testVarThree = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVarThree);
		test.add(testVarTwo);
		test.add(testVarThree);
		assertEquals(test.get(0), testVar);
		assertEquals(test.get(1), testVarTwo);
		assertEquals(test.get(2), testVarThree);
		assertEquals(test.get(3), testVarTwo);
		assertEquals(test.get(4), testVarThree);
		DynArray<Integer> testTwo = new DynArray<Integer>(0, false);
		testTwo.add(testVar);
		testTwo.add(testVarTwo);
		testTwo.add(testVarThree);
		testTwo.add(testVarTwo);
		testTwo.add(testVarThree);
		assertEquals(testTwo.get(0), testVar);
		assertEquals(testTwo.get(1), testVarTwo);
		assertEquals(testTwo.get(2), testVarThree);
		assertEquals(testTwo.get(3), testVarTwo);
		assertEquals(testTwo.get(4), testVarThree);
		assertTrue(test.equals(testTwo));
	}
	
	@Test
	public void testIterator() {
		Integer testVar = 1;
		Integer testVarTwo = 2;
		Integer testVarThree = 3;
		DynArray<Integer> test = new DynArray<Integer>(0, false);
		test.add(testVar);
		test.add(testVarTwo);
		test.add(testVarThree);
		test.add(testVarTwo);
		test.add(testVarThree);
		int index = 0;
		while(test.iterator().hasNext() && index < test.size()) {
			test.set(index, testVar);
			index++;
		}
		assertEquals(test.get(0), testVar);
		assertEquals(test.get(1), testVar);
		assertEquals(test.get(2), testVar);
		assertEquals(test.get(3), testVar);
		assertEquals(test.get(4), testVar);
		
	}

}
