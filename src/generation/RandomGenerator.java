package generation;

import java.util.Random;

import tools.SudokuBoard;

/*
 * RandomGenerator.java
 * 
 * RandomGenerator fills a board with a random value from 1-9. 
 * It is highly likely that the generated board will be illegal.
 */

public class RandomGenerator extends Generator
{
	Random r;
	
	public RandomGenerator()
	{
		r = new Random();
	}
	
	public RandomGenerator(int seed)
	{
		r = new Random(seed);
	}
	
	public SudokuBoard generate()
	{
		SudokuBoard board = new SudokuBoard();

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				board.setCell(j, i, r.nextInt(9) + 1);
			}
		}
		
		return board;
	}
}
