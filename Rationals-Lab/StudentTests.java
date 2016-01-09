import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {

	@Test
	public void testRational() {
		Rational r = new Rational(54, 24);
		Rational x = r.reduce();
		assertEquals(9, 4);
	}

}
