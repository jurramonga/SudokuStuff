package obfuscation;

import java.util.Random;

import solutions.BacktrackSolver;
import tools.SudokuBoard;
import tools.Tools;

public class RandomObfuscator extends Obfuscator
{
	Random r;
	public static final int MAX_DELETED_CELLS = 60;
	public static final int MIN_DELETED_CELLS = 45;
	
	public RandomObfuscator()
	{
		r = new Random();
	}
	
	public RandomObfuscator(int seed)
	{
		r = new Random(seed);
	}
	
	public SudokuBoard obfuscate(SudokuBoard board)
	{
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
		
		int solutions = 0;
		//int attempts = 0;
		
		SudokuBoard puzzle = new SudokuBoard();
		while(solutions != 1)
		{
			puzzle = new SudokuBoard(board);
			//attempts++;
			//System.out.println("Attempt #" + attempts + ".");
			//Tools.printBoard(puzzle);
			
			for (int i = 0; i < MIN_DELETED_CELLS + r.nextInt(MAX_DELETED_CELLS-MIN_DELETED_CELLS); i++)
			{
				int row;
				int column;
				
				do
				{
					row = r.nextInt(9);
					column = r.nextInt(9);
				}
				while (puzzle.getCell(row,column) == 0);
				
				puzzle.setCell(row,column,0);
			}
			
			//Check how many solutions the generated puzzle has
			BacktrackSolver solver = new BacktrackSolver();
			solutions = solver.findSolutions(puzzle).size();
		}
		
		return puzzle;
	}
	
	
}
