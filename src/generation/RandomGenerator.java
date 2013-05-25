package generation;

import java.util.Random;

import tools.SudokuBoard;

/**
 * RandomGenerator.java
 * @author Jason Prindle
 * 
 * RandomGenerator fills a board with a random value from 1-boardSize
 * The generated puzzle will almost certainly be an illegal board.
 */
public class RandomGenerator extends Generator
{
	Random r;
	SudokuBoard board;
	
	public RandomGenerator()
	{
		r = new Random();
		board = new SudokuBoard(9);
	}
	
	public RandomGenerator(int boardSize, int seed)
	{
		r = new Random(seed);
		board = new SudokuBoard(boardSize);
	}
	
	public SudokuBoard generate()
	{
		int size = board.getBoardSize();
		board = new SudokuBoard(size);
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				board.setCell(j, i, r.nextInt(size) + 1);
			}
		}
		
		return board;
	}
}
