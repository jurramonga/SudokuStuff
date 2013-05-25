package obfuscation;

import java.util.Random;

import tools.Log;
import tools.SudokuBoard;

/**
 * RandomObfuscator.java
 * @author Jason Prindle
 *
 * RandomObfuscator will take a filled Sudoku board, and randomly remove (replace with a 0) a number of cells.
 * The generated puzzle is not guaranteed to have a single solution.
 */

public class RandomObfuscator extends Obfuscator
{
	Random r;

	public RandomObfuscator()
	{
		r = new Random();
	}
	
	public RandomObfuscator(int seed)
	{
		r = new Random(seed);
	}
	
	
	public SudokuBoard obfuscate(SudokuBoard board, float percentage)
	{
		if (percentage < 0f || percentage > 1f)
		{
			Log.output("ERROR: Cannot obfuscate board. Percentage value is invalid (0 <= percentage <= 1): " + percentage);
			return null;
		}
		
		if (!board.isFilled())
		{
			Log.output("ERROR: Cannot obfuscate board. Board contains a zero-value.");
			return null;
		}
		
		int size = board.getBoardSize();
		int removalCount = (int)(Math.pow(board.getBoardSize(), 2) * percentage);
		SudokuBoard puzzle = new SudokuBoard(board);
		
		int row;
		int column;
		int value;	
		while (removalCount > 0)
		{
			row = r.nextInt(size);
			column = r.nextInt(size);			
			value = puzzle.getCell(row, column);
			
			//TODO: Keep track of values that have already been cleared and prevent them from being selected again.
			if (value != 0)
			{
				puzzle.setCell(row, column, 0);
				removalCount--;
			}
		}
		
		return puzzle;
	}
}
