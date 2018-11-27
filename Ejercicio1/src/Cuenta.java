import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cuenta {
	private String titular;
	private Integer cuenta;
	private Double saldo;
	private static final Double SALDO_POR_DEFECTO = 0.0;
//	private static final String RUTA_FICHEROS = "C:\\Temp";
	private static final String RUTA_FICHERO_CUENTAS = "cuentas.tmp";
	

	public static void recogerDatosCuenta() throws IOException {
		Controlscanner preguntar = new Controlscanner();
		Cuenta nuevacuenta = new Cuenta();
		System.out.println("Introduzca el nombre del titular de la cuenta");
		nuevacuenta.setTitular(preguntar.getCaptura().nextLine());
		if (completarYGuardarCuenta(nuevacuenta)) {
			System.out.println("Cuenta creada");
			System.out.println(nuevacuenta);
		} else {
			throw new RuntimeException("Error al crear la cuenta");
		}
	}

	public static Boolean completarYGuardarCuenta(Cuenta nuevacuenta) throws IOException {
		Boolean exito = true;
		nuevacuenta.setSaldo(SALDO_POR_DEFECTO);
		nuevacuenta.setCuenta(calculaNumeroCuenta());
		File fichero = new File(RUTA_FICHERO_CUENTAS);
		BufferedWriter escritura = new BufferedWriter(new FileWriter(fichero,true));
		escritura.write(nuevacuenta.toString());
		escritura.newLine();
		escritura.close();
		return exito;
	}

	public static Integer calculaNumeroCuenta() throws IOException {
//		File ruta = new  File(RUTA_FICHEROS);
//		ruta.mkdirs();
		File fichero = new File(RUTA_FICHERO_CUENTAS);
		Integer cuantas=0;
		if (fichero.exists()) {
			@SuppressWarnings("unused")
			String lineafichero;
			BufferedReader lectura = new BufferedReader(new FileReader(fichero));
			while ((lineafichero = lectura.readLine()) != null) {
				cuantas++;
			}
			lectura.close();
			return cuantas+=1;
		} else {
			BufferedWriter escritura = new BufferedWriter(new FileWriter(fichero));
			escritura.write("");
			escritura.close();
			return 1;
		}	
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[titular=" + titular + ", cuenta=" + cuenta + ", saldo=" + saldo + "]";
	}

}
