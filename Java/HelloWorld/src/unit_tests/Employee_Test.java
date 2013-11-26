package unit_tests;

import org.junit.Assert;
import org.junit.Test;

import model.Director;
import model.Employee;

/**
 * Unit tests for {@link Employee} class.
 * 
 * @author ricardosousa
 * */
public class Employee_Test 
{
	@Test
	public void creationTest()
	{
		Employee emp = new Director(1234, "Ricardo");
		
		Assert.assertEquals(1234, emp.getEmpNum());
		Assert.assertEquals("Ricardo", emp.getName());
		
		// Change emp name.
		emp.setName("Ricardo", "Sousa");
		
		Assert.assertEquals("Ricardo Sousa", emp.getName());
	}
	
	
}
