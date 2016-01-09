import java.util.Scanner;

public class ASCII_Art_Part2 {
	
	//NOTE: You can add static helper methods here if you want to.
	

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Shape? ");
		String shape = s.next();
		System.out.print("Size? ");
		int size = s.nextInt();
	
		if (shape.equals("l-triangle")) {
			//YOUR CODE HERE
			for (int row = size; row >= 1; row--) {
				for (int column = 1; column <= row; column++) {
					System.out.print("*");
				}
				System.out.println("");
			}
		} else if (shape.equals("r-triangle")) {
			//YOUR CODE HERE
			for (int row = 0; row < size; row++) {
				for (int column = size - 1; column >= 0; column--) {
					if (column > row) {
						System.out.print(" ");
					} else { 
						System.out.print("*");
					}
				}
				System.out.print("\n");
			}		
		} else {  // stripes
			//YOUR CODE HERE
			for (int row = 1; row <= size ; row++) {
				for (int column = 1; column <= size ; column++) {
					System.out.print(row * column);
				}
				System.out.print("\n");
			}
		}
		
		s.close();
	}

}
