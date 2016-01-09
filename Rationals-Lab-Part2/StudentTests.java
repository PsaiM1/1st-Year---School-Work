import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {
	
	/* 
	 * implement any or all of the following tests.
	 * If you do not wish to implement a particular test then
	 * you may either remove its declaration and method implementation
	 * from this file, or just remove the body of the method that you
	 * will to exclude from the tests ... in this way the method will
	 * not "fail" upon the execution of the remaining tests in this
	 * file.
	 */

	@Test
	public void testRationalIntInt() {
		Rational r = new Rational(7, 5);
		assertEquals(7, r.getNumer());
		assertEquals(5, r.getDenom());
	}

	@Test
	public void testRationalRational() {
		Rational r = new Rational(7, 5);
		assertEquals(7, r.getNumer());
		assertEquals(5, r.getDenom());
	}

	@Test
	public void testGetNumer() {
		Rational r = new Rational(7, 5);
		assertTrue(r.getNumer() == 7);
	}

	@Test
	public void testGetDenom() {
		Rational r = new Rational(7, 5);
		assertTrue(r.getDenom() == 5);
	}

	@Test
	public void testToString() {
		Rational r = new Rational(7, 5);
		assertTrue(r.toString().equals("7/5"));
	}

	@Test
	public void testReciprocal() {
		Rational r = new Rational(7, 5);
		Rational x = r.reciprocal();
		assertEquals(5, x.getNumer());
		assertEquals(7, x.getDenom());
	}

	@Test
	public void testMultiply() {
		Rational a = new Rational(7, 5);
		Rational b = new Rational(7, 11);
		Rational r = Rational.multiply(a, b);
		assertEquals(49, r.getNumer());
		assertEquals(55, r.getDenom());
	}

	@Test
	public void testDivide() {
		Rational a = new Rational(7, 5);
		Rational b = new Rational(7, 11);
		Rational r = b.divide(a);
		Rational x = r.reduce();
		assertEquals(5, x.getNumer());
		assertEquals(11, x.getDenom());
	}

	@Test
	public void testAdd() {
		Rational a = new Rational(7, 5);
		Rational b = new Rational(7, 11);
		Rational r = b.add(a);
		assertEquals(112, r.getNumer());
		assertEquals(55, r.getDenom());
	}

	@Test
	public void testReduce() {
		Rational r = new Rational(54, 24);
		Rational x = r.reduce();
		assertEquals(9, x.getNumer());
		assertEquals(4, x.getDenom());
	}

}
