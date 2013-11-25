package Model;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Class for represent one Employee.
 * */
public class Employee implements Worker
{
	private int    empNum;
	private String name;
	private String city;
	private double salary;
	
	private Date startedAt;
	private ArrayList<TimeIntervalNested> intervals;
	
	/**
	 * Creates an instance of {@link Employee}, without arguments.
	 * */
	public Employee()
	{
		this.city = "Lisboa";
		this.intervals = new ArrayList<>();
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
	
	public void setName(String...names) 
	{
		StringBuilder builder = new StringBuilder(50);
		for (int i = 0; i < names.length; i++) 
		{
//			if (i == names.length -1)
//			{
//				this.name += names[i];
//				break;
//			}
			
//			this.name += names[i] + ((i == names.length - 1) ? "" : " ");
			
			builder.append(names[i])
				   .append((i == names.length - 1) ? "" : " ");
		}
		
		this.name = builder.toString();
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
	public boolean startWork() 
	{
		if (isWorking())
			return false;
		
		System.out.println("Employee start work...");
		this.startedAt = new Date();
		return true;
	}
	
	@Override
	public void stopWork() throws UnsupportedOperationException
	{
		if (isWorking())
		{
			this.intervals.add(new TimeIntervalNested(startedAt, new Date()));
			
			this.startedAt = null;
			System.out.println("Employee stop work...");
		}
		else
			throw new UnsupportedOperationException("O empregado ainda nao começou a trabalhar.");
	}
	
	@Override
	public long workedHours()
	{
		long workedHours = 0;
		for (TimeIntervalNested interval : this.intervals) 
		{
			workedHours += interval.duration();
		}
		
		return workedHours + sessionTimeInSeconds();
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
	public boolean isWorking()
	{
		return this.startedAt != null;
	}
	
	@Override
	public String toString() 
	{
//		return "Employee [empName=" + this.getName() + ", empNum=" + this.getEmpNum() + ""
//				+ ", empCity=" + this.getCity() +"]";
		
		// Com o StringBuilder.
		StringBuilder builder = new StringBuilder("Employee [");
		
		// Se nao fosse fluente.
//		builder.append("empName=");
//		builder.append(this.getName());
		
		// Utilização de "Fluent API". (Idioma fluente)
		builder.append("empName=").append(this.getName())
			   .append(", empNum=").append(this.getEmpNum())
			   .append(", empCity=").append(this.getCity() + "]");
		
		return builder.toString();
	}
	
	/*
	@Override
	public boolean equals(Object obj) 
	{
		Employee emp = (Employee) obj;
		
		System.out.println("Conversao para employee.");
		
		return (this.getName().equals(emp.getName())) && (this.getEmpNum() == emp.getEmpNum()) && 
			   (this.getCity().equals(emp.getCity())) && (this.getSalary() == emp.getSalary());
	}
	*/
	
	private class TimeIntervalNested 
	{
		private Date startWorkDate,
					 endWorkDate;
		
		public TimeIntervalNested(Date startWorkDate, Date endWorkDate)
		{
			this.startWorkDate = startWorkDate;
			this.endWorkDate   = endWorkDate;
		}
		
		public long duration()
		{
			DateTime dt1 = new DateTime(startWorkDate);
			DateTime dt2 = new DateTime(endWorkDate);
			
			return new Duration(dt1, dt2).getStandardSeconds();
		}
	}
} // Fim da classe Employee.
