import java.awt.Color;
import GridTools.SquareGrid;

public class OperatorMaker {
	

	
	
	/* *************************************************
	 * Draws a single Operator in the already created grid
	 * that is passed as the first parameter 
	 * according to the symbol selection passed as the 
	 * second parameter by calling the appropriate
	 * helper method.	
	 * 
	 * All of the methods are static and are passed in a
	 * SquareGrid and the number indicating which symbol 
	 * to draw.				
	 * *************************************************/
	
	public static void drawOp(SquareGrid grid, int symbol) {
		if (symbol == 1) {
			minus(grid);
		} else if (symbol == 2) {
			plus(grid);
		} else if (symbol == 3) {
			divide(grid);
		} else if (symbol == 4) {
			multiply(grid);
		}
		

	}
	
	
	
	

	/*
	 * The helper methods you should write...
	 * 
	 * 
	 * You may add more helper methods if you want, but they 
	 * all need to be static as well.	 
	 */
	
	public static void minus(SquareGrid grid){
		int row = grid.getHt()/2;
		for (int column = 0; column < grid.getHt(); column++) {
			grid.setColor(row, column, Color.BLUE);
		}
	}

	public static void plus(SquareGrid grid){
		int row = grid.getHt()/2;
		for (int column = 0; column < grid.getHt(); column++) {
			grid.setColor(row, column, Color.BLUE);
		}
		int column = grid.getWd()/2;
		for (row = 0; row < grid.getHt(); row++) {
			grid.setColor(row, column, Color.BLUE);
		}

	}

	public static void divide(SquareGrid grid){
		for (int row = 0; row <grid.getWd(); row++) {
			grid.setColor(row, grid.getWd()-row-1, Color.BLUE);
		}

	}

	public static void multiply(SquareGrid grid){
		plus(grid);
		divide(grid);
		for (int row = 0; row < grid.getHt(); row++) {
			grid.setColor(row, row, Color.BLUE);
		}

	}

	
}

