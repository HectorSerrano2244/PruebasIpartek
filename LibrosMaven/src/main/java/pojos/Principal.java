package pojos;
public class Principal {
	private static String opcion;
	private static CrudAble<Libro> libros = new LibroDaoArrayList();

	public static void main(String[] args) {

		do {
			System.out.println("OPCIONES");
			System.out.println("--------");
			System.out.println("1- Listado");
			System.out.println("2- Alta");
			System.out.println("3- Moificar");
			System.out.println("4- Baja");
			System.out.println("5- Buscar por ID");
			System.out.println("5- Buscar por ISBN");
			System.out.println("0- Salir");
			opcion = Controlscanner.leerLinea("Esperando opci�n...");
			switch (opcion) {
			case "0":
				System.out.println("Programa terminado...");
				System.exit(0);
			case "1":
				for (Libro libro1 : libros.getAll()) {
					System.out.println(libro1);
				}
				break;
			case "2":
				pedirDatosLibro();
			}

		} while (!"0".equals(opcion));
	}

	public static void pedirDatosLibro() {
		Libro libro = new Libro();
		libro.setId(calculaID());
		libro.setTitulo(Controlscanner.leerLinea("T�tulo"));
		libro.setEditorial(Controlscanner.leerLinea("Editorial"));
		libro.setIsbn(Controlscanner.leerLinea("ISBN"));
		libro.setPrecio(Controlscanner.leerDoble("Precio"));
		libros.insert(libro);
	}
	public static Long calculaID() {
		Long id = 0L;
		for (@SuppressWarnings("unused") Libro libro1 : libros.getAll()) {
			id+=1;
		}
		return id+=1;
	}

}
