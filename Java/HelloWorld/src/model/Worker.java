package model;

/**
 * Specifies the contract to be implemented by all workers.
 * 
 * @author ricardosousa
 * */
public interface Worker 
{
	/**
	 * Tell worker to start working.
	 * @return True if start work, else returns False.
	 * */
	/*
	 *  Example with the "success/failure" technique.
	 *  The keyword "abstract" is not obligatory when defining methods in interfaces.
	 */
	abstract boolean startWork();
	
	/**
	 * Tell worker to stop working.
	 * @throws UnsupportedOperationException
	 * */
	// Example of one operation "except oriented".
	void stopWork() throws UnsupportedOperationException;
	
	/**
	 * Get the time of the worker work, in seconds.
	 * @return The time, in seconds, of the work time.
	 * */
	// Idempotence technique example.
	long workedSeconds();
	
	/**
	 * Verify if the work is working.
	 * @return True if the work is working, else returns False.
	 * */
	// Idempotence technique example.
	boolean isWorking();
}