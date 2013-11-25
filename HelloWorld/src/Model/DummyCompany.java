package Model;

public class DummyCompany 
{
	private Worker[] workers;
	
	public DummyCompany(Worker...workers)
	{
		this.workers = workers;
	}
	
	public int getNumberOfWorkers()
	{
		return this.workers.length;
	}
	
//	private static class DummyWorker implements Worker
//	{
//		@Override
//		public boolean startWork() { return false; }
//		@Override
//		public void stopWork() throws UnsupportedOperationException {}
//		@Override
//		public long workedHours() { return 0; }
//		@Override
//		public boolean isWorking() { return false; }
//	}
	
	// Só para testar, não se faz...
	public static void main(String[] args)
	{
		Employee ricardo = new Employee(1234, "Ricardo");
		DummyCompany company = new DummyCompany(ricardo, 
												new Employee(1235, "Lara"),
												// Classe anonima.
												new Worker() 
												{	
													@Override
													public long workedHours() { return 0; }
													@Override
													public void stopWork() {}
													@Override
													public boolean startWork() { return false; }
													@Override
													public boolean isWorking() {return false; }
												},
												new Machine("Apple", "iMac", 12345));
		
		System.out.println("Numero de workers na company: " + company.getNumberOfWorkers());
		
		System.out.println("Antes: " + ricardo.getName());
		ricardo.setName("Ricardo", "Marinho", "...", "Sousa");
		System.out.println("Depois: " + ricardo.getName());
		System.out.println();
		
		// Arrays bidimensionais
		Worker[][] workers = new Worker[2][2];
		workers[0][0] = new Employee(123, "Ricardo");
		workers[0][1] = new Employee(124, "Lara");
		workers[1][0] = new Machine("Apple", "iMac", 123);
		workers[1][1] = new Machine("Epson", "sdsd", 1235);
		
		for (int row = 0; row < workers.length; row++) 
		{
			for (int col = 0; col < workers.length; col++)
			{
				System.out.println(workers[row][col]);
			}
		}
	}
}
