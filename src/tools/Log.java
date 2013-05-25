package tools;

public class Log 
{
	public static boolean DEBUG = false;
	
	public static void output(String output)
	{
		if (DEBUG)
		{
			System.out.println(output);
		}
	}
}
