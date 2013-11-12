package Application;

/**
 * This is an JAVA Hello World.
 * This shows a basic anatomy of a program in JAVA.
 * */
public class HelloWorld 
{
	public static void main(String[] args) throws Exception
	{
		/*
		if (args.length != 0)
			System.out.println("Hello World " + args[0]);
		*/
		
		//String empty;
		//empty = "";
		//String empty = new String("");
		//String noString = null;
		
		/*
		System.out.println("Foram passados " + args.length + " parametros.");
		  
        
		String str = "Hello World";
		// Ternary operator.
		str = (args.length > 0)? str + " " + args[0] : str;
		
		System.out.println(str);
		*/
		
		/*
		// Criar objecto do tipo empregado utilizando o constructor por omissão.
		Employee emp = new Employee();
		// Atribuir valores aos campos do empregado, utilizando os setters methods.
		emp.setEmpNum(1234);
		emp.setName("Ricardo Sousa");
		emp.setCity("Lisboa");
		*/
		
		// Criar objecto do tipo empregado utilizando o contructor com parâmetros.
		Employee emp = new Employee(1234, "Ricardo Sousa", "Lisboa");
		
		// String name = emp.getName();
		// System.out.println("Hello " + name + ", from " + emp.getCity());
		
		System.out.println("Hello " + emp.toString());// Não é necessário o .toString()
		
		Object emp2 = new Employee(1234, "Ricardo Sousa", "Lisboa");
		System.out.println("Hello " + emp2.toString());
	}
}