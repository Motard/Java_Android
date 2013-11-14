package Application;

/**
 * Class for define machines.
 * 
 * @author ricardosousa
 * */
public class Machine 
{
	// private fields.
	private String manufacturer;
	private String model;
	private int    sku;
	
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
		// Dummy
	}
	
	@Override
	public String toString() 
	{
		return "Machine [sku=" + this.getSku() + ", manufacturer=" + this.getManufacturer() +", "
			 + "model=" + this.getModel() + "]";
	}
	
}
