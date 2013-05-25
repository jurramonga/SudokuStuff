package io;

import java.io.BufferedWriter;
import java.io.FileWriter;

import tools.SudokuBoard;

public class SudokuWriter 
{
	public static void writeToFile(SudokuBoard board, String filename)
	{
		try
		{
			// Create file 
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			  
			out.write(board.getBoardSize() + "\n");
			for (int i = 0; i < board.getBoardSize(); i++)
			{
				for (int j = 0; j < board.getBoardSize(); j++)
				{
					out.write(String.valueOf(board.getCell(j,i) + "\t"));
				}
			
				out.write("\n");
			}
			  
			out.close();
		}
		catch (Exception e)
		{
			//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
