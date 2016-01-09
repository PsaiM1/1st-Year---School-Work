package student_classes;
/**
 * Please reference the documentation for this lab before beginning.
 * @author UMD CS Department.
 *
 */
public class Recurrences {
	/**
	 * Returns <code>true</code> iff <code>n</code> is a prime integer, which
	 * in this case means a positive integer > 1 that is divisible only by itself
	 * and 1.
	 * @param n (a non-negative integer)
	 * @return
	 */
	public static boolean isPrime( int n ) {
		return isPrime_aux(n, n - 1);
	}
	
	private static boolean isPrime_aux(int n, int i) {
		if (i == 1) {
			return true;
		} else {
			if (n < 2) {
				return false;
			}
			if (n % i == 0) {
				return false;
			} else {
				return Recurrences.isPrime_aux(n, i - 1);
			}
		}
	}
	/**
	 * Recursively implements the classic Fibonacci function. Note,
	 * this implementation requires the fibonacci ( 0 ) = 0.
	 * @param n (a non-negative integer)
	 * @return
	 */
	public static int fibonacci( int n ) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return (Recurrences.fibonacci(n - 1) + Recurrences.fibonacci(n - 2));
		}
	}
	/**
	 * Implements the basic laws of positive exponents over the integers.
	 * Note: anything to the 0-power is 1.
	 * @param base (a non-negative integer)
	 * @param exponent (a non-negative integer)
	 * @return
	 */
	public static int power( int base, int exponent ) {
		if (exponent == 0) {
			return 1;
		} else {
			return (base * Recurrences.power(base, exponent - 1));
		}
	}
	/**
	 * pmod stands for pseudo-mod. The real modulus function
	 * needs to deal with negative integers, but this one assumes
	 * positive integers only. The modulus operator, from our
	 * perspective, looks just like the % (remainder) operator
	 * in Java---except that you're implementing it recursively,
	 * and you will use it to implement the gcd function, which
	 * is defined immediately after this one.
	 * 
	 */
	public static int pmod( int a, int modulus ) {
		if (a < modulus) {
			return a;
		} else {
			return (pmod(a - modulus, modulus));
		}
	}
	/**
	 * uses the Euclidean algorithm to recursively compute the greatest
	 * common divisor of two positive integers, m and n. Note, in order
	 * to receive credit for this function, your implementation 
	 * <em>must</em> use the <code>pmod</code> function implemented 
	 * directly above. (Rather than give you this, you'll need to look
	 * it up!)
	 * @param m
	 * @param n
	 * @return
	 */
	public static int gcd( int m, int n ) {
		if (n == 0) {
			return m;
		} else {
			return Recurrences.gcd(n, Recurrences.pmod(m, n));
		}
	}
	/**
	 * Given a positive integer, generate an array of ascending positive integers
	 * starting at 1 .. through-position (inclusive).
	 * For example:
	 * generateOdds( 1 ) => [ 1 ]
	 * generateOdds( 2 ) => [ 1, 3 ]
	 * generateOdds( 3 ) => [ 1, 3, 5 ]
	 * etc.
	 * @param through_position
	 * @return
	 */
	private static int[] oddArray;
	public static int[] generateOdds( int through_position ) {
		oddArray = new int[through_position];
		return Recurrences.generateOdds_aux(through_position - 1);
	}
	
	private static int[] generateOdds_aux(int index) {
		if (index == 0) {
			oddArray[index] = 1;
			return oddArray;
		} else {
			oddArray[index] = ((index) + (index + 1));
			return Recurrences.generateOdds_aux(index - 1);
		}
	}
	
	/**
	 * Uses "backtracking" (through recursion) to 
	 * determine if the "target" (an integer) can be computed as
	 * the result of summing any combination of
	 * integers in the <code>arrayOfIntegers</code>.
	 * Note: each integer in the array of integers 
	 * may be used only once, however.
	 * <P>
	 * For example: summable( 10, {1,2,3,4,5}) is true,
	 * but summable( 20, {1,2,3,4,5}) is not.
	 * </P>
	 * <P>Do not assume that the integers in the array of integers
	 * are ordered, or even positive for that matter.
	 * </P>
	 * <P>By way of a hint: you will most certainly want to define
	 * an "auxiliary" method (private static) that manages the array indexing for you.
	 * @param target
	 * @param arrayOfIntegers
	 * @return
	 */
	public static boolean summable( int target, int[] arrayOfIntegers ) {
		return Recurrences.sum_aux(target, arrayOfIntegers, 0);
	}
	
	public static int sum = 0;
	private static boolean sum_aux(int target, int[] arrayInts, int index) {
		
		if (target == 0) {
			return true;
		} else if (target < 0 || index == arrayInts.length) {
			return false;
		} else if (Recurrences.sum_aux(target - arrayInts[index], arrayInts, index + 1)) {
			return true;
		} else if (Recurrences.sum_aux(target, arrayInts, index + 1)) {
			return true;
		} else {
			return false;
		}
	}

}
