package Model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TimeIntervalOuter 
{
	private Date startWorkDate,
				 endWorkDate;
	
	public TimeIntervalOuter(Date startWorkDate, Date endWorkDate)
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
