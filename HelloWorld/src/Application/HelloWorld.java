package Application;

import java.util.ArrayList;

/**
 * This is an JAVA Hello World.
 * This shows a basic anatomy of a program in JAVA.
 * */
public class HelloWorld 
{
	public static void main(String[] args) throws Exception
	{
		/*
		if (args.length != 0)
			System.out.println("Hello World " + args[0]);
		*/
		
		//String empty;
		//empty = "";
		//String empty = new String("");
		//String noString = null;
		
		/*
		System.out.println("Foram passados " + args.length + " parametros.");
		  
        
		String str = "Hello World";
		// Ternary operator.
		str = (args.length > 0)? str + " " + args[0] : str;
		
		System.out.println(str);
		*/
		
		/*
		// Criar objecto do tipo empregado utilizando o constructor por omissão.
		Employee emp = new Employee();
		// Atribuir valores aos campos do empregado, utilizando os setters methods.
		emp.setEmpNum(1234);
		emp.setName("Ricardo Sousa");
		emp.setCity("Lisboa");
		*/
		
		// Criar objecto do tipo empregado utilizando o contructor com parâmetros.
//		Employee emp = new Employee(1234, "Ricardo Sousa", "Lisboa");
		
		// String name = emp.getName();
		// System.out.println("Hello " + name + ", from " + emp.getCity());
		
//		System.out.println("Hello " + emp.toString());// Não é necessário o .toString()
//		
//		Object emp2 = new Employee(1234, "Ricardo Sousa", "Lisboa");
//		System.out.println("Hello " + emp2.toString());
		
//		OtherDirector dir = new OtherDirector(123, "Ricardo Sousa", "Lisboa"); // Não existe .ctor 
//		OtherDirector dir = new Employee(1234, "Ricardo Sousa", "Lisboa"); // não compila
//		OtherDirector dir = new OtherDirector();
//		System.out.println(dir.getCity());
		
//		Director dir2 = new Director();
//		System.out.println(dir2.getCity());
//		System.out.println(dir2.getName());
		
		Employee[] employeesList;
		employeesList = new Employee[4];
		employeesList[0] = new Employee(1234, "Ricardo");
		employeesList[2] = new Director(12345, "Lara");
		employeesList[3] = new Employee(123456, "Pedro");
//		System.out.println(employeesList[1].toString());
//		((Director)employeesList[1]).firesEveryone();
		
//		int[] arr = new int[3];
//		
//		int i, count = 0;
//		for(i = 0; i < employeesList.length; ++i)
//		{
//			if (employeesList[i] != null)
//			{
//				System.out.println(employeesList[i].getName());
//				count++;
//			}
//				
//		}
		
//		for(Employee emp : employeesList)
//		{
//			System.out.println(emp);
//		}
		
		// Versão alternativa.
		int ct = 0;
		for (Employee employee : employeesList) 
		{
			if (employee == null)
				continue;
			
			System.out.println(employee);
			ct++;
		}
		System.out.println("Existem: " + ct + " empregados.\n");
		
//		System.out.println(i++);
//		System.out.println(i);
//		System.out.println("Existem: " + count);		
		
		Machine[] machinesList = new Machine[4];
		machinesList[0] = new Machine("Epson", "EMP-75", 1000);
		machinesList[1] = new Machine("Apple", "2010 iMac", 12345);
		
		ct = 0;
		for (Machine machine : machinesList) 
		{
			if (machine == null)
				continue;
			
			System.out.println(machine);
			ct++;
		}
		System.out.println("Existem: " + ct + " máquinas.\n");
		
		Object[] objsList = new Object[8];
		objsList[0] = new Employee(1234, "Ricardo");
		objsList[2] = new Director(12345, "Lara");
		objsList[3] = new Employee(123456, "Pedro");
		objsList[6] = new Machine("Epson", "EMP-75", 1000);
		objsList[7] = new Machine("Apple", "2010 iMac", 12345);
		
		int countEmps    = 0,
			countMachs   = 0,
			countDirs    = 0,
			countWorkers = 0;		
		for (Object obj : objsList)
		{
			if (obj == null)
				continue;
			
			System.out.println(obj);
			
			//obj.startWork(); // Assim não dá.
			
			// Detectar o tipo por introspecção.
			
			// Código feio, epa nao...
			if (obj instanceof Employee) 
			{
				((Employee) obj).startWork();
				countEmps++;
			}
			if (obj instanceof Machine)
			{
				((Machine) obj).startWork();
				countMachs++;
			}
			// fim código feio.
			
			if (obj instanceof Director) countDirs++;
			
			if (obj instanceof Worker) countWorkers++;
		}
		
		System.out.println("Existem: " + countEmps + " empregados dos quais " + countDirs + " director(es), "
						  + countMachs + " máquinas e " + countWorkers + " trabalhadores.\n");	
		
		/*
		Worker[] workersList = new Worker[8];
		workersList[0] = new Employee(1234, "Ricardo");
		workersList[2] = new Director(12345, "Lara");
		workersList[3] = new Employee(123456, "Pedro");
		workersList[6] = new Machine("Epson", "EMP-75", 1000);
		workersList[7] = new Machine("Apple", "2010 iMac", 12345);
		
		countWorkers = 0;		
		for (Worker w : workersList)
		{
			if (w == null)
				continue;
			
			System.out.println(w);
			w.startWork();
			countWorkers++;
		}
		System.out.println("Existem: " + countWorkers + " trabalhadores.");		
		*/
		
		
		
		
		// With ArrayList<E>
		ArrayList<Worker> workersArrayList = new ArrayList<Worker>();
		workersArrayList.add(new Employee(1234, "Ricardo"));
		workersArrayList.add(new Director(12345, "Lara"));
		workersArrayList.add(new Machine("Epson", "EMP-75", 1000));
		workersArrayList.add(new Machine("Apple", "2010 iMac", 12345));
		
		Employee emp = new Employee(123456, "Pedro");
		workersArrayList.add(emp);
		
//		workersArrayList.add(new Employee(123456, "Pedro"));
		
		
		countWorkers = 0;
		for (Worker worker : workersArrayList)
		{
			worker.startWork();
			countWorkers++;
		}
		
		// Utilização problemática do contains(...)
//		System.out.println("Tens o Pedro? " + workersArrayList.contains(new Employee(123456, "Pedro")));
		
		System.out.println("Existem: " + countWorkers + " trabalhadores. Tamanho: " + workersArrayList.size());	
	}
}