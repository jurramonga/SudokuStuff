package solutions;

import java.util.ArrayList;

import tools.SudokuBoard;


public class SudokuChecker 
{	
	/*public static boolean checkSolution(SudokuBoard sudokuBoard)
	{		
		//int[][] board = sudokuBoard.getBoard();
		
		for (int i = 0; i < 9; i++)
		{
			int[] group = new int[9];
			
			//For each row
			for (int j = 0; j < 9; j++)
			{
				group[j] = board[i][j];
			}
			
			if(!checkGroup(group))
				return false;
			
			//For each column
			group = new int[9];
			for (int j = 0; j < 9; j++)
			{
				group[j] = board[j][i];
			}
			
			if (!checkGroup(group))
				return false;
		}
		
		
		return true;
	}*/
	
	public static boolean checkSolution(SudokuBoard board)
	{
		ArrayList<Integer> group;
		
		for (int i = 0; i < 9; i++)
		{
			//For each row
			group = board.getRowValues(i);
			
			if(!checkGroup(group))
				return false;
			
			//For each column
			group = board.getColumnValues(i);
			
			if(!checkGroup(group))
				return false;
			
			//For each major cell
			group = board.getMajorCellValues(i / 3, i % 3);
			
			if (!checkGroup(group))
				return false;	
		}		
		
		return true;
	}
	
	private static boolean checkGroup(ArrayList<Integer> group)
	{
		boolean[] found = new boolean[9];
		
		if (group.size() != 9)
		{
			System.out.println("Invalid group size.");
			return false;
		}
		
		for (int i = 0; i < 9; i++)
		{
			if (found[group.get(i) - 1] == true)
				return false;
			else
				found[group.get(i) - 1] = true;			
		}
		
		return true;
	}
}
