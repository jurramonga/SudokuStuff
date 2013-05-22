package generation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tools.SudokuBoard;


/*
 * BacktrackGenerator.java
 * 
 * BacktrackGenerator is used to generate a completed Sudoku puzzle from scratch. 
 * Recursive backtracking is used to fill in the board.
 * 
 */

public class BacktrackGenerator extends Generator 
{
	/* This boolean is used to escape the method stack. */
	boolean completed;
	Random r;
	SudokuBoard board;
	
	public BacktrackGenerator()
	{
		r = new Random();
		board = new SudokuBoard();
	}
	
	public BacktrackGenerator(int seed)
	{
		r = new Random(seed);
		board = new SudokuBoard();
	}
	
	public SudokuBoard generate()
	{
		board.reset();
		completed = false;

		fillCell(0);		
		
		//Adding 1 to each cell, taking the range from 0-8 to 1-9
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				board.setCell(j, i, board.getCell(j, i) + 1);
			}
		}
		
		return board;
	}
	
	private void fillCell(int position)
	{
		//Row value for current position
		int cellRow = position / 9;
		int cellColumn = position % 9;
		
		//Used to determine which numbers are legal in the current position.
		Boolean[] valid = new Boolean[9];
		Arrays.fill(valid, Boolean.TRUE);

		//Find illegal numbers
		ArrayList<Integer> illegalNumbers = new ArrayList<Integer>();
		illegalNumbers.addAll(board.getRowValuesBeforePosition(position));
		illegalNumbers.addAll(board.getColumnValuesBeforePosition(position));
		illegalNumbers.addAll(board.getMajorCellValuesBeforePosition(position));
		
		//Remove illegal numbers from available options
		for (int i = 0; i < illegalNumbers.size(); i++)
		{
			valid[illegalNumbers.get(i)] = false;
		}		
		
		//Generate a list of valid moves
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++)
		{
			if (valid[i])
				tempList.add(i);
		}
		
		//Randomize list
		ArrayList<Integer> numbers = new ArrayList<Integer>();;
		
		while(tempList.size() > 0)
		{
			int index = r.nextInt(tempList.size());
			numbers.add(tempList.get(index));
			tempList.remove(index);
		}
		
		//---Try each valid move
		for (int i = 0; i < numbers.size(); i++)
		{
			if (!completed)
			{
				board.setCell(cellRow, cellColumn, numbers.get(i));
				
				if (position == 80)
					completed = true;
				else
					fillCell(position + 1);
			}

		}		
	}
}
