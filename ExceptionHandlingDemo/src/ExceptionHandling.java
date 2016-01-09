import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandling {
	public static int readAge() {
		Scanner s = new Scanner(System.in);
		try {
			int age = s.nextInt();
			if (age < 0) {
				InputMismatchException e = new InputMismatchException();
				throw e;
			}
			return age;
		} catch (InputMismatchException e) {
			//we dont need to create an object
			System.out.println("Error");
			throw e;
		} finally { // this forces java to execute this code
			s.close(); //no matter what >:D
		}
		/* putting something here is unreachable
		 * there is no case that will allow the program
		 * to reach here
		 */
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try {
			int x = s.nextInt();
			System.out.println("Integer is:" + x);
		} catch (InputMismatchException e) {
			System.out.println("Error");
		}
		s.close();
	}

}
