import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CuentaFicheroLocal implements CrudAble<Cuenta> {
//	private static final String RUTA_FICHERO_CUENTAS = "cuentas.tmp";

	@Override
	public void getAll() {
		File fichero = new File(Cuenta.RUTA_FICHERO_CUENTAS);
		String lineafichero;
		try {
			 BufferedReader lectura = new BufferedReader(new FileReader(fichero));
			 while ((lineafichero = lectura.readLine()) != null) {
				 System.out.println(lineafichero);
				}
				lectura.close();
		} catch (IOException e) {
			System.out.println("Algo pasa con el fichero");
		}
	}

	@Override
	public Cuenta getById(Long id) {
		return CrudAble.super.getById(id);
	}

	@Override
	public void insert(Cuenta clase) {
		CrudAble.super.insert(clase);
	}

	@Override
	public void update(Cuenta clase) {
		CrudAble.super.update(clase);
	}

	@Override
	public void delete(Long id) {
		CrudAble.super.delete(id);
	}
	
}
