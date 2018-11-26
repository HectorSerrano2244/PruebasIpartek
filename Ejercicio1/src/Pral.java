
public class Pral {

	public static void main(String[] args) {
		String opcion="";
		Controlscanner recepcion= new Controlscanner();
		do {
			System.out.println("MENU DE OPERACIONES");
			System.out.println("-------------------");
			System.out.println("");
			System.out.println("1- Crear nueva cuenta");
			System.out.println("0- Salir");
			opcion = recepcion.getCaptura().nextLine();
			switch(opcion) {
			case "1": Cuenta.recogerDatosCuenta();	break;
			case "0": System.out.println("Programa terminado");recepcion.cerrarEscaner();System.exit(0);
			}
		}while(opcion != "0");

	}

}
