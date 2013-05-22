package tests;

import java.util.ArrayList;

import generation.BacktrackGenerator;
import obfuscation.BasicObfuscator;
import obfuscation.RandomObfuscator;
import tools.SudokuBoard;
import tools.Tools;

public class BasicObfuscatorTest 
{
	public static final int SEED = 25873;
	public static final int TEST_CASES = 1000;
	
	public static void main(String[] args)
	{
		ArrayList<SudokuBoard> boards = new ArrayList<SudokuBoard>();
		BacktrackGenerator gen = new BacktrackGenerator(SEED);
		
		System.out.println("Generating " + TEST_CASES + " boards.");
		for (int i = 0; i < TEST_CASES; i++)
		{			
			boards.add(gen.generate());
			//Tools.printBoard(board);
		}		
		
		BasicObfuscator obs = new BasicObfuscator(SEED);
		
		System.out.println("Obfuscating " + TEST_CASES + " boards.");
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			obs.obfuscate(boards.get(i));
			System.out.println("Puzzle #" + i + " complete.");
		}
		
		long end = System.currentTimeMillis();
		long total = end - start;
		
		System.out.println("Obfuscated " + TEST_CASES + " puzzles in " + total + "ms (" + total/(float)TEST_CASES + "ms/each).");
	}
}
