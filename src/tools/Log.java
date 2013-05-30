package tools;

public class Log 
{
	public static boolean DEBUG = true;
	
	public static void output(String output)
	{
		if (DEBUG)
		{
			System.out.println(output);
		}
	}
}
