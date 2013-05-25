import generation.BacktrackGenerator;
import io.SudokuWriter;


public class Main 
{
	public static final int SEED = 984312;
	public static final int BOARD_SIZE = 16;
	public static final int TEST_CASES = 100;
	
	public static void main(String[] args)
	{	
		BacktrackGenerator gen = new BacktrackGenerator(BOARD_SIZE, SEED);
		
		for (int i = 0; i < TEST_CASES; i++)
		{			
			System.out.println("Generating puzzle #" + i + ".");
			SudokuWriter.writeToFile(gen.generate(), "res\\boards\\main\\" + i + ".sdkp");			
		}
		
		
		//write(board, "gen");
		
		
	}
}
