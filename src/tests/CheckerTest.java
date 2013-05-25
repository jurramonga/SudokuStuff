package tests;

import io.SudokuReader;
import solutions.SudokuChecker;
import tools.SudokuBoard;

public class CheckerTest 
{
	//public static final int TEST_CASES = 100;
	
	public static void main(String[] args)
	{
		SudokuBoard board = SudokuReader.readFromFile("res\\boards\\5x5-1.sdkp");
		
		board.print();
		System.out.println("The generated board is: " + SudokuChecker.checkSolution(board));	
	}
}
