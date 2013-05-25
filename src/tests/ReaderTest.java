package tests;

import io.SudokuReader;
import tools.SudokuBoard;

public class ReaderTest 
{
	public static void main(String[] args)
	{
			SudokuBoard board = SudokuReader.readFromFile("res\\boards\\check.sdkp");
			
			//System.out.println(SudokuChecker.checkSolution(board));
			board.print();
	}

}
