package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.Recurrences;

public class StudentTests {
	
	@Test
	public void testIsPrime() {
		assertTrue(Recurrences.isPrime(5));
		assertTrue(Recurrences.isPrime(7));
		assertTrue(Recurrences.isPrime(11));
		assertTrue(Recurrences.isPrime(13));
		assertFalse(Recurrences.isPrime(1));
	}
	
	@Test
	public void testFibonacci() {
		assertEquals(Recurrences.fibonacci(0), 0);
		assertEquals(Recurrences.fibonacci(1), 1);
		assertEquals(Recurrences.fibonacci(3), 2);
		assertEquals(Recurrences.fibonacci(4), 3);
		assertEquals(Recurrences.fibonacci(5), 5);
	}
	
	@Test
	public void testPower() {
		assertEquals(Recurrences.power(2, 2), 4);
		assertEquals(Recurrences.power(2, 3), 8);
		assertEquals(Recurrences.power(2, 4), 16);
		assertEquals(Recurrences.power(3, 2), 9);
		assertEquals(Recurrences.power(3, 3), 27);
		assertEquals(Recurrences.power(12, 5), 248832);
	}
	
	@Test
	public void testPMOD() {
		assertEquals(Recurrences.pmod(3, 2), 1);
		assertEquals(Recurrences.pmod(5, 2), 1);
		assertEquals(Recurrences.pmod(17, 13), 4);
		assertEquals(Recurrences.pmod(25, 5), 0);
	}

	@Test
	public void testGCD() {
		assertEquals(Recurrences.gcd(6, 15), 3);
		assertEquals(Recurrences.gcd(45, 50), 5);
		assertEquals(Recurrences.gcd(110, 120), 10);
	}
	
	@Test
	public void generateOdds() {
		int[] testArr = {1, 3, 5};
		int[] testArrTwo = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
		// for loops used to check individual elements and to avoid the not overridden equals method
		for (int i = 0; i < testArr.length; i++) {
			assertEquals(Recurrences.generateOdds(3)[i], testArr[i]);
		}
		for (int j = 0; j < testArrTwo.length; j++) {
			assertEquals(Recurrences.generateOdds(10)[j], testArrTwo[j]);
		}
		
		
	}
	
	@Test
	public void testSummable() {
		int[] testArr = {2, 1, 5, 4, 3};
		assertTrue(Recurrences.summable(10, testArr));
		int[] testArrTwo = {3, 1, 4, 6, 7};
		assertTrue(Recurrences.summable(13, testArrTwo));
		assertTrue(Recurrences.summable(14, testArrTwo));
		assertTrue(Recurrences.summable(17, testArrTwo));
	}

}
