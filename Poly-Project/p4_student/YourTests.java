package p4_student;

import static org.junit.Assert.*;

import org.junit.Test;



public class YourTests {
	//Some example JUnit tests to get you started thinking about 
	//  writing JUnit tests...
	//There are signatures for JUnit tests you are required to write
	//  below the first few that we've provided.
	
	@Test
	public void testDefaultConstructor() {
		QuadraticEquation testQuad = new QuadraticEquation();
		assertTrue(testQuad.getA().isZero()
				&& testQuad.getB().isZero()
				&& testQuad.getC().isZero());
	}
	
	@Test
	public void testSingleValConstructor() {
		MyDouble cVal = new MyDouble(27.8);
				
		QuadraticEquation testQuad = new QuadraticEquation(cVal);
		assertTrue(testQuad.getA().isZero()
				&& testQuad.getB().isZero()
				&& testQuad.getC().equals(cVal));
	}

	@Test
	public void testThreeValConstructor() {
		MyDouble aVal = new MyDouble(15.7);
		MyDouble bVal = new MyDouble(-23.7);
		MyDouble cVal = new MyDouble(4.3);
				
		QuadraticEquation testQuad = new QuadraticEquation(aVal, bVal, cVal);
		assertTrue(testQuad.getA().equals(aVal)
				&& testQuad.getB().equals(bVal)
				&& testQuad.getC().equals(cVal));
	}

	@Test
	public void testEvaluate() {
		MyDouble aVal = new MyDouble(2);
		MyDouble bVal = new MyDouble(4);
		MyDouble cVal = new MyDouble(8);
		
		QuadraticEquation testQuad = new QuadraticEquation(aVal, bVal, cVal);
		assertTrue(testQuad.evaluate(MyDouble.zero).equals(new MyDouble(8)));
		assertTrue(testQuad.evaluate(new MyDouble(1)).equals(new MyDouble(14)));
		assertTrue(testQuad.evaluate(new MyDouble(2)).equals(new MyDouble(24)));
		assertTrue(testQuad.evaluate(new MyDouble(3)).equals(new MyDouble(38)));
	}

	
	
	
	//YOU NEED TO IMPLEMENT AT LEAST THESE JUNIT TESTS BELOW
	@Test
	public void testGetters() {
		MyDouble a = new MyDouble(3);
		MyDouble b = new MyDouble(4);
		MyDouble c = new MyDouble(5);
		QuadraticEquation testQuad = new QuadraticEquation(a, b, c);
		assertTrue(a.equals(testQuad.getA()));
		assertTrue(b.equals(testQuad.getB()));
		assertTrue(c.equals(testQuad.getC()));
	}

	@Test
	public void testAdd() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation EquatOne = new QuadraticEquation(a, b, c);
		QuadraticEquation EquatTwo = new QuadraticEquation(a, b, c);
		QuadraticEquation TestQuad = new QuadraticEquation(a.add(a), b.add(b), c.add(c));
		assertTrue(TestQuad.equals(EquatOne.add(EquatTwo)));
	}

	@Test
	public void testSubtract() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation EquatOne = new QuadraticEquation(a, b, c);
		QuadraticEquation EquatTwo = new QuadraticEquation(a, b, c);
		QuadraticEquation TestQuad = new QuadraticEquation(a.subtract(a), b.subtract(b), c.subtract(c));
		assertTrue(TestQuad.equals(EquatOne.subtract(EquatTwo)));
	}

	@Test
	public void testMultiply() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation TestOneQuad = new QuadraticEquation(a, b, c);
		QuadraticEquation TestTwoQuad = new QuadraticEquation(a, b, c);
		QuadraticEquation MultiQuadNull = TestOneQuad.limitedMultiply(TestTwoQuad);
		assertEquals(null, MultiQuadNull);
		QuadraticEquation TestThreeQuad = new QuadraticEquation(c);
		QuadraticEquation MultiQuad = TestOneQuad.limitedMultiply(TestThreeQuad);
		QuadraticEquation MultiTest = new QuadraticEquation(a.multiply(c), b.multiply(c), c.multiply(c));
		assertTrue(MultiQuad.equals(MultiTest));
	}

	@Test
	public void testDerivative() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation DiffQuad = new QuadraticEquation(a, b, c);
		MyDouble expOne = new MyDouble(2);
		MyDouble expTwo = new MyDouble(1);
		QuadraticEquation ResultQuad = new QuadraticEquation(a.multiply(expOne), b.multiply(expTwo));
		assertTrue(ResultQuad.equals(DiffQuad.derivative()));
	}

	@Test
	public void testNormalize() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation NormQuad = new QuadraticEquation(a, b, c);
		MyDouble norm = new MyDouble(a.square().add(b.square().add(c.square())));
		MyDouble normTest = new MyDouble(norm.sqrt());
		assertTrue(normTest.equals(NormQuad.normalize()));
	}

	@Test
	public void testEqualsAndCompareTo() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation TestQuadOne = new QuadraticEquation(a, b, c);
		QuadraticEquation TestQuadTwo = new QuadraticEquation(a, b, c);
		QuadraticEquation TestQuadThree = new QuadraticEquation(c, c, c);
		assertEquals(0, TestQuadOne.compareTo(TestQuadTwo));
		assertEquals(-1, TestQuadOne.compareTo(TestQuadThree));
		assertEquals(true, TestQuadOne.equals(TestQuadTwo));
	}

	@Test
	public void testToString() {
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation StringQuad = new QuadraticEquation(a, b, c);
		assertEquals("1x^2+2x+3", StringQuad.toString());
	}
	
	@Test
	public void TestParse() {
		String Quadratic = "x^2+2x+3";
		MyDouble a = new MyDouble(1);
		MyDouble b = new MyDouble(2);
		MyDouble c = new MyDouble(3);
		QuadraticEquation TestQuad = new QuadraticEquation(a, b, c);
		assertTrue((QuadraticEquation.parseQuadratic(Quadratic)).equals(TestQuad));
	}
	
}
