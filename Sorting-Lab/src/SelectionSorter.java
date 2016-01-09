/**
 * Just a container, really, that serves as a place to house these public methods:
 * 
 * static Integer[] selectSort( Integer[] incoming );
 * 
 * static Integer[] selectSortReverse( Integer[] incoming );
 * 
 * static int findMin( int fromIndex, Integer[] arrayOfInts );
 * 
 * static int findMax( int fromIndex, Integer[] arrayOfInts );
 * 
 * static void swap( int fromPos, int toPos, Integer[] arrayOfInts);
 * 
 * Note: these MUST be public in order for you to pass any of the tests. 
 * 
 * @author UMD CD Department
 *
 */
public class SelectionSorter {
	/**
	 * Returns a sorted (from smallest to largest) shallow COPY of the incoming
	 * array of Integers.
	 * @param incoming
	 * @return
	 */
	public static Integer[] selectSort( Integer[] incoming ) {
		Integer[] incomingCopy = new Integer[incoming.length];
		for (int j = 0; j < incoming.length; j++) {
			incomingCopy[j] = incoming[j];
		}
		for (int i = 0; i < incomingCopy.length; i++) {
			int minIndex = i;
			int min = incomingCopy[i];
			for (int j = i + 1; j < incomingCopy.length; j++) { //this loop finds the min and puts it in the first index
				if (incomingCopy[j] < min) {
					minIndex = j;
					min = incomingCopy[j];
				}
			}
			//these lines of code take the min and put it in the first index and take the current placeholder and shifts it over one
			//this repeats until sorted
			int temp = incomingCopy[i];
			incomingCopy[i] = min;
			incomingCopy[minIndex] = temp;
		}
		return incomingCopy ;
	}
	/**
	 * Returns a sorted (from largest to smallest) copy of the incoming array
	 * of Integers.
	 * @param incoming
	 * @return
	 */
	public static Integer[] selectSortReverse( Integer[] incoming ) {
		Integer[] incomingCopy = new Integer[incoming.length];
		for (int j = 0; j < incoming.length; j++) {
			incomingCopy[j] = incoming[j];
		}
		for (int i = 0; i < incomingCopy.length; i++) {
			int maxIndex = i;
			int max = incomingCopy[i];
			for (int k = i + 1; k < incomingCopy.length; k++) { //this loop finds the max and puts it in the first index
				if (incomingCopy[k] > max) {
					maxIndex = k;
					max = incomingCopy[k];
				}
			}
			//these lines of code take the max and put it in the first index and take the current placeholder and shifts it over one
			//this repeats until sorted
			int temp = incomingCopy[i];
			incomingCopy[i] = max;
			incomingCopy[maxIndex] = temp;
		}
		return incomingCopy;
	}
	/**
	 * Return the index of the smallest element in the arrayOfInts, beginning
	 * the search fromIndex;
	 * @param fromIndex
	 * @param arrayOfInts
	 * @return
	 */
	public static int findMin( int fromIndex, Integer[] arrayOfInts ) {
		int min = Integer.MAX_VALUE; //arbitrary max value
		int minIndex = fromIndex;
		for (int i = fromIndex; i < arrayOfInts.length; i++) {
			if (arrayOfInts[i] < min) { 
				min = arrayOfInts [i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	/**
	 * Returns the index of the largest element in the arrayOfIntegers, beginning
	 * from the fromIndex.
	 * @param fromIndex
	 * @param arrayOfIntegers
	 * @return
	 */
	public static int findMax( int fromIndex, Integer[] arrayOfIntegers ) {
		int max = Integer.MIN_VALUE;//arbitrary min value
		int maxIndex = fromIndex;
		for (int i = fromIndex; i < arrayOfIntegers.length; i++) {
			if (arrayOfIntegers[i] > max) { 
				max = arrayOfIntegers [i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	/**
	 * Swaps the contents in the toPos with the fromPos; note, this 
	 * method does not copy the array, but operates on the arrayOfInts
	 * directly.
	 * @param fromPos
	 * @param toPos
	 * @param arrayOfInts
	 */
	public static void swap( int fromPos, int toPos, Integer[] arrayOfInts ) {
		int temp = arrayOfInts[fromPos];
		int temptwo = arrayOfInts[toPos];
		arrayOfInts[fromPos] = temptwo;
		arrayOfInts[toPos] = temp;
	}
	
}
