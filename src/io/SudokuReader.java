package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tools.Log;
import tools.SudokuBoard;

public class SudokuReader 
{
	public static SudokuBoard readFromFile(String filename)
	{		
		File boardFile = new File(filename);
		Scanner scanner;
		
		try 
		{
			scanner = new Scanner(boardFile);
		} 
		catch (FileNotFoundException e) 
		{
			Log.output("Error: Could not find specified file.");
			return null;
		}
		
		int boardSize = Integer.parseInt(scanner.next());
		SudokuBoard board = new SudokuBoard(boardSize);
		
		for (int i = 0; i < board.getBoardSize(); i++)
		{
			for (int j = 0; j < board.getBoardSize(); j++)
			{
				String value = scanner.next();
				try
				{
					board.setCell(i, j, Integer.parseInt(value));
				}
				catch (Exception e)
				{
					Log.output("Error: Invalid input found while reading in puzzle. Character '" + value + "' found.");
					scanner.close();
					return null;
				}
			}
		}
		
		scanner.close();
		return board;
	}
}
