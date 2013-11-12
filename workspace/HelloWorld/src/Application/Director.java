package Application;

public class Director extends Employee
{	
	/**
	 * Creates an instance of {@link Director}, without arguments.
	 * */
	public Director()
	{
		// this.setCity("Lisboa");
		// super(); Não é preciso, chamado sempre por omissão no inicio dos .ctors.
	}
	
	/**
	 * Creates an instance of {@link Director}, with arguments.
	 * 
	 * @param empNum The Number of the {@link Director}
	 * @param name   The name of the {@link Director}
	 * */
	public Director(int empNum, String name)
	{
		// Already have the information of the city, This calls the default .ctor.
//		this();
//		this.setEmpNum(empNum);
//		this.setName(name);
		
		super(empNum, name);
	}
	
	/**
	 * Creates an instance of {@link Director}, with arguments.
	 * 
	 * @param empNum The Number of the {@link Director}
	 * @param name   The name of the {@link Director}
	 * @param city 	 The city of the {@link Director}
	 * */
	public Director(int empNum, String name, String city)
	{
//		this.setEmpNum(empNum);
//		this.setName(name);
//		this.setCity(city);
		
		super(empNum, name, city);
	}

	@Override
	public String toString() 
	{
		return "Director [empName=" + this.getName() + " empNum=" + this.getEmpNum() + ""
				+ " empCity=" + this.getCity() +"]";
	}
	
	public void firesEveryone()
	{
		System.out.println("Despediu toda a gente!");
	}
}
