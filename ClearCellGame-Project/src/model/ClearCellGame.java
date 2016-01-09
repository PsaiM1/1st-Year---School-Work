package model;

import java.util.Random;

/* This class must extend Game */


public class ClearCellGame  extends Game{
	
	Random rand;
	int clearStrategey;
	private int score;
	/**
	 * Constructor that calls the Game's constructor via the super call
	 * and sets the random to the inputted random
	 * and the strategy to the inputted strategy
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, java.util.Random random, int strategy) {
		super(maxRows, maxCols);
		rand = random;
		clearStrategey = strategy;
	}
	/**
	 * Called to determine whether or the game is over. If the last row
	 * is not empty then the method returns true and the game is over.
	 *
	 */
	@Override
	public boolean isGameOver() {
		int counter = 0;
		for(int i = 0; i < board[board.length - 1].length; i++) {
			if (board[board.length - 1][i].equals(BoardCell.EMPTY)) {
				counter++;
			}
		}
		
		if (counter == board[board.length - 1].length) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Getter for the score
	 */
	@Override
	public int getScore() {
		
		return score;
	}

	/**
	 * Determines whether or not the last row is empty and then proceeds to
	 * translate the cells down while adding a new random row at the top.
	 *
	 */
	@Override
	public void nextAnimationStep() {
		if (this.hasEmptyRowAt(this.getMaxRows() - 1) == true) {
			for (int row = this.getMaxRows() - 1; row >= 1; row--) {
				for (int col = 0; col < this.getMaxCols(); col++) {
					this.setBoardCell(row, col, this.getBoardCell(row - 1, col));
				}
			}
			
			for (int col = 0; col < this.getMaxCols(); col++) {
				this.setBoardCell(0, col, BoardCell.getNonEmptyRandomBoardCell(rand));
			}
		}
		
		
	}

	/**
	 * The ProcessCell method looks at every cell in the Up, Down, Left, Right
	 * Up Right, Up Left, Down Right, and Down Left directions and checks if they
	 * equal the current cell being processed. It then clears all the cells in that direction
	 * until it reaches a block that is not the same color.
	 * It then shifts all rows up if it finds an empty row.
	 */
	@Override
	public void processCell(int rowIndex, int colIndex) {
		BoardCell currCell = this.getBoardCell(rowIndex, colIndex);
		//clear the cells
		/*horizontals*/
		for (int n = colIndex + 1; n <= this.getMaxCols() - 1; n++) { // right
			if (this.getBoardCell(rowIndex, n).equals(currCell)) {
				this.setBoardCell(rowIndex, n, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		for (int j = colIndex - 1; j >= 0; j--) { // left
			if (this.getBoardCell(rowIndex, j).equals(currCell)) {
				this.setBoardCell(rowIndex, j, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		/*verticals*/
		for (int k = rowIndex - 1; k >= 0; k--) { //up
			if (this.getBoardCell(k, colIndex).equals(currCell)) {
				this.setBoardCell(k, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int m = rowIndex + 1; m <= this.getMaxRows() - 1; m++) { //down
			if (this.getBoardCell(m, colIndex).equals(currCell)) {
				this.setBoardCell(m, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		/*diagonals*/
		for (int a = 1; a < Integer.MAX_VALUE; a++) { // up left
			if (rowIndex == 0 || rowIndex - a < 0 || colIndex - a < 0) {
				break;
			} else if (this.getBoardCell(rowIndex - a, colIndex - a).equals(currCell)) {
				this.setBoardCell(rowIndex - a, colIndex - a, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int b = 1; b < Integer.MAX_VALUE; b++) { // up right
			if (rowIndex == 0 || rowIndex - b < 0 || colIndex + b > this.getMaxCols() - 1) {
				break;
			} else if (this.getBoardCell(rowIndex - b, colIndex + b).equals(currCell)) {
				this.setBoardCell(rowIndex - b, colIndex + b, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		
		for (int c = 1; c < Integer.MAX_VALUE; c++) { // down right
			if (rowIndex == this.getMaxRows() || rowIndex + c > this.getMaxRows() || colIndex + c > this.getMaxCols() - 1) {
				break;
			} else if (this.getBoardCell(rowIndex + c, colIndex + c).equals(currCell)) {
				this.setBoardCell(rowIndex + c, colIndex + c, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int d = 1; d < Integer.MAX_VALUE; d++) { // down left
			if (rowIndex == this.getMaxRows() || rowIndex + d > this.getMaxRows() || colIndex - d < 0) {
				break;
			} else if (this.getBoardCell(rowIndex + d, colIndex - d).equals(currCell)) {
				this.setBoardCell(rowIndex + d, colIndex - d, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		//clear the current cell to EMPTY
		this.setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
		score++;
		//collapse the rows
		for (int currRow = 0; currRow < this.getMaxRows() - 1; currRow++) {
			if (this.hasEmptyRowAt(currRow)) {
				for (int currCol = 0; currCol < this.getMaxCols(); currCol++) {
					this.setBoardCell(currRow, currCol, this.getBoardCell(currRow + 1, currCol));
					this.setBoardCell(currRow + 1, currCol, BoardCell.EMPTY);
				}
			}
		}
		
		
	}
	/**
	 * private method that allows the program to find whether the inputted
	 * row is empty or not
	 * @param rowIndex
	 * @return
	 */
	private boolean hasEmptyRowAt(int rowIndex) {
		int counter = 0;
		for(int i = 0; i < board[rowIndex].length; i++) {
			if (board[rowIndex][i].equals(BoardCell.EMPTY)) {
				counter++;
			}
		}
		
		if (counter == board[rowIndex].length) {
			return true;
		} else {
			return false;
		}
	}

}