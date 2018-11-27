import java.io.IOException;

import bibliotecas.Controlscanner;

public class Pral {

	public static void main(String[] args) throws IOException {
		String opcion = "";
		do {
			System.out.println("MENU DE OPERACIONES");
			System.out.println("-------------------");
			System.out.println("");
			System.out.println("1- Crear nueva cuenta");
			System.out.println("0- Salir");
			opcion = Controlscanner.leerLinea("Teclee una opciòn [0-1]");
			switch (opcion) {
			case "1":
				Cuenta.recogerDatosCuenta();
				break;
			case "0":
				System.out.println("Programa terminado");
				System.exit(0);
			}
		} while (opcion != "0");

	}

}
