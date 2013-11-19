package Application;

public interface Worker
{
	abstract void startWork();
	// keyword abstract não é obrigatório colocar nas interfaces, o Java coloca por omissão.
	void stopWork();
	long workedHours();
}
