package solutions;

import java.util.ArrayList;

import tools.SudokuBoard;


public class SudokuChecker 
{	
	static SudokuBoard board;
	
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
	
	public static boolean checkSolution(SudokuBoard oldBoard)
	{
		board = new SudokuBoard(oldBoard);
		ArrayList<Integer> group;
		
		for (int i = 0; i < board.getBoardSize(); i++)
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
			int root = board.getBoardRootSize();
			group = board.getMajorCellValues(i / root, i % root);
			
			if (!checkGroup(group))
				return false;	
		}		
		
		return true;
	}
	
	private static boolean checkGroup(ArrayList<Integer> group)
	{
		int size = board.getBoardSize();
		boolean[] found = new boolean[size];
		
		if (group.size() != size)
		{
			System.out.println("Invalid group size.");
			return false;
		}
		
		for (int i = 0; i < size; i++)
		{
			if (found[group.get(i) - 1] == true)
				return false;
			else
				found[group.get(i) - 1] = true;			
		}
		
		return true;
	}
}
