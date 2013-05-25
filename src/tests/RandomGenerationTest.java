package tests;

import generation.RandomGenerator;
import tools.SudokuBoard;

public class RandomGenerationTest 
{
	public static final int SEED = 39325;
	
	public static void main(String[] args)
	{
		RandomGenerator rg = new RandomGenerator(16, SEED);
		
		SudokuBoard board = rg.generate();
		
		board.print();
	}

}
