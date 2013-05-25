package tests;

import generation.BacktrackGenerator;

import java.util.ArrayList;

import obfuscation.RandomObfuscator;
import tools.Log;
import tools.SudokuBoard;

public class RandomObfuscatorTest 
{
	//SEED Notes:
	//984312, gets stuck generating board #95 making 16x16 boards.
	public static final int SEED = 984312;
	public static final int TEST_CASES = 10;
	
	public static void main(String[] args)
	{		
		ArrayList<SudokuBoard> boards = new ArrayList<SudokuBoard>();
		BacktrackGenerator gen = new BacktrackGenerator(16, SEED);
		
		System.out.println("Generating " + TEST_CASES + " boards.");
		for (int i = 0; i < TEST_CASES; i++)
		{			
			Log.output("Generating board #" + i);
			boards.add(gen.generate());
			//boards.get(i).print();
		}
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.get(i).print();
		}
		
		RandomObfuscator obs = new RandomObfuscator(SEED);
		
		Log.output("Obfuscating " + TEST_CASES + " boards.");
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.set(i, obs.obfuscate(boards.get(i), 0.5f));
			boards.get(i).print();
			Log.output("Puzzle #" + i + " complete.\n");
		}
		
		long end = System.currentTimeMillis();
		long total = end - start;
		
		Log.output("Obfuscated " + TEST_CASES + " puzzles in " + total + "ms (" + total/(float)TEST_CASES + "ms/each).");		
	}
}
