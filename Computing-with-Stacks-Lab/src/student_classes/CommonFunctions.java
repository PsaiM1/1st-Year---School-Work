package student_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * You must implement the following methods using Java's Stack object to 
 * replace the iteration/recursion in the original functions with a series
 * of stack operations, including push, pop, peek and isEmpty. Use no other
 * special library functions or classes; in other words, your code should use
 * standard arithmetic operators and in the case of the reverse function, only
 * push and pop and the default constructor for whichever Java collection
 * class you choose to represent lists.
 */

public class CommonFunctions {
	/*
	 * computes the standard factorial function --assumes non-negative integers.
	 */
	public static int factorial( int n ) {
		Stack<Integer> factStack = new Stack<Integer>();
		int ans = 1;
		while (n >= 1) {
			if (n == 1) {
				factStack.push(1);
				break;
			} else {
				factStack.push(n); 
				n--;
			}
		}
		while (! factStack.isEmpty()) {
			ans = ans * factStack.pop();
		}
		return ans;
	}
	/*
	 * computes the standard fibonacci series. Note fibonacci(0) = 0, in this
	 * version. Assumes non-negative integers.
	 */
	public static int fibonacci( int n ) {
		Stack<Integer> fiboStack = new Stack<Integer>();
		int c = 0;
		
		while (c <= n) {
			if (c == 1) {
				fiboStack.push(1);
			} else if (c == 0) {
				fiboStack.push(0);
			} else {
				int tempOne = fiboStack.pop();
				int tempTwo = fiboStack.peek();
				int result = tempOne + tempTwo;
	
				fiboStack.push(tempOne);
				fiboStack.push(result);
			}
			c++;
		}
		return fiboStack.peek();
	}
	/*
	 * Intended to reverse a list of any Object type. You choose which list
	 * implementation you'd like to use.
	 */
	public static <T> List<T> reverse( List<T> list ) {
		Stack<T> listStack = new Stack<T>();
		for (int i = 0; i < list.size(); i++) {
			listStack.push(list.get(i));
		}
		List<T> reverseList = new ArrayList<T>();
		while (! listStack.isEmpty()) {
			reverseList.add(listStack.pop());
		}
		return reverseList;
	}
	/*
	 * This is the question from quiz 1. For those who don't recall what that was:
	 * 
	 * f(n) = 1 when n=0, and f(n-1) + (n+1) otherwise.
	 */
	public static int quizQuestion( int n ) {
		Stack<Integer> quizStack = new Stack<Integer>();
		int ans = 1; // smallest possible solution
		int c = 0;
		while (c <= n) {
			if (n == 0) {
				quizStack.push(1);
				break;
			} else if (c != n){
				quizStack.push(ans += (c + 2));
				c++;
			} else {
				break;
			}
		}
		ans = quizStack.peek();
		return ans;
	}
}
