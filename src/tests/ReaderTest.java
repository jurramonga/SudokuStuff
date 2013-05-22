package tests;

import io.SudokuReader;
import tools.SudokuBoard;
import tools.Tools;

public class ReaderTest 
{
	public static void main(String[] args)
	{
			SudokuBoard board = SudokuReader.readFromFile("res\\boards\\falseBoard.txt");
			
			Tools.printBoard(board);
	}

}
