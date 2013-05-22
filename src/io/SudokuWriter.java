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
			  
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					out.write(String.valueOf(board.getCell(j,i)));
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
