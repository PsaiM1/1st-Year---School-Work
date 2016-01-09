/**
 * Author: PrasannaSai Meruga
 * TA: Ahmed
 * Section Number: 0402
 * Date: 10/9/14
 */
import java.awt.Color;

import GridTools.MyGrid;

public class FlagMaker {

	/* *************************************************
	 * Draws a single flag in the already created grid
	 * that is passed as the first parameter 
	 * according to the countryCode passed as the 
	 * second parameter.		
	 * 
	 * (Student: you will need to modify this method	- the code
	 * provided here is for demonstration purposes only.			
	 * *************************************************/
	public static void drawFlag(MyGrid grid, int flagNumber) { 
		/* the if statements below are used to declar the helper methods for the flags based on
		 * what the user has input in the Example Driver*/
		if (flagNumber == 1) {
			Poland(grid); 
		} else if (flagNumber == 2) {
			Ukraine(grid);
		} else if (flagNumber == 3) {
			CzechRepublic(grid);
		} else if (flagNumber == 4) {
			Benin(grid);
		} else if (flagNumber == 5) {
			Rwanda(grid);
		} else if (flagNumber == 6) {
			Bahamas(grid);
		} else if (flagNumber == 7) {
			Afghanistan(grid);
		} else if (flagNumber == 8) {
			Jersey(grid);
		} else if (flagNumber == 9) {
			Scotland(grid);
		} else {
			ErrorFlag(grid);
		}
	}
	
	/*Helper functions for the drawFlag method appear below this line*/
	
	/*
	 * This method takes an already created grid, a row number that
	 * will be assumed to be a valid row on that grid and a color.  It
	 * then draws on that grid, in the indicated row, a full horizontal
	 * line in the color indicated.
	 * (Student: you may or may not wish to keep this method -- it is just
	 * provided as a demonstration of what you may want to do.)
	 */
	
