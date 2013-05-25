package tests;

import generation.BacktrackGenerator;
import io.SudokuWriter;
import tools.SudokuBoard;

/*
 * WriterTest.java
 * 
 * Generates a number of puzzles using recursive backtracking and writes each of them to file.
 */

public class WriterTest 
{
	public static final int TEST_CASES = 5;
	public static final int BOARD_SIZE = 16;
	
	public static void main(String[] args)
	{
		System.out.println("Creating and saving " + TEST_CASES + " sudoku puzzles.");
		long start = System.currentTimeMillis();
		
		SudokuBoard board;
		BacktrackGenerator bg = new BacktrackGenerator(BOARD_SIZE,53);
		int root = (int)Math.sqrt(BOARD_SIZE);
		
		for (int i = 1; i <= TEST_CASES; i++)
		{
			board = bg.generate();	
			SudokuWriter.writeToFile(board, "res\\boards\\" + root + "x" + root + "-" + i + ".sdkp");
		
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Total time: " + (end - start) + "ms.");
		System.out.println("Average per puzzle: " + (end - start)/(float)TEST_CASES + "ms.");
	}

}
