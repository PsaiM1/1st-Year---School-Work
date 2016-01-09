import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {

	@Test
	public void selectSortTest() {
		Integer[] incoming = new Integer[5];
		incoming [0] = 5;
		incoming [1] = 4;
		incoming [2] = 3;
		incoming [3] = 2;
		incoming [4] = 1;
		Integer[] result = {1, 2, 3, 4, 5};
		assertArrayEquals(result, SelectionSorter.selectSort(incoming));
	}
	
	@Test
	public void selectSortReverseTest() {
		Integer[] incoming = new Integer[5];
		incoming [0] = 1;
		incoming [1] = 2;
		incoming [2] = 3;
		incoming [3] = 4;
		incoming [4] = 5;
		Integer[] result = {5, 4, 3, 2, 1};
		assertArrayEquals(result, SelectionSorter.selectSortReverse(incoming));
	}
	
	@Test
	public void findMinTest() {
		Integer[] incoming = new Integer[5];
		incoming [0] = 3;
		incoming [1] = 4;
		incoming [2] = 2;
		incoming [3] = 5;
		incoming [4] = 1;
		assertEquals(4, SelectionSorter.findMin(0, incoming));
		
	}
	
	@Test
	public void findMaxTest() {
		Integer[] incoming = new Integer[5];
		incoming [0] = 3;
		incoming [1] = 4;
		incoming [2] = 2;
		incoming [3] = 5;
		incoming [4] = 1;
		assertEquals(3, SelectionSorter.findMax(0, incoming));
	}
	
	@Test
	public void swapTest() {
		Integer[] incoming = new Integer[5];
		incoming [0] = 1;
		incoming [1] = 2;
		incoming [2] = 3;
		incoming [3] = 4;
		incoming [4] = 5;
		SelectionSorter.swap(1, 3, incoming);
		
	}

}
