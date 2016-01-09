
import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {

	// your tests go here
	@Test
	public void testConstruct() {
		MyStack stack = new MyStack(5);
	}
	
	@Test
	public void TestEmpty() {
		MyStack stackOne = new MyStack(5);
		assertTrue(stackOne.empty());
	}
	
	@Test
	public void TestPushandPeek() {
		MyStack testStack = new MyStack(5);
		int x = 1;
		int y = 2;
		int a = 3;
		int b = 4;
		int c = 5;
		int d = 6;
		testStack.push(x);
		assertFalse(testStack.empty());
		assertEquals(x, testStack.peek());
		testStack.push(y);
		assertEquals(y, testStack.peek());
		testStack.push(a);
		assertEquals(a, testStack.peek());
		testStack.push(b);
		assertEquals(b, testStack.peek());
		testStack.push(c);
		assertEquals(c, testStack.peek());
	}
	
	@Test
	public void TestPop() {
		MyStack testStack = new MyStack(5);
		int x = 5;
		testStack.push(x);
		assertFalse(testStack.empty());
		assertEquals(x, testStack.peek());
		assertEquals(x, testStack.pop());
		assertTrue(testStack.empty());
	}
	
	@Test
	public void TestSearch() {
		MyStack testStack = new MyStack(5);
		int x = 5;
		int y = 3;
		int z = 0;
		testStack.push(x);
		testStack.push(y);
		assertFalse(testStack.empty());
		assertEquals(0, testStack.search(x));
		assertEquals(1,testStack.search(y));
		assertEquals(-1, testStack.search(z));
		
		MyStack testStackTwo = new MyStack(5);
		int a = 5;
		int b = 3;
		testStackTwo.push(a);
		testStackTwo.push(b);
		assertFalse(testStackTwo.empty());
		assertEquals(0, testStackTwo.search(a));
		assertEquals(1, testStackTwo.search(b));
	}

}
