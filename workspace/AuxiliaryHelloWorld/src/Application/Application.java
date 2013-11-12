package Application;

public class Application 
{
	public static void main(String[] args) throws Exception 
	{
		// Testing the WorkerLoggerEntry...
		WorkerLoggerEntry entry = new WorkerLoggerEntry();
		// Start work...
		entry.start();
		System.out.println("Start work...");
		// Do some work...
		Thread.sleep(10000);
		// Stop work...
		entry.stop();
		System.out.println("...End work");
		
		System.out.println("Seconds worked: " + entry.getInterval() + " seconds.");
	}
}