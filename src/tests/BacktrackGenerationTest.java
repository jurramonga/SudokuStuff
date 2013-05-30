package tests;

import tools.Log;
import generation.BacktrackGenerator;

public class BacktrackGenerationTest 
{
	public static final int TEST_CASES = 100;
	public static final int BOARD_SIZE = 16;
	public static final int SEED = 29472;
	
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		
		BacktrackGenerator gen = new BacktrackGenerator(BOARD_SIZE,SEED);
		Log.DEBUG = true;
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			System.out.println("Generating board #" + i);
			gen.generate().print();
			System.out.println();
		}	
		
		System.out.println("Total:" + (System.currentTimeMillis() - start));
	}
}