	// prints out flag for Poland:OP 1
	// this will print out the bottom red half of the flag
	public static void Poland(MyGrid grid) { 
		if (grid.getHt() % 2 != 0) { //error flag, checks if size is even
			ErrorFlag(grid);
		} else {
			for (int row = (grid.getHt()/2 ); row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.RED);
				}
			}
		}
	}
	// prints out flag for Ukraine:OP 2
	//this will print the flag with the top half blue and the bottom half yellow
	public static void Ukraine(MyGrid grid) { 
		if (grid.getHt() % 2 != 0) { //error flag, checks if size is even
			ErrorFlag(grid);
		} else {
			for (int row = (grid.getHt()/2-1); row >= 0; row--) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.BLUE);
				}
			}
			for (int row = (grid.getHt()/2); row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.YELLOW);
				}
			}
		}
	}
	// prints out flag for Czech Republic:OP3
	/* this method works layer by layer; first it will print the bottom half red first
	 * then it will print out the triangle that finishes the flag*/
	public static void CzechRepublic(MyGrid grid) { 
		if (grid.getHt() % 2 != 0) { // error flag, checks if size is even
			ErrorFlag(grid);
		} else {
			for (int row = (grid.getHt()/2 ); row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.RED);
				}
			}
			for (int row = 0; row < grid.getHt(); row++) { //sets the first row
				grid.setColor(row, 0, Color.BLUE);
			}
			for (int row = 0; row <= (grid.getHt()/2 - 1); row++) {//right side up triangle
				for (int column = 0; column <= row; column++) {
					grid.setColor(row, column+1, Color.BLUE);
				}

			}
			for (int row = grid.getHt()-1; row > (grid.getHt()/2)-1; row--) {//upside down triangle
				for (int column = 0; column <= grid.getHt()-(row); column++) {
					grid.setColor(row, column, Color.BLUE);
				}
			}
		}
	}
	//prints Benin flag:OP 4
	/* this method first prints out the top half of the flag with the color yellow and then the 
	 * bottom half of the flag with the color red and then colors the left third of the flag green*/
	public static void Benin(MyGrid grid) {
		if (grid.getHt() % 6 != 0) { //error flag, checks if size is a multiple of six
			ErrorFlag(grid);
		} else {
			for (int row = (grid.getHt()/2-1); row >= 0; row--) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.YELLOW);
				}
			}
			for (int row = (grid.getHt()/2); row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.RED);
				}
			}
			for (int row = 0; row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd()/3; column++ )
					grid.setColor(row, column, Color.GREEN);
			}
		}
	}
	//prints Rwanda flag:OP 5
	/* this method is prints out the top half of the flag as blue, it then colors the grid yellow from the halfway point to three-quarters
	 * down the flag, and then prints green for the last quarter */
	public static void Rwanda(MyGrid grid) {
		if (grid.getHt() % 4 != 0) { //error flag, checks if size is a multiple of four
			ErrorFlag(grid);
		} else {
			for (int row = (grid.getHt()/2-1); row >= 0; row--) { 
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.BLUE);
				}
			}
			for (int row = (grid.getHt()/2); row < ((3*grid.getHt())/4); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.YELLOW);
				}
			}
			for (int row = ((3*grid.getHt())/4); row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.GREEN);
				}
			}
		}
	}
	//prints Bahamas flag:OP 6
	/* this method initially colors the flag all blue and then colors the middle two rows yellow, and then iterated for
	 * two triangles so that it prints a black triangle on the left side of the flag. */
	public static void Bahamas(MyGrid grid) {
		if (grid.getHt() % 2 != 0) {//error flag, checks if size is even
			ErrorFlag(grid);
		} else {
			for (int row = 0; row < grid.getHt(); row++) { //puts first layer of blue
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.BLUE);
				}
			}
			for (int row = (grid.getHt()/2-1); row <= (grid.getHt()/2); row++) { //places the middle yellow rows
				for (int column = 0; column < grid.getWd(); column++) {
					grid.setColor(row, column, Color.YELLOW);
				}
			}
			for (int row = 0; row < grid.getHt(); row++) { //sets the first column
				grid.setColor(row, 0, Color.BLACK);
			}
			for (int row = 0; row <= (grid.getHt()/2 - 1); row++) {//right side up triangle
				for (int column = 0; column <= row; column++) {
					grid.setColor(row, column+1, Color.BLACK);
				}

			}
			for (int row = grid.getHt()-1; row > (grid.getHt()/2)-1; row--) {//upside down triangle
				for (int column = 0; column <= grid.getHt()-(row); column++) {
					grid.setColor(row, column, Color.BLACK);
				}
			}
		}
	}
	//prints afghan flag:OP 7
	/* this method iterates in columns instead of rows, so it prints black in the left third of the columns in the flag, 
	 * then it prints red in the middle third of the columns in the flag, and then it prints the green in the right 
	 * third of the flag */
	public static void Afghanistan(MyGrid grid) {
		if (grid.getHt() % 3 != 0) {//error flag, checks if size is multiple of three
			ErrorFlag(grid);
		} else {
			for (int row = 0; row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd()/3; column++ ) {
					grid.setColor(row, column, Color.BLACK);
				}
			}
			for (int row = 0; row < grid.getHt(); row++) {
				for (int column = grid.getWd()/3; column < ((2*grid.getWd())/3); column++ ) {
					grid.setColor(row, column, Color.RED);
				}
			}
			for (int row = 0; row < grid.getHt(); row++) {
				for (int column = ((2*grid.getWd())/3); column < grid.getWd(); column++ ) {
					grid.setColor(row, column, Color.GREEN);
				}
			}
		}
	}
	//prints jersey flag:OP 8
	/* this method prints out two intersecting diagonals that form a red X shape across the flag, it prints at the same row number 
	 * twice but instead prints in an adjacent column which forms a two block step diagonal. */
	public static void Jersey(MyGrid grid) {
		if (grid.getHt() % 2 != 0) { // this if statement is switched, so now it checks if the size is odd
			for (int row = 0; row <grid.getHt(); row++) { //diagonal right left
				grid.setColor(row, grid.getWd()-(2*row)-2, Color.RED);
				grid.setColor(row, grid.getWd()-(2*row)-1, Color.RED);
			}
			for (int row = 0; row <grid.getHt(); row++) { //diagonal left right
				grid.setColor(row, (2*row)+1, Color.RED);
				grid.setColor(row, (2*row), Color.RED);
			}
		} else { // if size is not odd it prints the error flag
			ErrorFlag(grid);
		}
	} 
	//prints Scotland flag:OP 9
	/* this method is similar to the jersey methid but this methid will initially print the entire grid as blue
	 * then it creates the white diagonals that layer on top of the blue making a white X on blue flag. */
	public static void Scotland(MyGrid grid) {
		if (grid.getHt() % 2 != 0) {// this if statement is switched, so now it checks if the size is odd
			for (int row = 0; row < grid.getHt(); row++) {
				for (int column = 0; column < grid.getWd(); column++) { // sets first layer of blue
					grid.setColor(row, column, Color.BLUE);
				}
			}
			for (int row = 0; row <grid.getHt(); row++) { //diagonal right left
				grid.setColor(row, grid.getWd()-(2*row)-2, Color.WHITE);
				grid.setColor(row, grid.getWd()-(2*row)-1, Color.WHITE);
			}
			for (int row = 0; row <grid.getHt(); row++) { //diagonal left right
				grid.setColor(row, (2*row)+1, Color.WHITE);
				grid.setColor(row, (2*row), Color.WHITE);
			}
		} else {// if size is not odd then it prints the error flag
			ErrorFlag(grid);
		}
	} 
	/* code for error flag, prints if size is less than 4 or exceeds 30, if the country code
	 * is incorrect, or if the size does not match the requirements of the code like being even or a multiple of 
	 * a certain number */
	public static void ErrorFlag(MyGrid grid) {
		grid.setColor(0,0,Color.RED);
		grid.setColor(grid.getHt()-1,0,Color.RED);
		grid.setColor(grid.getHt()-1, grid.getWd()-1, Color.RED);
		grid.setColor(0,grid.getWd()-1, Color.RED);
	}
	public static void drawLine(MyGrid grid, int row,
			Color myColor){
		for (int col = 0; col < grid.getWd(); col++){
			grid.setColor(row,col,myColor);
		}		
	}
}



 
