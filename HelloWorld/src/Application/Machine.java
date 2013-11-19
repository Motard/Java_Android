package Application;

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
	
	public void startWork()
	{
		System.out.println("Machine start working...");
		
		// TODO cuidado, porque pode já estar a trabalhar. 
		
		this.startedAt = new Date(); // Data actual.
	}
	
	@Override
	public void stopWork()
	{
		// TODO verificar se está a trabalhar.
		
		Date now = new Date();
		
		DateTime startDate = new DateTime(this.startedAt);
		DateTime endDate   = new DateTime(now);
		
		Duration duration = new Duration(startDate, endDate);
		
		// TODO alterar para getStandartHours()
		this.hoursWorked = duration.getStandardSeconds();
		
		this.startedAt = null;
		
		System.out.println("Machine stop working...");
	}
	
	@Override
	public long workedHours()
	{
		// TODO adicionar as horas de trabalho actuais.
		return this.hoursWorked;
	}
	
	@Override
	public String toString() 
	{
		return "Machine [sku=" + this.getSku() + ", manufacturer=" + this.getManufacturer() +", "
			 + "model=" + this.getModel() + "]";
	}
	
}
