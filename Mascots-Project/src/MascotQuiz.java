/*
 * Author: PrasannaSai Meruga
 * Section Number: 0402
 * Teaching Assistant: Ahmed
 * Purpose: To test the user's ability to correctly identify the mascots of four universities
 */

import java.util.Scanner;
/*
 * add comments as needed but all executable code must appear
 * between the two large comment blocks below.
*/
public class MascotQuiz {

	public static void main(String[] args) {

		int score = 0;
		
		String greeting = 
				"In this game, I ask you four questions about mascots for "
				+"US collegiate sports teams." +
				"\nYou get 1 point for each correct answer, "
				+"0 points if you type don't know, "
				+"and you lose a point for wrong answers.";
		final String schoolOptions = "University of Michigan, "
				+"University of Nebraska, "
				+"University of Oklahoma, "
				+"University of Wisconsin";
		final String mascotOptions = 
				"Badgers, Cornhuskers, Sooners, Wolverines";
		String prompt1 = 
				"\nType 1 and I'll give you the mascot and "
				+"you give give the school. \n"
				+"Type 2 and I'll give you the school and "
				+"you give me the mascot. \n"
				+"Type 3 and I'll quit.";		
		
		System.out.println( greeting );
		
		/*************************************************************
		 *  Do NOT delete, move, or change the lines of code above this:
		 * All of your code should appear between these comments.
		 ************************************************************/
		int questionNumber = 0; //declare variable questionNumber to control the do-while loop
		
		Scanner userInput = new Scanner(System.in); //declare the scanner that will be used to control the quiz and input answers based on user input.
		
		do {
			questionNumber++; //this will control the flow of the quiz from question 1 to 2 to 3 etc.
			System.out.println("Question Number:"+questionNumber);
			System.out.println(prompt1);
			int questionOption = userInput.nextInt(); //this will determine whether its a school question or a mascot question or if its quit.
			userInput.nextLine();
			if (questionOption == 2) {
				System.out.println("Answer with one of :"+ mascotOptions);
				// the if loops proceeding determine the question, based on the question number
				if (questionNumber == 1) { //question one: University of Oklahoma
					System.out.print("University of Oklahoma? => ");
					String answerUnivOne = userInput.nextLine();
					/*
					 * All the if statement blocks below have similar structures
					 */
					if (answerUnivOne.equals("Sooners")) { //tests against the correct answer and increments score by 1 if correct
						score++;
					}
					else if (answerUnivOne.equalsIgnoreCase("don't know")) {//if user types "don't know" then it will instead keep the same score
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 2) { //question two: University of Wisconsin
					System.out.print("University of Wisconsin? => ");
					String answerUnivTwo = userInput.nextLine();
					if (answerUnivTwo.equals("Badgers")) {
						score++;
					}
					else if (answerUnivTwo.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 3) { //question three: University of Michigan
					System.out.print("University of Michigan? => ");
					String answerUnivThree = userInput.nextLine();
					if (answerUnivThree.equals("Wolverines")) {
						score++;
					}
					else if (answerUnivThree.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 4) { //question four: University of Nebraska
					System.out.print("University of Nebraska? => ");
					String answerUnivFour = userInput.nextLine();
					if (answerUnivFour.equals("Cornhuskers")) {
						score++;
					}
					else if (answerUnivFour.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
			}
			else if (questionOption == 1)	{ //the question will direct to the mascot questions below if the user types in "2"
				System.out.println("Answer with one of :"+ schoolOptions);
				if (questionNumber == 1) { //question 1: Mascot of Oklahoma
					System.out.print("Sooners ? => ");
					String answerMasOne = userInput.nextLine();
					if (answerMasOne.equals("University of Oklahoma")) {
						score++;
					}
					else if (answerMasOne.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 2) { //question 2: Mascot
					System.out.print("Badgers ? => ");
					String answerMasTwo = userInput.nextLine();
					if (answerMasTwo.equals("University of Wisconsin")) {
						score++;
					}
					else if (answerMasTwo.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 3) { //question 3: Mascot
					System.out.print("Wolverines ? => ");
					String answerMasThree = userInput.nextLine();
					if (answerMasThree.equals("University of Michigan")) {
						score++;
					}
					else if (answerMasThree.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
				if (questionNumber == 4) { //question 4: Mascot
					System.out.print("Cornhuskers ? => ");
					String answerMasFour = userInput.nextLine();
					if (answerMasFour.equals("University of Nebraska")) {
						score++;
					}
					else if (answerMasFour.equalsIgnoreCase("don't know")) {
						score = score + 0;
					}
					else {
						score--;
					}
				}
			}
			else if (questionOption == 3) {// will quit the quiz if user types in "3"
				questionNumber = 5;
				}
			if (questionNumber == 4) {
			System.out.print("Want to play again? (type yes or no):"); 
			String promptPlayAgain = userInput.next(); //prompts the user to answer if they want to play again
				if (promptPlayAgain.equalsIgnoreCase("yes"))	{
					questionNumber = 0; //this resets the entire do-while statement allowing the user to play again
					score = 0; //resets the score
				}
				else if (promptPlayAgain.equalsIgnoreCase("no")) {
					questionNumber = 4; //if no then it sets to 4 so that the loop terminates
				}
				else {
					questionNumber = 4; //just in case they decide to not type yes or no
				}
			}
		} while (questionNumber < 4);
		userInput.close(); //closes the scanner
		
		/*************************************************************
		 *  Do NOT delete, move, or change this next line of code:
		 * This should be the last line of code in your program!
		 ************************************************************/
		System.out.println( "\nBye. Your score is " + score );
	}

}
