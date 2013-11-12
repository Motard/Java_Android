package Application;

/**
 * Class for represent one Employee.
 * */
public class Employee
{
	private int    empNum;
	private String name;
	private String city;
	private double salary;
	
	/**
	 * Creates an instance of {@link Employee}, without arguments.
	 * */
	public Employee()
	{
		this.city = "Lisboa";
	}
	
	/**
	 * Creates an instance of {@link Employee}, with arguments.
	 * 
	 * @param empNum The Number of the {@link Employee}
	 * @param name   The name of the {@link Employee}
	 * */
	public Employee(int empNum, String name)
	{
		// Already have the information of the city, This calls the default .ctor.
		this();
		
		this.empNum = empNum;
		this.name   = name;
	}
	
	/**
	 * Creates an instance of {@link Employee}, with arguments.
	 * 
	 * @param empNum The Number of the {@link Employee}
	 * @param name   The name of the {@link Employee}
	 * @param city 	 The city of the {@link Employee}
	 * */
	public Employee(int empNum, String name, String city)
	{
//		this.empNum = empNum;
//		this.name   = name;
		this(empNum, name);
		this.city = city;
	}
	
	/*
	 * Getters/Setters methods.
	 * */
	public int getEmpNum() 
	{
		return empNum;
	}
	
	public void setEmpNum(int empNum) 
	{
		this.empNum = empNum;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	// setName Overload 
	public void setName(String firstName, String lastName)
	{
		this.name = firstName + " " + lastName;
	}
	
	public String getCity() 
	{
		return city;
	}
	
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public double getSalary()
	{
		return this.salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	@Override
	public String toString() 
	{
		return "Employee [empName=" + this.getName() + " empNum=" + this.getEmpNum() + ""
				+ " empCity=" + this.getCity() +"]";
	}
}
