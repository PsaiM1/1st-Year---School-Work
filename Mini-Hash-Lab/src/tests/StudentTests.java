package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import student_classes.HashTbl;

public class StudentTests {

	@Test
	public void testHashTbl() {
		HashTbl<Integer, String> test = new HashTbl<Integer, String>();
		test.put(1, "Well");
		assertEquals(test.get(1), "Well");
		test.put(3, "this");
		test.put(5, "is fun");
		assertEquals(test.get(3), "this");
		assertEquals(test.get(5), "is fun");
		
		HashTbl<String, String> testTwo = new HashTbl<String, String>();
		testTwo.put("One", "First one");
		testTwo.put("Two", "Second One");
		testTwo.put("9001", "Over 9000!");
		
		assertEquals(testTwo.get("One"), "First one");
		assertEquals(testTwo.get("Two"), "Second One");
		assertEquals(testTwo.get("9001"), "Over 9000!");
	}
	
	@Test
	public void testHashKeys() {
		HashTbl<Integer, String> test = new HashTbl<Integer, String>();
		test.put(1, "Well");
		assertEquals(test.get(1), "Well");
		test.put(3, "this");
		test.put(5, "is fun");
		assertEquals(test.get(3), "this");
		assertEquals(test.get(5), "is fun");
		test.put(5, "oops");
		assertEquals(test.get(5), "oops");
		
		try {
			test.put(null, "3");
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), null);
		}
		
	}

}
