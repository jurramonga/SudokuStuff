package obfuscation;

import java.util.ArrayList;
import java.util.Random;

import solutions.BacktrackSolver;
import tools.Log;
import tools.SudokuBoard;

/**
 * BasicObfuscator.java
 * @author Jason Prindle
 *
 * Sets a number of cells to 0, checking after each one to be sure the puzzle still has 1 solution.
 */

public class BasicObfuscator extends Obfuscator
{
	SudokuBoard board;
	Random r;
	boolean completed;
	ArrayList<Integer> unchecked;
	
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
		this.board = new SudokuBoard(board);
		
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
		
		int size = board.getBoardSize();
		int removalCount = (int)(Math.pow(size, 2) * percentage);
		completed = false;
		
		while(!completed)
		{
			this.board = new SudokuBoard(board);
			unchecked = new ArrayList<Integer>();
			for (int i = 0; i < size*size; i++)
			{
				unchecked.add(i);
			}
			
			clearCell(removalCount);
		}
		
		return this.board;
	}
	
	private void clearCell(int count)
	{
		if (count == 0)
		{
			completed = true;
			return;
		}
		
		int position;
		int oldValue;
		
		int solutions = 0;
		do
		{
			if (unchecked.size() < count)
			{
				Log.output("Obfuscator stuck. Retrying.");
				return;
			}
			else
			{
				Log.output(count + " " + unchecked.toString());
			}
			int index = r.nextInt(unchecked.size());
			position = unchecked.get(index);
			oldValue = board.getCell(position);
			unchecked.remove(index);
			
			board.setCell(position, 0);
			
			
			/* This really slows things down. All I need to do find 2 different solutions. Not all of them.
			 */
			solutions = BacktrackSolver.hasMultipleSolutions(board);
			
			if (solutions != 1)
			{
				board.setCell(position, oldValue);
			}
		}
		while (solutions != 1);
		
		clearCell(count-1);
	}
}
