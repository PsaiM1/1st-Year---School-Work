package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.FunctionalList;
/* You may wish to implement any or none of these. Or, perhaps
 * you wish to design your own?
 */
public class StudentTests {

	/*
	public void testDeepCopyConstructor() {
		
	}*/
	
	@Test
	public void testFunctionalList() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
	}

	@Test
	public void testAdd() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		assertEquals(testList.head.getData(), 3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		assertEquals(testListTwo.head.getData(), 500);
	}
	
	@Test
	public void testAppend() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
	}

	@Test
	public void testRemove() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		testList.remove(3);
		assertEquals(testList.head.getData(), 500);
		testListTwo.remove(500);
		assertEquals(testListTwo.head.getData(), 3);
		//need to check to see if it can create an empty list by removing all the
		// elements
		testListTwo.remove(3);
		assertEquals(testListTwo.head, null);
	}

	@Test
	public void testReverse() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		assertEquals(testList.toString(), "[3, 500]");
		testList.reverse();
		assertEquals(testList.toString(), "[500, 3]");
	}

	@Test
	public void testSize() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		assertTrue(testList.size() == 2);
		assertTrue(testListTwo.size() == 3);
		
		testList.remove(3);
		assertEquals(testList.head.getData(), 500);
		assertTrue(testList.size() == 1);
		
		testListTwo.remove(500);
		assertEquals(testListTwo.head.getData(), 3);
		assertTrue(testListTwo.size() == 1);
	}

	@Test
	public void testPositionOf() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		assertTrue(testList.size() == 2);
		assertTrue(testListTwo.size() == 3);
		assertTrue(testList.positionOf(3) == 0);
		assertTrue(testListTwo.positionOf(3) == 1);
		
	}

	@Test
	public void testNth() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		assertTrue(testListTwo.nth(2).equals(500));
		assertTrue(testListTwo.nth(1).equals(3));
		try {
			testListTwo.nth(500);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertTrue(e.getMessage().equals("out of bounds"));
		}
	}

	@Test
	public void testToString() {
		FunctionalList<Integer> testList = new FunctionalList<Integer>();
		testList.add(3);
		FunctionalList<Integer> testListTwo = new FunctionalList<Integer>();
		testListTwo.add(500);
		testList.append(testListTwo);
		testListTwo.append(testList);
		assertEquals(testList.head.getData(), 3);
		assertEquals(testListTwo.head.getData(), 500);
		assertEquals(testList.toString(), "[3, 500]");
	}

}
