package tools;

public class Tools 
{
	
	/*public void resetBoard(SudokuBoard board)
	{
		board = new int[9][9];
		
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				board[j][i] = 0;
			}
		}
	}*/
	
	public static void printBoard(SudokuBoard board)
	{
		Tools.printBoard(board.getBoard());
	}
	
	public static void printBoard(int[][] board)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
