package obfuscation;

import java.util.Random;

import solutions.BacktrackSolver;
import tools.SudokuBoard;

/**
 * BacktrackObfuscator.java
 * @author Jason Prindle
 *
 * This isn't backtracking...
 */
public class BasicObfuscator extends Obfuscator
{
	public static final int MAX_DELETED_CELLS = 60;
	public static final int MIN_DELETED_CELLS = 50;

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
	
	public SudokuBoard obfuscate(SudokuBoard board)
	{
		completed = false;
		this.board = new SudokuBoard(board);
		
		//Check that the starting board is a valid, solved puzzle
		for(int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int cellValue = board.getCell(j,i);
				if (cellValue < 1 || cellValue > 9)
				{
					System.out.println("Invalid puzzle. Can't be obfuscated.");
					return null;
				}
			}
		}	
		
		int count = MIN_DELETED_CELLS - r.nextInt(MAX_DELETED_CELLS - MIN_DELETED_CELLS);
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
				row = r.nextInt(9);
				column = r.nextInt(9);
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
