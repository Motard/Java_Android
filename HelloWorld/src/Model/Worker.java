package Model;

public interface Worker
{
	// Exemplo de tecnica de sucesso/falha.
	abstract boolean startWork();
	// keyword abstract não é obrigatório colocar nas interfaces, o Java coloca por omissão.
	// Exemplo de orientação à excepção.
	void stopWork() throws UnsupportedOperationException;
	// Exemplo de idempotencia.
	long workedHours();
	boolean isWorking();
}
