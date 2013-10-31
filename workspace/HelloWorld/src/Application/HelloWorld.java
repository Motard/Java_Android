package Application;

/**
 * This is an JAVA Hello World.
 * This shows a basic anatomy of a program in JAVA.
 * */
public class HelloWorld 
{
	public static void main(String[] args)
	{
		/*
		if (args.length != 0)
			System.out.println("Hello World " + args[0]);
		*/
		
		//String empty;
		//empty = "";
		//String empty = new String("");
		//String noString = null;
		
		System.out.println("Foram passados " + args.length + " parametros.");
		  
        
		String str = "Hello World";
		// Ternary operator.
		str = (args.length > 0)? str + " " + args[0] : str;
		
		System.out.println(str);
	}
}