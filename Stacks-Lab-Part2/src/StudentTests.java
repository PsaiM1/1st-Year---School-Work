
import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {

	// your tests go here
	@Test
	public void testConvert() {
		assertEquals("10101", BaseConverter.fromBase10(21, 2));
		assertEquals("210", BaseConverter.fromBase10(21, 3));
	}

}
