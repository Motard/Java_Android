package Application;

public class MyCompany 
{
	public static void main(String[] args) 
	{
		Worker machine = new Machine("Apple", "MacBook Pro", 123456);
		
		System.out.println("A maquina trabalhou " + machine.workedHours() + " segundos.");
		
		// Começar a trabalhar...
		machine.startWork();
		
		// Trabalhar...
		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			//TODO mudar isto, isto é feio...
			e.printStackTrace();
		}
		
		// Fim trabalho...
		machine.stopWork();
		
		System.out.println("A maquina trabalhou " + machine.workedHours() + " segundos.");
	}
}
