package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import student_classes.Heap;

public class StudentTests {
	private Collection<Comparable<Integer>> testList;
// these are merely suggestions ....

	@Test
	public void testHeap() {
		testList = new ArrayList<Comparable<Integer>>();
		testList.add(4);
		testList.add(3);
		testList.add(1);
		testList.add(9);
		testList.add(7);
		Heap<Integer> testHeap = new Heap<Integer>(testList);
	}

	@Test
	public void testSort() {
		testList = new ArrayList<Comparable<Integer>>();
		testList.add(4);
		testList.add(3);
		testList.add(1);
		testList.add(9);
		testList.add(7);
		Heap<Integer> testHeap = new Heap<Integer>(testList);
		testHeap.sort();
		List<Integer> testHeapList = testHeap.asList();
		assertTrue(testHeapList.get(0) == 1);
		assertTrue(testHeapList.get(1) == 3);
		assertTrue(testHeapList.get(2) == 4);
		assertTrue(testHeapList.get(3) == 7);
		assertTrue(testHeapList.get(4) == 9);
	}

	@Test
	public void testSize() {
		testList = new ArrayList<Comparable<Integer>>();
		testList.add(4);
		testList.add(3);
		testList.add(1);
		testList.add(9);
		testList.add(7);
		Heap<Integer> testHeap = new Heap<Integer>(testList);
		assertTrue(testHeap.size() == 5);
	}
	
	@Test
	public void testAsList() { 
		testList = new ArrayList<Comparable<Integer>>();
		testList.add(4);
		testList.add(3);
		testList.add(1);
		testList.add(9);
		testList.add(7);
		Heap<Integer> testHeap = new Heap<Integer>(testList);
		List<Integer> testHeapList = testHeap.asList();
		assertTrue(testHeapList.get(0) == 9);
		assertTrue(testHeapList.get(1) == 7);
		assertTrue(testHeapList.get(2) == 1);
		assertTrue(testHeapList.get(3) == 3);
		assertTrue(testHeapList.get(4) == 4);
	}

}
