
public class DoubleArray {
	public double[] myArray;
	
	public DoubleArray(double[] other) {
		//myArray = other;
		
		  //for deep copy its:
		 int len = other.length;
		 DoubleArray myArray = new DoubleArrayother;
		 
		 
	}
	
	public void printArray() {
		for (int i = 0; i < myArray.length; i++) {
			System.out.print(myArray[i] + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] a = {1, 2, 3};
		DoubleArray da = new DoubleArray(a);
		da.printArray();
		a[0] = 5;
		da.printArray();

	}

}
