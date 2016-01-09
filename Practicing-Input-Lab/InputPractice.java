/* This lab exercise is meant to be done during the lab session for which you
 * are registered. You must submit whatever you have before the end of your 
 * lab session.  You can still receive full credit even if all tasks here are not
 * done when the lab session ends - just complete it on Wednesday.
 * During lab sessions, you may work together with the people around you.
 * 
 * This program will read input from the user and print
 * it out in the form specified 
 * 
 * This program will help you when you go to a restaurant and don't know
 * how much tip to give your waitress/waiter.  
 * You will tell the amount that was on the bill (the check) that the
 * waiter/waitress gave you.  Then you will type in a number 0-100 indicating
 * what percentage of tip you want to give.  Then you will give your name,
 * and it will give you a personalized message of how much to pay.
 * 
 * You only need to fill in the places indicated below.
 * Do not change the code I have provided.
 */

import java.util.Scanner;

public class InputPractice {

	public static void main(String[] args) {
		/*This code sets up the Scanner object so you can use it for
		 * input from System.in (the keyboard)
		 * This code is provided for you */
		Scanner keyboardInput  = new Scanner(System.in);
		
		/*First you must prompt for and read the amount
		 * the user has spent for this dinner.
		 * You may assume the user will answer with a double 
		 * that represents the amount of their bill.
		 * Prompt is given for you, but you have to read from input.*/
		System.out.print("How much is your bill?");
		double Bill = keyboardInput.nextDouble();
		
		/*Second, you must prompt for and read the percent
		 * the user would like to use when calculating the tip.
		 * You may assume the user will enter an integer between 0 and 100
		 * to indicate the percentage of the bill they would like
		 * to give as a tip. 
		 * All code for this section is given for you.*/
		System.out.print("What percentage of tip would you like to give?");
		double percentTip = keyboardInput.nextDouble();
		
		/* Next you must calculate the total amount to be paid 
		 * the amount of the bill plus the amount of the tip */
		/* You need to do this section completely (remove the "=0"). */
		double Tip = (percentTip/100.0)*Bill;
		double totalBill = Bill + Tip;
		
		/* This section should print (to the screen) the amount to be paid.
		 * note just the number no sentence around it -
		 * it is OK if there are too many digits beyond the decimal.
		 * e.g.  1.2312   or 1231.99483 */
		/* All code for this section is done for you */
		System.out.println(totalBill);
		
		/*This section needs to prompt for and read the name of
		 * the users.  This code has been provided.
		 * You may assume that the user will enter his/her name 
		 * with firstname followed by lastname with a single space between. */
		/* The prompt for this section is given for you */
		/* Be careful - the input may be more than one word,
		 * but the last thing read from the input stream was a number. */
		System.out.print("What is your name?");
		String name = keyboardInput.next();
		String name2 = keyboardInput.next();

		/*Then write a nice message to the users telling him/her
		 * (by name to pay the bill)
		 * e.g. Bob Smith, please pay $45.23412 
		 * or   Joe Rosenthal, please pay $23.0
		 * (again don't worry about too many or too few decimal digits)*/
		System.out.println(name+" "+name2+", please pay $" + totalBill);

	}
}
