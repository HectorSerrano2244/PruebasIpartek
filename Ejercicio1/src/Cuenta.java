
public class Cuenta {
	private String titular;
	private Integer cuenta;
	private Double saldo;
	private static final Double SALDO_POR_DEFECTO = 0.0;

	public static void recogerDatosCuenta() {
		Controlscanner preguntar= new Controlscanner();
		Cuenta nuevacuenta= new Cuenta();
		System.out.println("Introduzca el nombre del titular de la cuenta");
		nuevacuenta.setTitular(preguntar.getCaptura().nextLine());
		if (completarYGuardarCuenta(nuevacuenta)) {
			System.out.println("Cuenta creada");
		}else {
			throw new RuntimeException("Error al crear la cuenta");
		}
	}
	public static Boolean completarYGuardarCuenta(Cuenta nuevacuenta) {
		Boolean exito=true;
		nuevacuenta.setSaldo(SALDO_POR_DEFECTO);
		nuevacuenta.setCuenta(calculaNumeroCuenta());
		return exito;
	}
	public static Integer calculaNumeroCuenta() {
		
		return 0;
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
		return "Cuenta [titular=" + titular + ", cuenta=" + cuenta + ", saldo=" + saldo + "]";
	}
	
}
