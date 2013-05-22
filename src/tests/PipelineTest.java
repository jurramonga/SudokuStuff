package tests;

import generation.BacktrackGenerator;

import java.util.ArrayList;

import obfuscation.BasicObfuscator;
import solutions.BacktrackSolver;
import solutions.SudokuChecker;
import tools.SudokuBoard;
import tools.Tools;

/**
 * PipelineTest.java
 * @author Jason Prindle
 *
 * This program is used to test the 'pipeline' features of the SudokuSuite. It will generate, obfuscate, solve, and
 * double-check a number of puzzles.
 */

public class PipelineTest 
{
	public static final int TEST_CASES = 100;
	public static final int SEED = 209647;
	static ArrayList<SudokuBoard> boards;
	
	public static void main(String[] args)
	{
		BacktrackGenerator gen = new BacktrackGenerator(SEED);
		BasicObfuscator obs = new BasicObfuscator(SEED);
		
		long trueStart;
		long start;
		long total;
		
		boards = new ArrayList<SudokuBoard>();
		
		//--- GENERATE BOARDS
		
		System.out.println("Generating boards.");
		trueStart =  System.currentTimeMillis();
		start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.add(gen.generate());
		}
		
		total = System.currentTimeMillis() - start;
		
		System.out.println("Generated " + TEST_CASES + " boards in " + total + "ms.");
		System.out.println("Average time per generation: " + total/(float)TEST_CASES + "ms.");
		System.out.println();

		//--- OBFUSCATE BOARDS
		
		System.out.println("Obfuscating boards.");
		start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.set(i, obs.obfuscate(boards.get(i)));
		}
		
		total = System.currentTimeMillis() - start;
		
		System.out.println("Obfuscated " + TEST_CASES + " boards in " + total + "ms.");
		System.out.println("Average time per obfuscation: " + total/(float)TEST_CASES + "ms.");
		System.out.println();

		//--- SOLVE BOARDS
		
		System.out.println("Solving boards.");
		start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			boards.set(i, BacktrackSolver.findSolutions(boards.get(i)).get(0));
		}
		
		total = System.currentTimeMillis() - start;
		
		System.out.println("Solved " + TEST_CASES + " boards in " + total + "ms.");
		System.out.println("Average time per solution: " + total/(float)TEST_CASES + "ms.");
		System.out.println();

		//--- DOUBLE-CHECK BOARDS
		
		System.out.println("Double-checking boards.");
		start = System.currentTimeMillis();
		
		for (int i = 0; i < TEST_CASES; i++)
		{
			if(!SudokuChecker.checkSolution(boards.get(i)))
			{
				System.out.println("Invalid solution found.");
			}
		}
		
		total = System.currentTimeMillis() - start;
		
		System.out.println("Double-checked " + TEST_CASES + " boards in " + total + "ms.");
		System.out.println("Average time per double-check: " + total/(float)TEST_CASES + "ms.");
		System.out.println();
		
		total = System.currentTimeMillis() - trueStart;
		
		System.out.println("Pipeline tested " + TEST_CASES + " boards in " + total + "ms.");
		System.out.println("Average time per board: " + total/(float)TEST_CASES + "ms.");
	}
}
