package tools;

import java.util.ArrayList;

public class SudokuBoard 
{
	int[][] board;
	
	int boardSize;
	
	public SudokuBoard()
	{
		boardSize = 9;
		board = new int[boardSize][boardSize];
	}
	
	public SudokuBoard(int boardSize)
	{
		//Check that board size is valid (a squared integer greater than 0)   Ex: 1, 4, 9, 16, 25...
		if (Math.pow(Math.sqrt(boardSize), 2) == (double)boardSize)
		{
			this.boardSize = boardSize;
			this.board = new int[boardSize][boardSize];
		}
		else
		{
			System.out.println("SudokuBoard failed to initiate. Invalid board size (must be a squared integer greater than 0.");
		}
	}
	
	public SudokuBoard(SudokuBoard board)
	{
		int oldSize = board.getBoardSize();
		this.setBoardSize(oldSize);
		this.board = new int[oldSize][oldSize];
		
		for (int i = 0; i < boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				this.board[j][i] = board.getCell(j,i);
			}
		}
	}
	
	public int getBoardSize()
	{
		return boardSize;
	}
	
	public void setBoardSize(int size)
	{
		if (Math.pow(Math.sqrt(size), 2) == (double)size)
		{
			this.boardSize = size;
		}
		else
		{
			System.out.println("ERROR: Invalid board size (must be a squared integer greater than 0.");
		}
	}
	
	public int getBoardRootSize()
	{
		return (int)Math.sqrt(boardSize);
	}
	
	public int getCell(int position)
	{
		return board[position/boardSize][position%boardSize];
	}
	
	public int getCell(int row, int column)
	{
		return board[row][column];
	}
	
	public void setCell(int position, int value)
	{
		board[this.getRow(position)][this.getColumn(position)] = value;
	}
	
	public void setCell(int row, int column, int value)
	{
		board[row][column] = value;
	}
	
	public int getRow(int position)
	{
		return position / boardSize;
	}
	
	public int getColumn(int position)
	{
		return position % boardSize;
	}
	
	public ArrayList<Integer> getRowValues(int row)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < boardSize; i++)
		{
			list.add(getCell(row,i));
		}
		
		return list;
	}
	
	public ArrayList<Integer> getRowValuesBeforePosition(int position)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int row = this.getRow(position);
		int column = this.getColumn(position);
		
		for (int i = 0; i < column; i++)
		{
			list.add(getCell(row, i));
		}		
		
		return list;
	}
	
	public ArrayList<Integer> getColumnValues(int column)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < boardSize; i++)
		{
			list.add(getCell(i,column));
		}
		
		return list;
	}
	
	public ArrayList<Integer> getColumnValuesBeforePosition(int position)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		int row = this.getRow(position);
		int column = this.getColumn(position);
		
		for (int i = 0; i < row; i++)
		{
			list.add(getCell(i,column));
		}
		
		return list;
	}
	
	/**
	 * Returns the numbers present in the selected Major Cell
	 * 
	 */
	public ArrayList<Integer> getMajorCellValues(int majorRow, int majorColumn)
	{
		int root = this.getBoardRootSize();
		
		if (majorRow < 0 || majorRow > root - 1)
		{
			System.out.println("Invalid majorRow value: " + majorRow + ".");
			return null;
		}
		
		if (majorColumn < 0 || majorColumn > root - 1)
		{
			System.out.println("Invalid majorColumn value: " + majorColumn + ".");
			return null;
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < root; i++)
		{
			for (int j = 0; j < root; j++)
			{
				int cellValue = getCell(majorRow * root + i, majorColumn * root + j);
				list.add(cellValue);
			}
		}
		
		return list;
	}
	
	public ArrayList<Integer> getMajorCellValuesBeforePosition(int position)
	{
		int row = this.getRow(position);
		int column = this.getColumn(position);
		int root = this.getBoardRootSize();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//Find major cell
		int majorRow = row / root;
		int majorColumn = column / root;
		
		for (int i = 0; i < root; i++)
		{
			for (int j = 0; j < root; j++)
			{
				if ((majorRow*root+i)*this.getBoardSize()+(majorColumn*root+j) < position)
				{
					list.add(getCell(majorRow*root+i,majorColumn*root+j));
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
		int row = this.getRow(position);
		int column = this.getColumn(position);
		int root = this.getBoardRootSize();
		
		ArrayList<Integer> list = this.getRowValues(row);
		list.addAll(this.getColumnValues(column));
		list.addAll(this.getMajorCellValues(row / root, column / root));
		
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

	/**
	 * Checks if the board is entirely filled (no zeroes).
	 * @return
	 */
	public boolean isFilled()
	{
		for (int i = 0; i < this.getBoardSize(); i++)
		{
			for (int j = 0; j < this.getBoardSize(); j++)
			{
				if (this.getCell(j, i) == 0)
					return false;
			}
		}
		
		return true;
	}
	
	public void reset()
	{
		for (int i = 0; i < boardSize ;i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				this.board[j][i] = 0;
			}
		}
	}
	
	@Override
	public String toString()
	{
		String string = new String();
		
		for (int i = 0; i < boardSize ;i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				string += this.getCell(j, i);
				string += "\t";
			}
			string += "\n";
		}
		
		return string;
		
	}
	
	public void print()
	{
		System.out.print(this.toString());
	}
}
