package generation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tools.Log;
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
	public static final int STUCK_THRESHOLD = 10000;
	
	/* This boolean is used to escape the method stack. */
	boolean completed;
	Random r;
	SudokuBoard board;
	int boardSize;
	int progressCounter;
	int furthestPosition = -1;
	
	public BacktrackGenerator()
	{
		r = new Random();
		this.boardSize = 9;
	}
	
	public BacktrackGenerator(int boardSize, int seed)
	{
		r = new Random(seed);
		this.boardSize = boardSize;
	}
	
	public SudokuBoard generate()
	{
		Log.output("BacktrackGenerator: Generating a new size-" + boardSize + " board.");
		board = new SudokuBoard(boardSize);
		completed = false;
		progressCounter = 0;

		while (!completed)
		{
			board.reset();
			progressCounter = 0;
			fillCell(0);
		}
		
		//Adding 1 to each cell, taking the range from 0-8 to 1-9 (for a 9x9 puzzle).
		for (int i = 0; i < board.getBoardSize(); i++)
		{
			for (int j = 0; j < board.getBoardSize(); j++)
			{
				board.setCell(j, i, board.getCell(j, i) + 1);
			}
		}
		
		Log.output("BacktrackGenerator: Completed a size " + boardSize + "board.");
		//Log.output(board.toString());
		
		return board;
	}
	
	private void fillCell(int position)
	{
		//Stalled puzzle 
		if(position > furthestPosition)
		{
			furthestPosition = position;
			progressCounter = 0;
		}
		else
		{
			progressCounter++;
			
			if (progressCounter > STUCK_THRESHOLD)
			{
				Log.output("Board appears to be stuck. Retrying.");
				return;
			}
		}		

		//Row value for current position
		int cellRow = board.getRow(position);
		int cellColumn = board.getColumn(position);
		
		//Find illegal numbers
		ArrayList<Integer> illegalNumbers = new ArrayList<Integer>();
		illegalNumbers.addAll(board.getRowValuesBeforePosition(position));
		illegalNumbers.addAll(board.getColumnValuesBeforePosition(position));
		illegalNumbers.addAll(board.getMajorCellValuesBeforePosition(position));
		
		//Used to determine which numbers are legal in the current position.
		Boolean[] valid = new Boolean[board.getBoardSize()];
		Arrays.fill(valid, Boolean.TRUE);

		//Remove illegal numbers from available options
		for (int i = 0; i < illegalNumbers.size(); i++)
		{
			valid[illegalNumbers.get(i)] = false;
		}		
		
		//Generate a list of valid moves
		ArrayList<Integer> tempList = new ArrayList<Integer>();		
		for (int i = 0; i < board.getBoardSize(); i++)
		{
			if (valid[i])
				tempList.add(i);
		}
		
		//Randomize list
		ArrayList<Integer> numbers = new ArrayList<Integer>();		
		while(tempList.size() > 0)
		{
			int index = r.nextInt(tempList.size());
			numbers.add(tempList.get(index));
			tempList.remove(index);
		}
		
		//Try each valid move
		for (int i = 0; i < numbers.size(); i++)
		{
			if (!completed)
			{
				board.setCell(cellRow, cellColumn, numbers.get(i));
				
				if (position == (int)(Math.pow(board.getBoardSize(), 2) - 1))
					completed = true;
				else
					fillCell(position + 1);
			}
		}		
	}
}
