package tests;

import generation.BacktrackGenerator;
import generation.RandomGenerator;

import java.util.Random;

import solutions.SudokuChecker;
import tools.SudokuBoard;

public class CheckerTest 
{
	public static final int TEST_CASES = 100;
	public static final int SEED = 25387;
	
	public static void main(String[] args)
	{
		BacktrackGenerator bg = new BacktrackGenerator(SEED);
		RandomGenerator rg = new RandomGenerator(SEED);
		Random r = new Random(SEED);
		
		SudokuBoard board;
		
		for(int i = 0; i < TEST_CASES; i++)
		{
			if(r.nextInt() % 2 == 0)
			{
				System.out.println("Generating board by backtracking.");
				board = bg.generate();
			}
			else
			{
				System.out.println("Generating board by random draw.");
				board = rg.generate();
			}

			System.out.println("The generated board is: " + SudokuChecker.checkSolution(board));	
		}
		
		
		
	}

}
