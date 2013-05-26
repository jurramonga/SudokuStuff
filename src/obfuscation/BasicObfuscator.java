package obfuscation;

import java.util.Random;

import solutions.BacktrackSolver;
import tools.SudokuBoard;

/**
 * BasicObfuscator.java
 * @author Jason Prindle
 *
 * Sets a random number of cells to 0, checking after each one to be sure the puzzle still has 1 solution.
 */

//TODO: Prevent this class from locking up (by becoming unsolvable)
//TODO: Improve speed by keeping track of (and skipping) cells that can't be changed

public class BasicObfuscator extends Obfuscator
{
	SudokuBoard board;
	Random r;
	boolean completed;
	
	public BasicObfuscator()
	{
		r = new Random();
	}
	
	public BasicObfuscator(int seed)
	{
		r = new Random(seed);
	}
	
	public SudokuBoard obfuscate(SudokuBoard board, float percentage)
	{
		completed = false;
		this.board = new SudokuBoard(board);
		
		int removalCount = (int)(Math.pow(board.getBoardSize(), 2) * percentage);
		
		//Check that the starting board is a valid, solved puzzle
		for(int i = 0; i < board.getBoardSize(); i++)
		{
			for (int j = 0; j < board.getBoardSize(); j++)
			{
				int cellValue = board.getCell(j,i);
				if (cellValue < 1 || cellValue > board.getBoardSize())
				{
					System.out.println("Invalid puzzle. Can't be obfuscated.");
					return null;
				}
			}
		}	
		
		int count = removalCount;
		clearCell(count);
		return this.board;
	}
	
	private void clearCell(int count)
	{
		if (count == 0)
		{
			completed = true;
			return;
		}
		
		int row;
		int column;
		int oldValue;
		
		int solutions = 0;
		while(solutions != 1)
		{
			do
			{
				row = r.nextInt(board.getBoardSize());
				column = r.nextInt(board.getBoardSize());
				oldValue = board.getCell(row,column);
			}
			while (oldValue == 0);
			
			board.setCell(row, column, 0);
			
			solutions = BacktrackSolver.findSolutions(board).size();
			
			if (solutions != 1)
			{
				board.setCell(row, column, oldValue);
			}
		}
		
		clearCell(count-1);
	}
}
