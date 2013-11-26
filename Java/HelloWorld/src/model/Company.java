package model;

/**
 * Dummy class for represents one Company.
 * 
 * @author ricardosousa.
 * */
public class Company
{
	private Worker[] workers;
    
	/**
	 * Creates an instance of {@link Company}, with arguments.
	 * @param workers An array of workers.
	 * */
    public Company(Worker...workers)
    {
        this.workers = workers;
    }
    
    /**
     * Get the actual number of workers in the company.
     * @return The number of workers.
     * */
    public int getNumberOfWorkers()
    {
        return this.workers.length;
    }
}
