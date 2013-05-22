package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tools.SudokuBoard;

public class SudokuReader 
{
	public static SudokuBoard readFromFile(String filename)
	{
		SudokuBoard board = new SudokuBoard();		
		File boardFile = new File(filename);
		Scanner scanner;
		
		try 
		{
			scanner = new Scanner(boardFile);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error while reading in a file.");
			System.out.println("Error: " + e.getMessage());
			return null;
		}		
		
		int row = 0;
		while(scanner.hasNext())
		{
			String line = scanner.next();
			for (int i = 0; i < 9; i++)
			{
				try
				{
					board.setCell(row, i, Integer.parseInt(String.valueOf(line.charAt(i))));
				}
				catch (Exception e)
				{
					System.out.println("Invalid input found. Character '" + line.charAt(i) + "' found.");
					System.out.println("Error: " + e.getMessage());
					return null;
				}
			}
			
			row++;
		}
		
		return board;
	}
}
