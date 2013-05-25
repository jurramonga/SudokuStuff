package obfuscation;

import tools.SudokuBoard;

public abstract class Obfuscator
{
	/**
	 * Takes a completed Sudoku board and removes cells, resulting in a puzzle with a single solution.
	 * @param board - Completed board
	 * @param percentage - Percentage of cells to remove
	 * @return
	 */
	public abstract SudokuBoard obfuscate(SudokuBoard board, float percentage);
}
