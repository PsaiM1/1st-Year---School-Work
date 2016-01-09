
public class SelectionSort {
	
	public void FindtheMinimum() {
		int [] numbers = createArray();
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] < min) { //if(numbers[i] > firstMin && numbers[i] < min) if wanting to find second minimum
				min = numbers [i];
				minIndex = i;
			}
		}
		System.out.println(minIndex + "," + min);
		/*
		 * problem is repeating this algorithm is ineffiecent and takes up memory
		 */
	}
	
	public void RealSelectionSort() {
		
	}

}
