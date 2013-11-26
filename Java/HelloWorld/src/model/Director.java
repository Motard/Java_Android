package model;

/**
 * Concrete implementation of {@link Employee} for Directors.
 * 
 * @author ricardosousa
 * */
public class Director extends Employee
{
	public Director(int empNum, String name) 
	{
		super(empNum, name);
	}
	
	public Director(int empNum, String city, String name)
	{
		super(empNum, city, name);
	}

	@Override
	public double calculateSalary() 
	{
		return baseSalary + 2000;
	}
	
	/**
	 * Simulates the fire of all employees. It's only a demo 
	 * method for show that this method can only be used by directors.
	 * */
	public void firesEveryone()
	{
		System.out.println("The " + getName() + ", fires everyone.");
	}
	
	// Overrides toString method.
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder("Director [");

        builder.append("empName=").append(this.getName())
               .append(", empNum=").append(this.getEmpNum())
               .append(", empCity=").append(this.getCity() + "]");
        
        return builder.toString();
	}
}
