package Application;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/*
 * Using joda-time.jar:
 * 1st: Download from: http://www.joda.org/joda-time/
 * 2nd: Add jar into project (create "lib" folder to put the jar, then add via 
 * project Build Path)
 * */

/**
 * Class for specifies the entry object for one worker logger. 
 * Saves the start and end date of one work.
 * @author ricardosousa
 * */
public class WorkerLoggerEntry 
{
	private Date _startDate,
				 _endDate;
	
	/**
	 * Trigger the start of the work.
	 * @throws Exception 
	 * */
	public void start() throws Exception 
	{
		if (_startDate != null)
			throw new Exception("Already started the work!");
		
		// Sets start date with current date time.
		_startDate = new Date();
	}

	/**
	 * Trigger the end of the work.
	 * @throws Exception 
	 * */
	public void stop() throws Exception 
	{
		if (_startDate == null)
			throw new Exception("It's need to call start() method first!");
		
		if (_endDate != null)
			throw new Exception("Already ended the work!");
		
		// Sets end date with current date time.
		_endDate = new Date();
	}
	
	/**
	 * Get the time of the work in seconds.
	 * @return The Difference, in seconds, between start {@link Date} and end {@link Date}.
	 * @throws Exception 
	 * */
	public long getInterval() throws Exception
	{
		if (_startDate == null || _endDate == null)
			throw new Exception("It's need to call start() method then stop() method first!");
			
		// Using joda-time DateTime for simplifies the comparison between the dates.
		DateTime startDate = new DateTime(_startDate);
		DateTime endDate   = new DateTime(_endDate);
		
		// Use joda-time Duration object for get the difference, in seconds, between start and end date.
		Duration duration = new Duration(startDate, endDate);
		
		return duration.getStandardSeconds();
	}
}