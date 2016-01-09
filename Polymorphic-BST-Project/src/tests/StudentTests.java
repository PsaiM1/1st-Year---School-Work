package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import tree.PolymorphicBST;


public class StudentTests {
	
	@Test
	public void collectionTest() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(4, "Four");
		assertEquals(3, ptree.height());
		
		assertEquals(ptree.keySet().toString(), "[1, 2, 3, 4]");
	}
	
}