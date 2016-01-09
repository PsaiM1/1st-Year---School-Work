package tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

import org.junit.Test;

public class StudentTests {

	@Test
	public void testBasicLinkedList() {
		BasicLinkedList<String> testList = new BasicLinkedList<String>();
		//tests the adding and getting
		testList.addToEnd("Red").addToFront("Yellow").addToFront("Blue");
		assertEquals("Blue", testList.getFirst());
		assertEquals("Red", testList.getLast());
		assertEquals(3, testList.getSize());
		
		//test the retrieval
		assertEquals("Blue", testList.retrieveFirstElement());
		assertEquals("Red", testList.retrieveLastElement());
		
		//tests to removal
		for (String test : testList) {
			assertEquals("Yellow", test);
		}
		
		BasicLinkedList<String> testList2 = new BasicLinkedList<String>();
		testList2.addToEnd("Red").addToFront("Yellow").addToFront("Blue").addToEnd("Purple");
		assertEquals("Purple", testList2.getLast());
	}
	
	@Test
	public void testSortedLinkedList() {
		SortedLinkedList<String> testList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		//test adding
		testList.add("Yellow").add("Red");
		//test Iteration
		String test = "Iteration (for sorted list): ";
		for (String entry : testList) {
			String temp = entry + " ";
			test = test.concat(temp);
		}
		assertEquals(test, "Iteration (for sorted list): Red Yellow ");
		testList.remove("Red");
		String testTwo = "After remove in sorted list first is: ";
		String tempTwo = testList.getFirst();
		testTwo = testTwo.concat(tempTwo);
		assertEquals(testTwo, "After remove in sorted list first is: Yellow");
	}
	
	@Test
	public void testBasicLinkedListRemoveandSize() {
		BasicLinkedList<Integer> testList = new BasicLinkedList<Integer>();
		testList.addToEnd(500).addToEnd(3).addToEnd(500);
		assertTrue(testList.getFirst().equals(500));
		assertTrue(testList.getLast().equals(500));
		testList.remove(500, new Comparator<Integer>() {
			@Override
			public int compare(Integer obj1, Integer obj2) {
				return obj1.compareTo(obj2);
			}
		});
		assertTrue(testList.getFirst().equals(3));
		assertTrue(testList.getSize() == 1);
		testList.remove(3, new Comparator<Integer>() {
			@Override
			public int compare(Integer obj1, Integer obj2) {
				return obj1.compareTo(obj2);
			}
		});
		assertTrue(testList.getSize() == 0);
	}

}
