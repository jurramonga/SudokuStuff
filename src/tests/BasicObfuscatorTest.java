package tests;

import generation.BacktrackGenerator;

import java.util.ArrayList;

import obfuscation.BasicObfuscator;
import tools.SudokuBoard;

public class BasicObfuscatorTest 
{
	public static final int BOARD_SIZE = 9;
	public static final int SEED = 2545;
	public static final int TEST_CASES = 100;
	
	public static void main(String[] args)
	{
		ArrayList<SudokuBoard> boards = new ArrayList<SudokuBoard>();
		BacktrackGenerator gen = new BacktrackGenerator(BOARD_SIZE, SEED);
		
		System.out.println("Generating " + TEST_CASES + " boards.");
		for (int i = 0; i < TEST_CASES; i++)
		{			
			System.out.println("Generating board #" + i);
			boards.add(gen.generate());
			//boards.get(i).print();
			//Tools.printBoard(boards.get(i));
		}		
		
		BasicObfuscator obs = new BasicObfuscator(SEED);
		
		System.out.println("Obfuscating " + TEST_CASES + " boards.");
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.set(i, obs.obfuscate(boards.get(i), 0.5f));
			boards.get(i).print();
			System.out.println("Puzzle #" + i + " complete.");
		}
		
		long end = System.currentTimeMillis();
		long total = end - start;
		
		System.out.println("Obfuscated " + TEST_CASES + " puzzles in " + total + "ms (" + total/(float)TEST_CASES + "ms/each).");
	}
}
