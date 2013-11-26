package model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Concrete implementation of {@link Worker} for Machines.
 * 
 * @author ricardosousa
 * */
public class Machine implements Worker
{
	// Final instance fields.
    private final String manufacturer;
    private final String model;
    // Non-final instance fields.
    private int  sku;
    private long secondsWorked;
    private Date startedAt;
    
    /**
     * Creates an instance of {@link Machine}, with arguments.
     * @param manufacturer The name of the machine's manufacturer.
     * @param model		   The machine's model.
     * @param sku 		   The machine's SKU number.
     * */
    public Machine(String manufacturer, String model, int sku)
    {
            this.manufacturer = manufacturer;
            this.model        = model;
            this.sku          = sku;
    }

    /**
     * Getter method for SKU number.
     * @return The machine's SKU number.
     * */
    public int getSku() { return sku; }
    
    /**
     * Setter method for SKU number.
     * @param sku The new machine's SKU number.
     * */
    public void setSku(int sku) { this.sku = sku; }
    
    /**
     * Getter method for manufacturer name.
     * @return The machine's manufacturer name.
     * */
    public String getManufacturer() { return manufacturer; }
    
    /**
     * Getter method for model name.
     * @return The machine's model name.
     * */
    public String getModel() { return model; }
    
	@Override
	public boolean startWork() 
	{
		if (this.isWorking())
            return false;
    
	    this.startedAt = new Date();
	    System.out.println("Machine start working...");
	    return true;
	}

	@Override
	public void stopWork() throws UnsupportedOperationException
	{
		if (!this.isWorking())
			throw new UnsupportedOperationException("The machine has not yet begun to work..");
        
		this.secondsWorked += sessionTimeInSeconds();     
        this.startedAt = null;
        System.out.println("Machine stop working...");        
	}
	
	/**
	 * Helper method for calculate the session time in seconds.
	 * @return The session time, in seconds.
	 * */
	private long sessionTimeInSeconds()
    {
        if (!isWorking())
                return 0;
                                
        Date now = new Date();
        DateTime startDate = new DateTime(this.startedAt);
        DateTime endDate   = new DateTime(now);

        return new Duration(startDate, endDate).getStandardSeconds();
    }

	@Override
	public long workedSeconds() 
	{
		return this.secondsWorked + sessionTimeInSeconds();
	}

	@Override
	public boolean isWorking() 
	{
		return this.startedAt != null;
	}
	
	@Override
	public String toString() 
	{
		// Example without the StringBuilder.
		return "Machine [sku=" + this.getSku() + ", manufacturer=" + this.getManufacturer() +", "
               + "model=" + this.getModel() + "]";
	}
}
