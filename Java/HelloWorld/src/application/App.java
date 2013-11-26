package application;

import model.Company;
import model.Director;
import model.Employee;
import model.Machine;
import model.Manager;
import model.Worker;

/**
 * Entry point class of the HelloWorld project.
 * */
public class App 
{
	public static void main(String[] args) 
	{
		// Example of two worker working.
		Machine worker1 = new Machine("Apple", "iMac", 1234);
		worker1.startWork();
		try { Thread.sleep(5000); } catch(InterruptedException _){}
		worker1.stopWork();
		System.out.println("The " + worker1 + ", works " + worker1.workedSeconds() + " seconds.");
		
		Employee worker2 = new Manager(123, "Ricardo");
		worker2.startWork();
		try { Thread.sleep(5000); } catch(InterruptedException _){}
		worker2.stopWork();
		System.out.println("The " + worker2 + ", works " + worker2.workedSeconds() + " seconds.");
		// Start work again, to see that the intervals times are working.
		worker2.startWork();
		try { Thread.sleep(5000); } catch(InterruptedException _){}
		worker2.stopWork();
		System.out.println("The " + worker2 + ", works " + worker2.workedSeconds() + " seconds."); // Should be 10 seconds.
		
		// See the change of the name.
		worker2.setName("Ricardo", "Sousa");
		System.out.println("The new name of the " + worker2 + " is " + worker2.getName() + "."); // Should be "Ricardo Sousa".
		
		// Example of anonymous classes.
		Company company = new Company(worker1,
									  worker2,
									  new Director(12345, "Lara"),
									  // Anonymous class.
									  new Worker() {
										@Override
										public long workedSeconds() { return 0; }
										
										@Override
										public void stopWork() throws UnsupportedOperationException {}
										
										@Override
										public boolean startWork() { return false; }
										
										@Override
										public boolean isWorking() { return false; }
									});
		
		System.out.println("The number of workers in the Company is " + company.getNumberOfWorkers()); // Should be 4.
	}
}
