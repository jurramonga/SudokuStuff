package tools;

import java.util.ArrayList;

public class SudokuBoard 
{
	int[][] board;
	
	public SudokuBoard()
	{
		board = new int[9][9];
	}
	
	public SudokuBoard(SudokuBoard board)
	{
		this.board = new int[9][9];
		
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.board[j][i] = board.getCell(j,i);
			}
		}
	}
	
	public SudokuBoard(int[][] board)
	{
		this.board = new int[9][9];
		
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.board[j][i] = board[j][i];
			}
		}
	}
	
	public int getCell(int position)
	{
		return board[position/9][position%9];
	}
	
	public int getCell(int row, int column)
	{
		return board[row][column];
	}
	
	public void setCell(int position, int value)
	{
		board[position / 9][position % 9] = value;
	}
	
	public void setCell(int row, int column, int value)
	{
		board[row][column] = value;
	}
	
	public ArrayList<Integer> getRowValues(int row)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++)
		{
			list.add(getCell(row,i));
		}
		
		return list;
	}
	
	public ArrayList<Integer> getRowValuesBeforePosition(int position)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int row = position / 9;
		int column = position % 9;
		
		for (int i = 0; i < column; i++)
		{
			list.add(getCell(row, i));
		}		
		
		return list;
	}
	
	public ArrayList<Integer> getColumnValues(int column)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++)
		{
			list.add(getCell(i,column));
		}
		
		return list;
	}
	
	public ArrayList<Integer> getColumnValuesBeforePosition(int position)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		int row = position / 9;
		int column = position % 9;
		
		for (int i = 0; i < row; i++)
		{
			list.add(getCell(i,column));
		}
		
		return list;
	}
	
	/**
	 * Returns the numbers present in the selected Major Cell
	 * @param majorRow 3-row subsection of board
	 * @param majorColumn 3-column subsection of board
	 * @return
	 */
	public ArrayList<Integer> getMajorCellValues(int majorRow, int majorColumn)
	{
		if (majorRow < 0 || majorRow > 2)
		{
			System.out.println("Invalid majorRow value: " + majorRow + ".");
			return null;
		}
		
		if (majorColumn < 0 || majorColumn > 2)
		{
			System.out.println("Invalid majorColumn value: " + majorColumn + ".");
			return null;
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				int cellValue = getCell(majorRow * 3 + i, majorColumn * 3 + j);
				list.add(cellValue);
			}
		}
		
		return list;
	}
	
	public ArrayList<Integer> getMajorCellValuesBeforePosition(int position)
	{
		int row = position / 9;
		int column = position % 9;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//Find major cell
		int majorRow = row / 3;
		int majorColumn = column / 3;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if ((majorRow*3+i)*9+(majorColumn*3+j) < position)
				{
					list.add(getCell(majorRow*3+i,majorColumn*3+j));
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Returns the values in the row, column, and major cell of a position.
	 * @param positon
	 * @return
	 */
	public ArrayList<Integer> getConnectedValues(int position)
	{
		int row = position / 9;
		int column = position % 9;
		
		ArrayList<Integer> list = this.getRowValues(row);
		list.addAll(this.getColumnValues(column));
		list.addAll(this.getMajorCellValues(row / 3, column / 3));
		
		return list;
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	
	public void setBoard(int[][] board)
	{
		this.board = board;
	}
	
	public void reset()
	{
		for (int i = 0; i< 9 ;i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.board[j][i] = 0;
			}
		}
	}

}
