import java.util.Scanner;

public class Controlscanner {
	private static final Scanner captura=new Scanner(System.in);

	public Scanner getCaptura() {
		return captura;
	}

	public void cerrarEscaner() {
		captura.close();
	}
}

