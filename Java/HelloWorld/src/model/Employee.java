package model;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Abstract class for represent employees. Also, implements the {@link Worker} 
 * interface.
 * 
 * @author ricardosousa
 * */
public abstract class Employee implements Worker
{
	// Static variables.
	// Saves the current employee number auto-generated.
	private static int currentEmpNum;	
	// protected variable, can only be see by derived classes. It's final and static because it's an constant for all employees.
	protected final static double baseSalary = 500;
	
	// Final instance variables.
	private final int 	 empNum;
    // Non-final instance variables.
	private String name;
    private String city;
    private Date startedAt;
    private ArrayList<TimeIntervalNested> intervals = new ArrayList<>();

    /**
     * Creates an instance of {@link Employee}, with arguments.
     * 
     * @param empNum The Number of the {@link Employee}.
     * @param name   The name of the {@link Employee}.
     * */
    public Employee(int empNum, String name)
    {
    	// Default value for city.
        this.city 	= "Lisbon";
        this.name   = name;
        this.empNum = empNum;
    }
    
    /**
     * Creates an instance of {@link Employee}, with arguments.
     * 
     * @param empNum The Number of the {@link Employee}
     * @param name   The name of the {@link Employee}
     * @param city   The city of the {@link Employee}
     * */
    public Employee(int empNum, String city, String name)
    {
            this(empNum, name);
            this.city = city;
    }
    
    /**
     * Calculates the employee's salary.
     * @return The calculated salary.
     * */
    // Abstract method, must be implemented by all Employee's derived classes.
    public abstract double calculateSalary();
    
    /**
     * Factory method for generate an employee number.
     * @return The new auto-generated employee number.
     * */
    public static int generateEmpNum()
    {
    	return ++currentEmpNum;
    }
    
    /**
     * Getter method for the employee number.
     * @return The employee number.
     * */
    public int getEmpNum() { return empNum; }

    /**
     * Getter method for the employee name.
     * @return The employee name.
     * */
    public String getName() { return name; }
    
    /**
     * Setter method for the employee name.
     * @param names An array with the employees names.
     * */
    // Example of variable arguments.
    public void setName(String...names)
    {
    	// Example of usage of the StringBuilder to construct the name of the employee.
        StringBuilder builder = new StringBuilder(50);
        for (int i = 0; i < names.length; i++) 
        {                    
            builder.append(names[i])
                   .append((i == names.length - 1) ? "" : " ");
        }
        
        this.name = builder.toString();
    }
    
    /**
     * Getter method for the employee city.
     * @return The employee city.
     * */
    public String getCity() { return city; }
    
    /**
     * Setter method for the employee city.
     * @param city The new city.
     * */
    public void setCity(String city) { this.city = city; }
        
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
    	if (!isWorking())
    		throw new UnsupportedOperationException("The " + getName() + ", has not yet begun to work.");
        
        this.intervals.add(new TimeIntervalNested(startedAt, new Date()));
        this.startedAt = null;
        System.out.println("Employee stop work..."); 
    }
    
    @Override
    public long workedSeconds()
    {
        long workedSeconds = 0;
        
        for (TimeIntervalNested interval : this.intervals) 
        	workedSeconds += interval.getSecondsDuration();
        
        return workedSeconds + sessionTimeInSeconds();
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
    public boolean isWorking()
    {
            return this.startedAt != null;
    }
    
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder("Employee [");

        // "Fluent API" Utilization with the StringBuilder.
        builder.append("empName=").append(this.getName())
               .append(", empNum=").append(this.getEmpNum())
               .append(", empCity=").append(this.getCity() + "]");
        
        return builder.toString();
    }
    
    /**
     * Nested class that represents time intervals. Only used in {@link Employee} class, so is nested and private.
     * For more information about nested classes, see {@link http://docs.oracle.com/javase/tutorial/java/javaOO/nested.html}.
     * 
     * @author ricardosousa
     * */
    private class TimeIntervalNested 
    {
        private final long durationInSecs;
        
        public TimeIntervalNested(Date startWorkDate, Date endWorkDate)
        {
        	DateTime dt1 = new DateTime(startWorkDate);
            DateTime dt2 = new DateTime(endWorkDate);
            
            this.durationInSecs = new Duration(dt1, dt2).getStandardSeconds();
        }
        
        public long getSecondsDuration() { return this.durationInSecs; }
    }
    
} // End of Employee's class.
