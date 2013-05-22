package tests;

import io.SudokuReader;
import solutions.SudokuChecker;
import tools.SudokuBoard;
import tools.Tools;

public class ReaderTest 
{
	public static void main(String[] args)
	{
			SudokuBoard board = SudokuReader.readFromFile("res\\boards\\columnTest");
			
			System.out.println(SudokuChecker.checkSolution(board));
			Tools.printBoard(board);
	}

}
