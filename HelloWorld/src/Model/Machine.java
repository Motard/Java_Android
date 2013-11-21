package Model;

import java.util.Date;

import org.joda.time.*;

/**
 * Class for define machines.
 * 
 * @author ricardosousa
 * */
public class Machine implements Worker
{
	// private fields.
	private String manufacturer;
	private String model;
	private int    sku;
	
	private long hoursWorked;
	private Date startedAt;
	
	public Machine(String manufacturer, String model, int sku)
	{
		this.manufacturer = manufacturer;
		this.model 		  = model;
		this.sku 		  = sku;
	}
	
	/*
	 * Getters and Setters.
	 * */
	public int getSku() 
	{
		return sku;
	}
	
	public void setSku(int sku)
	{
		this.sku = sku;
	}
	
	public String getManufacturer() 
	{
		return manufacturer;
	}
	
	public String getModel() 
	{
		return model;
	}
	
	public boolean startWork()
	{
		// Utilização de lógica invertida (testar primeiro a falha)
		if (this.isWorking())
			return false;
		
		this.startedAt = new Date(); // Data actual.
		System.out.println("Machine start working...");
		return true;
	}
	
	@Override
	public void stopWork() throws UnsupportedOperationException
	{
		if (this.isWorking())
		{
			this.hoursWorked += sessionTimeInSeconds();
			
			this.startedAt = null;
			System.out.println("Machine stop working...");
		}
		else
			throw new UnsupportedOperationException("A maquina ainda nao começou a trabalhar.");
	}
	
	@Override
	public boolean isWorking()
	{
		return startedAt != null;
	}
	
	@Override
	public long workedHours()
	{
		return this.hoursWorked + sessionTimeInSeconds();
	}
	
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
	public String toString() 
	{
		return "Machine [sku=" + this.getSku() + ", manufacturer=" + this.getManufacturer() +", "
			 + "model=" + this.getModel() + "]";
	}
}
