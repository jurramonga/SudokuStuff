package tests;

import generation.BacktrackGenerator;
import tools.Tools;

public class BacktrackGenerationTest 
{
	public static void main(String[] args)
	{
		BacktrackGenerator gen = new BacktrackGenerator(25387);
		Tools.printBoard(gen.generate());
	}

}
