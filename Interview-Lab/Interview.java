/*
 * This lab is intended to be completed in one recitation, but will be extended into the next lab session 
 * in order to allow more than enough time to complete it.
 * 
 * Essentially, you will create a Scanner object and use its methods in conjunction with print statements
 * to simulate an ask-and-read dialog with a user. 
 * 
 * You will ask the user to provide their name (as a single word, assume first name here)
 * You will ask the user their age in full years (no fraction or months)
 * You will ask the user how many full months since their last birthday (again, whole numbers here).
 * 
 * You will use this information to calculate the user's age in months and use that information to print
 * out a greeting.
 */
import java.util.Scanner;


public class Interview {

	public static void main(String[] args) {
		// Create an input scanner
		Scanner s = new Scanner(System.in);

		// Prompt the user for their name and read it. Assume the name will be just ONE WORD.
		// The prompt needs to be "What is your name?" and it must allow the user to enter their answer
		// on the same line as where the prompt appears.
		System.out.print("What is your name?");
		String Name = s.next();
		

		// Prompt the user for their age in whole years and read it.
		// The prompt needs to be "How old are you in full years?" and it must allow the user to enter 
		// their answer on the same line as where the prompt appears.
		System.out.print("How old are you in full years?");
		int Age = s.nextInt();
		
		
		
		

		// Ask how many full months it has been since their last birthday and read it.
		// The prompt needs to be "How many full months has it been since your last birthday?" 
		// and it must allow the user to enter their answer on the same line as where the prompt 
		// appears.
		System.out.print("How many full months has it been since your last birthday?");
		int months = s.nextInt();
		
		
		
		
		

		// This closes the input scanner since you are done with it by this point.
		s.close();
		
		
		// Calculate their total age in months and store this in a variable. 
		int totalMonths = (Age * 12)+months;
		
		

		
		/* 
		 * Print the appropriate one of the following lines, depending on their age: 
		 *	age > 900 months, print: Greetings NAME, your age is TOTALMONTHS months. 
		 *  age < 120 months, print: Hello NAME, your age is only TOTALMONTHS months. 
		 *  any other age, print: Howdy NAME, you are TOTALMONTHS months old.
		 */
		if(totalMonths > 900) {
			System.out.println("Greetings "+Name+", your age is "+totalMonths+" months." );
		} else if(totalMonths < 120) {
			System.out.println("Hello "+Name+", your age is only "+totalMonths+" months.");
		} else {
			System.out.println("Howdy "+Name+", you are "+totalMonths+" months old.");
		}
		
	}

}
