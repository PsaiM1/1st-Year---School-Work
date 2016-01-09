package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import student_classes.CommonFunctions;

public class StudentTests {

	@Test
	public void testFactorial() {
		assertEquals(CommonFunctions.factorial(1), 1);
		assertEquals(CommonFunctions.factorial(2), 2);
		assertEquals(CommonFunctions.factorial(3), 6);
		assertEquals(CommonFunctions.factorial(4), 24);
		assertEquals(CommonFunctions.factorial(5), 120);
	}
	
	@Test
	public void testFibonacci() {
		assertEquals(CommonFunctions.fibonacci(0), 0);
		assertEquals(CommonFunctions.fibonacci(1), 1);
		assertEquals(CommonFunctions.fibonacci(3), 2);
		assertEquals(CommonFunctions.fibonacci(4), 3);
		assertEquals(CommonFunctions.fibonacci(5), 5);
	}
	
	@Test
	public void testReverse() {
		List<Integer> testList = new ArrayList<Integer>();
		List<Integer> testReverseList = new ArrayList<Integer>();
		for (int i = 0; i <= 10; i++) {
			testList.add(i);
		}
		for (int j = 10; j >= 0; j--) {
			testReverseList.add(j);
		}
		assertEquals(CommonFunctions.reverse(testList), testReverseList);
	}
	
	@Test
	public void testQuizQuestion() {
		assertEquals(CommonFunctions.quizQuestion(5), 21);
		assertEquals(CommonFunctions.quizQuestion(0), 1);
		assertEquals(CommonFunctions.quizQuestion(1), 3);
		assertEquals(CommonFunctions.quizQuestion(2), 6);
		assertEquals(CommonFunctions.quizQuestion(3), 10);
	}

}
