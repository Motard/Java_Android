package model;

/**
 * Concrete implementation of {@link Employee} for Managers.
 * 
 * @author ricardosousa
 * */
public class Manager extends Employee
{
	public Manager(int empNum, String name) 
	{
		super(empNum, name);
	}
	
	public Manager(int empNum, String city, String name)
	{
		super(empNum, city, name);
	}

	@Override
	public double calculateSalary() 
	{
		return baseSalary + 1000;
	}
	
	// Overrides toString method.
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder("Manager [");

        builder.append("empName=").append(this.getName())
               .append(", empNum=").append(this.getEmpNum())
               .append(", empCity=").append(this.getCity() + "]");
        
        return builder.toString();
	}
}
