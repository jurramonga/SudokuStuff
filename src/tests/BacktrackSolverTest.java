package tests;

import io.SudokuReader;

import java.util.ArrayList;

import solutions.BacktrackSolver;
import tools.SudokuBoard;
import tools.Tools;

public class BacktrackSolverTest 
{
	public static void main(String[] args)
	{
		SudokuBoard input = SudokuReader.readFromFile("res\\boards\\obsPuzzle");
		System.out.println("## Input Puzzle:");
		Tools.printBoard(input);
		System.out.println();
		
		ArrayList<SudokuBoard> solutions = BacktrackSolver.findSolutions(input);		
		
		if (solutions != null)
		{
			System.out.println("## Solutions found: " + solutions.size());
			for(int i = 0; i < solutions.size(); i++)
			{
				System.out.println("## Solution #" + (i + 1) + ":\n");
				Tools.printBoard(solutions.get(i));
				System.out.println();
			}
		}
	}
}
