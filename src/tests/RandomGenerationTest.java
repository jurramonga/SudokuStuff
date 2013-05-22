package tests;

import generation.RandomGenerator;
import tools.SudokuBoard;
import tools.Tools;

public class RandomGenerationTest 
{
	public static final int SEED = 39325;
	
	public static void main(String[] args)
	{
		RandomGenerator rg = new RandomGenerator(SEED);
		
		SudokuBoard board = rg.generate();
		
		Tools.printBoard(board);
	}

}
