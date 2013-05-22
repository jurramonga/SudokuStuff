package solutions;

/**
 * BacktrackSolver.java
 * 
 * Takes an obfuscated puzzle as input and returns the completed puzzle.
 */
import java.util.ArrayList;
import java.util.Arrays;

import tools.SudokuBoard;

public class BacktrackSolver 
{
	static boolean completed;
	static SudokuBoard board;
	static ArrayList<SudokuBoard> boards;
	
	public BacktrackSolver()
	{
		
	}
	
	public static ArrayList<SudokuBoard> findSolutions(SudokuBoard inputBoard)
	{
		completed = false;
		board = new SudokuBoard(inputBoard.getBoard());
		boards = new ArrayList<SudokuBoard>();
		
		fillCell(0);
		
		return boards;
	}
	
	private static void fillCell(int position)
	{
		//If the puzzle is completed...
		if (position > 80)
		{
			boards.add(new SudokuBoard(board.getBoard()));
		}
		//If this cell is already filled in, skip it.
		else if (board.getCell(position) != 0)
		{
			fillCell(position + 1);
		}
		//If this spot needs a number
		else if(board.getCell(position) == 0)
		{
			//Consider all numbers as legal options
			boolean[] valid = new boolean[9];
			Arrays.fill(valid, Boolean.TRUE);
			
			//Remove illegal options
			ArrayList<Integer> illegalNumbers = board.getConnectedValues(position);
			for (Integer i: illegalNumbers)
			{
				if (i != 0)
					valid[i-1] = false;
			}			
			
			//Use each legal option
			for (int i = 0; i < 9; i++)
			{
				if (valid[i])
				{
					board.setCell(position, i+1);
					fillCell(position + 1);
				}
			}
			
			//Before we fall back to the previous cell, reset this cell
	    	board.setCell(position, 0);
		}
	}	
}
