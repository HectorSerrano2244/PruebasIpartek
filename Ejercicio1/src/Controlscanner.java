import java.util.Scanner;

public class Controlscanner {
	private static final String FORMATO = "[%s] : ";
	private static final Scanner captura = new Scanner(System.in);

	public static String leerLinea(String mensaje) {
		mostrarMensaje(mensaje);
		return captura.nextLine();
	}

	public static String leerLinea() {
		return leerLinea(null);
	}

	public static Long leerLong(String mensaje) {
		Boolean hayErrores;
		Long l = null;
		do {
			hayErrores=false;
			mostrarMensaje(mensaje);
			try {
//				l = captura.nextLong();
				l=Long.parseLong(captura.nextLine());
			} catch (Exception e) {
				System.out.println("El dato no es un numero");
				hayErrores=true;
			}finally {
				captura.nextLine();
			}
		} while (hayErrores);
		
		return l;
	}

	private static void mostrarMensaje(String mensaje) {
		if (mensaje != null) {
			System.out.println(String.format(FORMATO, mensaje));
		}
	}

	public static Long leerLong() {
		return leerLong(null);
	}

	public static Scanner getCaptura() {
		return captura;
	}

	public static void cerrarEscaner() {
		captura.close();
	}
}
